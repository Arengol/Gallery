package ru.surf.vorobev.gallery.performance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.databinding.ProgresButtonBinding

class ProgressButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?=null): FrameLayout(
    context, attributeSet
){
    private val binding = ProgresButtonBinding.inflate(LayoutInflater.from(context), this)

    var text: String = ""
        set(value) {
            field = value
            binding.buttonTv.text=value
        }

    var isLoading: Boolean = false
    set(value){
        field = value
        binding.buttonPb.isVisible=value
        binding.buttonTv.isVisible=!value
        binding.root.isEnabled=!value
    }

    init{
        context.withStyledAttributes(attributeSet, R.styleable.ProgressButton){
            text = getString(R.styleable.ProgressButton_textBtn) ?: ""
            isLoading = getBoolean(R.styleable.ProgressButton_isLoading, false)
        }
    }
}