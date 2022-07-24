package ru.surf.vorobev.gallery.performance.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.databinding.FragmentLoginBinding
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel
import ru.surf.vorobev.gallery.performance.viewmodel.UiState
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentLoginBinding

    private var phone: String = ""
    private var phoneIsValid: Boolean = false
    private fun getMask(): MaskedTextChangedListener = installOn(
        binding.loginTv,
        "+7 ([000]) [000]-[00]-[00]",
        object : MaskedTextChangedListener.ValueListener {
            override fun onTextChanged(maskFilled: Boolean, extractedValue: String, formattedValue: String) {
                phone = extractedValue
                phoneIsValid=maskFilled
            }
        }
    )

    private fun isPasswordValid():Boolean{
        return binding.passwordTv.text.toString().isNotEmpty()
                &&binding.passwordTv.text.toString().length>=6
                &&binding.passwordTv.text.toString().length<=255
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentLoginBinding.inflate(inflater,  container, false)
        getMask()
        return binding.root
    }

    private fun validate():Boolean{
        var result = true
        if (!phoneIsValid&&binding.passwordTv.text!!.isNotEmpty()) {
            binding.loginTil.error = "Не верный формат номера телефона"
            result=false
        }
        if (!isPasswordValid()) {
            binding.passwordTil.error = "Пароль должен быть от 6 до 255 символов"
            result=false
        }
        if (phone.isEmpty()) {
            binding.loginTil.error = "Поле не может быть пустым"
            result=false
        }
        if (binding.passwordTv.text!!.isEmpty()) {
            binding.passwordTil.error = "Поле не может быть пустым"
            result=false
        }
        return result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            if (validate())
                mainViewModel.login("+7${phone}", binding.passwordTv.text.toString(), this.context!!)
            else return@setOnClickListener
        }
    }


    override fun onResume() {
        super.onResume()
        mainViewModel.state.observe(this, Observer {
            when(it){
                is UiState.Loading -> binding.loginButton.isLoading=true
                is UiState.Succes -> {
                    binding.loginButton.isLoading=false
                    mainViewModel.clearState()
                    binding.root.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
                is UiState.InvalidLoginOrPass ->{

                    Snackbar.make(binding.root,"Не правильный огин или пароль",Snackbar.LENGTH_LONG).setAnchorView(binding.loginButton).show()
                    binding.loginButton.isLoading=false
                }
                is UiState.DataError ->{
                    Snackbar.make(binding.root,"Ошибка подключения к сети",Snackbar.LENGTH_LONG).setAnchorView(binding.loginButton).show()
                    binding.loginButton.isLoading=false
                }
                else->{}
            }
        })
    }
}