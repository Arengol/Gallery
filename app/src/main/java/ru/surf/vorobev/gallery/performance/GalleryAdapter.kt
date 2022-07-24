package ru.surf.vorobev.gallery.performance

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.surf.vorobev.gallery.R
import ru.surf.vorobev.gallery.data.dto.PostDTO
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel

class GalleryAdapter (private val dataset: MainViewModel):
RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){
    lateinit var context:Context
    class GalleryViewHolder(view:View, mainViewModel: MainViewModel): RecyclerView.ViewHolder(view){
        val imageView: ImageView
        val textView: TextView
        val iconLike: ImageView
        var isLiked: Boolean = false
        var id: Int = 0
        init {
            imageView = view.findViewById(R.id.imageViewAdapter)
            textView = view.findViewById(R.id.textViewAdapter)
            iconLike = view.findViewById(R.id.isLikedAdapter)
            iconLike.setOnClickListener {
                if(!isLiked){ iconLike.setImageResource(R.drawable.ic_heart_fill)
                mainViewModel.addLikedPost(id, view.context )
                }
                else { mainViewModel.deleteLikedPostReq(id)
                    iconLike.setImageResource(R.drawable.ic_heart)}
                isLiked=!isLiked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_adapter, parent, false)
        context=parent.context
        return GalleryViewHolder(itemView, dataset)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.id=dataset.postData.value!!.get(position).id
        holder.isLiked=dataset.postData.value!![position].liked
        holder.textView.text=dataset.postData.value!![position].title
        if(holder.isLiked)holder.iconLike.setImageResource(R.drawable.ic_heart_fill)
        Glide.with(context).load(dataset.postData.value!![position].photoUrl).apply(RequestOptions().override(160, 222)).centerCrop().into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return dataset.postData.value!!.size
    }

}