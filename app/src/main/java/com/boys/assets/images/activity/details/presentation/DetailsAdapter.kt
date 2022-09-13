package com.boys.assets.images.activity.details.presentation

import android.app.Activity
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.images.activity.details.model.DetailsModel
import com.boys.assets.images.databinding.ActivityDetailsListItemBinding
import com.boys.assets.images.helper.InterfaceDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget


class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.AddressHolder>() {

    private var listModel = mutableListOf<DetailsModel>()
    private val FADE_DURATION = 500

    fun provided(
        model: ArrayList<DetailsModel>,
        context: Activity,
        interfaceDialog: InterfaceDialog
    ) {
        this.listModel = model.toMutableList()

        interfaceDialog.dismisDialogLoading()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ActivityDetailsListItemBinding.inflate(inflater, parent, false)
        return AddressHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.listModel.size
    }

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        val model = this.listModel[position]
        holder.bind(model)
        setFadeAnimation(holder.itemView);
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }

    fun addItem(model: DetailsModel) {
        listModel.add(model)
        notifyItemInserted(listModel.size)
    }

    fun removeAt(position: Int) {
        listModel.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class AddressHolder(binding: ActivityDetailsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val thisBinding: ActivityDetailsListItemBinding

        fun bind(model: DetailsModel) {
            thisBinding.tvName.text = model.firstname + " " + model.lastname
            thisBinding.tvComment.text = model.comment
            thisBinding.tvDuration.text = "now"

            val initials = thisBinding.tvName.text
                .split(' ')
                .mapNotNull { it.firstOrNull()?.toString() }
                .reduce { acc, s -> acc + s }

            thisBinding.tvTumbnail.text = initials
        }

        init {
            thisBinding = binding
        }
    }
}