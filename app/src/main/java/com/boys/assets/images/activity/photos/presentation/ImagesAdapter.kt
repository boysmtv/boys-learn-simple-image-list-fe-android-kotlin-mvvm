package com.boys.assets.images.activity.photos.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.images.activity.photos.model.ImagesModel
import com.boys.assets.images.databinding.ActivityImagesListItemBinding
import com.boys.assets.images.helper.InterfaceDialog
import com.bumptech.glide.Glide

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.AddressHolder>() {

    private var listModel = mutableListOf<ImagesModel>()
    private lateinit var listener : ImagesOnClickListener<ImagesModel>

    fun provided(
        model: ArrayList<ImagesModel>,
        context: ImagesActivity,
        interfaceDialog: InterfaceDialog
    ) {
        this.listModel = model.toMutableList()
        this.listener = context

        interfaceDialog.dismisDialogLoading()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ActivityImagesListItemBinding.inflate(inflater, parent, false)
        return AddressHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.listModel.size
    }

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        val model = this.listModel[position]
        holder.bind(position, model, listener)
    }

    inner class AddressHolder(binding: ActivityImagesListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val thisBinding: ActivityImagesListItemBinding

        fun bind(position: Int, model: ImagesModel, listener: ImagesOnClickListener<ImagesModel>) {

            Glide.with(thisBinding.root).load(model.download_url).into(thisBinding.icPhoto)

            thisBinding.tvAuthor.text = model.author

            // set on click listener
            thisBinding.layoutContent.setOnClickListener { view ->
                listener.onItemClick(
                    view,
                    position,
                    model
                )
            }
        }

        init {
            thisBinding = binding
        }
    }
}