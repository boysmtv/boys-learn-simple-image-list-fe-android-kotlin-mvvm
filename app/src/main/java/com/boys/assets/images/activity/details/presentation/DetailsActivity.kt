package com.boys.assets.images.activity.details.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.images.activity.details.custom.SwipeToDeleteCallback
import com.boys.assets.images.activity.details.model.DetailsModel
import com.boys.assets.images.activity.details.model.LocalModel
import com.boys.assets.images.activity.photos.model.ImagesModel
import com.boys.assets.images.databinding.ActivityDetailsBinding
import com.boys.assets.images.helper.InterfaceDialog
import com.boys.assets.images.utils.LogUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.util.*


class DetailsActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@DetailsActivity

    private lateinit var binding            : ActivityDetailsBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var detailsAdapter     : DetailsAdapter
    private lateinit var model              : ImagesModel

    private lateinit var listComment        : ArrayList<DetailsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivityDetailsBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        detailsAdapter      = DetailsAdapter()
        model               = ImagesModel()
        listComment         = ArrayList<DetailsModel>()

        setContentView(binding.root)
        supportActionBar!!.hide()

        getIntentExtra()
        setupActivity(binding, detailsAdapter)
    }

    private fun setupActivity(
        binding: ActivityDetailsBinding,
        detailsAdapter: DetailsAdapter
    ) {
        Glide.with(binding.root).load(model.download_url).into(binding.icPhoto)

        binding.rvComment.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvComment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true)
        binding.rvComment.adapter = detailsAdapter

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.rvComment.adapter as DetailsAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvComment)

        binding.llBack.setOnClickListener {
            finish()
        }

        binding.ivAdd.setOnClickListener {
            generateComment()
        }
    }

    private fun generateComment(){
        val fname = LocalModel.strFirstName
        val lname = LocalModel.strLastName
        val noun = LocalModel.strNoun
        val verbs = LocalModel.strVerbs

        val model = DetailsModel(
            firstname = getRandom(fname, 1),
            lastname = getRandom(lname, 1),
            comment = getRandom(noun, 10) + getRandom(verbs, 10)
        )
        detailsAdapter.addItem(model)
        binding.rvComment.scrollToPosition(detailsAdapter.itemCount - 1)
    }

    private fun getRandom(fname: Array<String>, count: Int): String {
        val random = Random()
        var strResult = ""
        for (i in 0 until count){
            val index = random.nextInt(fname.size)
            strResult += fname[index]
            strResult += " "
        }
        return strResult
    }

    private fun getIntentExtra(){
        val intent = intent
        model.id = intent.getStringExtra("id")
        model.author = intent.getStringExtra("author")
        model.width = intent.getIntExtra("width", 0)
        model.height = intent.getIntExtra("height", 0)
        model.url = intent.getStringExtra("url")
        model.download_url = intent.getStringExtra("download_url")

        LogUtil.e(TAG, "model: " + Gson().toJson(model))
    }
}