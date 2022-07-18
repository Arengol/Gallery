package ru.surf.vorobev.gallery

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.surf.vorobev.gallery.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}