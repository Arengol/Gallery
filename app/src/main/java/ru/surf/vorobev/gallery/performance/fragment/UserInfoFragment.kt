package ru.surf.vorobev.gallery.performance.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.databinding.FragmentUserInfoBinding
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel
import ru.surf.vorobev.gallery.performance.viewmodel.UiState
import javax.inject.Inject

@AndroidEntryPoint
class UserInfoFragment : Fragment() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentUserInfoBinding.inflate(inflater,  container, false)
        binding.tabLayout.getTabAt(2)!!.select()
        showUserInfo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener {
            mainViewModel.logout()
        }
            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position==0) {
                    mainViewModel.clearState()
                    binding.root.findNavController()
                        .navigate(R.id.action_userInfoFragment_to_mainFragment)
                }
                if(tab!!.position==1) {
                    mainViewModel.clearState()
                    binding.root.findNavController()
                        .navigate(R.id.action_userInfoFragment_to_likedPostFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun showUserInfo(){
        mainViewModel.userData.value!!.firstName?.let { binding.firstNameTv.text = it } ?: run {
            binding.firstNameTv.isVisible = false
        }
        mainViewModel.userData.value!!.lastName?.let { binding.lastNameTv.text = it } ?: run {
            binding.lastNameTv.isVisible = false
        }
        mainViewModel.userData.value!!.about?.let { binding.aboutTv.text = it } ?: run {
            binding.aboutTv.isVisible = false
        }
        mainViewModel.userData.value!!.city?.let { binding.cityTv.text = it } ?: run {
            binding.cityTv.isVisible = false
            binding.cityHeader.isVisible = false
        }
        mainViewModel.userData.value!!.phone?.let { binding.phoneTv.text = it } ?: run {
            binding.phoneTv.isVisible = false
            binding.phoneHeader.isVisible = false
        }
        mainViewModel.userData.value!!.email?.let { binding.mailTv.text = it } ?: run {
            binding.mailTv.isVisible = false
            binding.mailHeader.isVisible = false
        }
        mainViewModel.userData.value!!.avatar?.let { Glide.with(this.context!!).load(it).into(binding.AvatarImageView) }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.state.observe(this, Observer {
            when(it){
                is UiState.Loading -> binding.logoutBtn.isLoading=true
                is UiState.Unathorized -> {
                    mainViewModel.clearState()
                    binding.root.findNavController().navigate(R.id.action_userInfoFragment_to_loginFragment)}
                is UiState.DataError ->{
                    Snackbar.make(binding.root,"Не удалось выйти, попробуйте еще раз", Snackbar.LENGTH_LONG).setAnchorView(binding.logoutBtn).show()
                    binding.logoutBtn.isLoading=false
                }
                else->{}
            }
        })
    }

}

