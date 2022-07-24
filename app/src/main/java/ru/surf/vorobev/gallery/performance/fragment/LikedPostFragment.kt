package ru.surf.vorobev.gallery.performance.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.databinding.FragmentLikedPostBinding
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel
import javax.inject.Inject
@AndroidEntryPoint
class LikedPostFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentLikedPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedPostBinding.inflate(inflater, container, false)
        binding.tabLayout.getTabAt(1)!!.select()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position==2) {
                    mainViewModel.clearState()
                    binding.root.findNavController()
                        .navigate(R.id.action_likedPostFragment_to_userInfoFragment)
                }
                if(tab!!.position==0) {
                    mainViewModel.clearState()
                    binding.root.findNavController()
                        .navigate(R.id.action_likedPostFragment_to_mainFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

}