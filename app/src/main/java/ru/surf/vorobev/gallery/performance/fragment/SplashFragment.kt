package ru.surf.vorobev.gallery.performance.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.databinding.FragmentSplashBinding
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModelImpl
import ru.surf.vorobev.gallery.performance.viewmodel.UiState
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        mainViewModel.isAuthorized()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.state.observe(this, Observer {
            when(it){
                is UiState.Unathorized -> {
                    mainViewModel.clearState()
                    Handler(Looper.getMainLooper()).postDelayed({binding.root.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }, 800)}
                is UiState.Succes -> {
                    Log.i("User", mainViewModel.userData.value.toString())
                    mainViewModel.clearState()
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.root.findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }, 800)
                }else -> {}
            }
        })
    }
}