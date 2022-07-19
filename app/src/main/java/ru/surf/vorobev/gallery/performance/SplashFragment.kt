package ru.surf.vorobev.gallery.performance


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({binding.root.findNavController().navigate(R.id.loginFragment2)
    }, 800)
    }
}