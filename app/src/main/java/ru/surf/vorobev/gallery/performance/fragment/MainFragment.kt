package ru.surf.vorobev.gallery.performance.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.data.dto.PostDTO
import ru.surf.vorobev.gallery.databinding.FragmentMainBinding
import ru.surf.vorobev.gallery.performance.GalleryAdapter
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModelImpl
import ru.surf.vorobev.gallery.performance.viewmodel.UiState
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context,2)
        binding.progressbar.isVisible=false
        binding.imageErrorConnection.isVisible=false
        binding.errorConnection.isVisible=false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getPost()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position==2) {
                    mainViewModel.clearState()
                    binding.root.findNavController()
                        .navigate(R.id.action_mainFragment_to_userInfoFragment)
                }
                if(tab!!.position==1) {
                    mainViewModel.clearState()
                    binding.root.findNavController()
                        .navigate(R.id.action_mainFragment_to_likedPostFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.state.observe(this, Observer {
            when(it){
                is UiState.Loading -> {
                    binding.recyclerView.isVisible=false
                    binding.progressbar.isVisible=true
                }
                is UiState.Unathorized -> {
                    mainViewModel.logout()
                    mainViewModel.clearState()
                    binding.root.findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                }
                is UiState.Succes -> {
                    binding.recyclerView.isVisible=true
                    binding.progressbar.isVisible=false
                    binding.recyclerView.adapter = GalleryAdapter(mainViewModel)
                }
                is UiState.DeletLikeRequest -> mainViewModel.deleteLikedPost()
                is UiState.DataError -> {
                    binding.progressbar.isVisible=false
                    binding.errorConnection.isVisible=true
                    binding.imageErrorConnection.isVisible=true
                    binding.recyclerView.isVisible=false
                }
                else -> {}
            }
        })
    }
}
