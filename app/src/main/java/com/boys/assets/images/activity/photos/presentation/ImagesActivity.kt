package com.boys.assets.images.activity.photos.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.images.activity.photos.model.ImagesModel
import com.boys.assets.images.activity.search.vm.SearchViewModel
import com.boys.assets.images.activity.details.presentation.DetailsActivity
import com.boys.assets.images.databinding.ActivityImagesBinding
import com.boys.assets.images.helper.InterfaceDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesActivity : AppCompatActivity(), ImagesOnClickListener<ImagesModel> {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@ImagesActivity

    private lateinit var binding            : ActivityImagesBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var imagesAdapter      : ImagesAdapter
    private lateinit var imagesModel        : ImagesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivityImagesBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        imagesAdapter       = ImagesAdapter()
        imagesModel         = ImagesModel()

        setContentView(binding.root)
        supportActionBar!!.hide()

        val VM by viewModel<SearchViewModel>()

        setAddressVM(VM, binding, imagesAdapter)
        setRequest(VM, binding, imagesAdapter)
    }

    /**
     * set view model
     */
    private fun setAddressVM(
        VM: SearchViewModel,
        binding: ActivityImagesBinding,
        imagesAdapter: ImagesAdapter
    ) {
        with(VM){
            onSuccess.observe(thisContext) {
                imagesAdapter.provided(it, thisContext, interfaceDialog)
            }
            onError.observe(thisContext) {

            }
        }
    }

    private fun setRequest(
        VM: SearchViewModel,
        binding: ActivityImagesBinding,
        imagesAdapter: ImagesAdapter
    ) {
        // set loading on ui
        interfaceDialog.showDialogLoading("Loading ...")

        binding.rvSearch.adapter = imagesAdapter
        VM.doIt()
    }


    override fun onItemClick(v: View?, position: Int, model: ImagesModel) {
        val intent = Intent(thisContext, DetailsActivity::class.java)
        intent.putExtra("id", model.id)
        intent.putExtra("author", model.author)
        intent.putExtra("width", model.width)
        intent.putExtra("height", model.height)
        intent.putExtra("url", model.url)
        intent.putExtra("download_url", model.download_url)
        startActivity(intent)
    }

}