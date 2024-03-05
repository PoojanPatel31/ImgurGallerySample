package com.imgurgallery.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.imgurgallery.BR
import com.imgurgallery.ui.details.GalleryDetailActivity
import com.imgurgallery.R
import com.imgurgallery.databinding.HomeActivityBinding
import com.imgurgallery.models.GalleryImages

class HomeActivity : ComponentActivity(), Observer<Result<List<GalleryImages>>> {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var adapter: GalleryListAdapter
    private lateinit var bindView: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = HomeActivityBinding.inflate(layoutInflater)
        setContentView(bindView.root)

        galleryViewModel =
            ViewModelProvider(this, GalleryViewModelFactory())[GalleryViewModel::class.java]
        adapter = galleryViewModel.getAdapter()

        galleryViewModel.galleryList.observe(this, this)
        galleryViewModel.onGallerySelect.observe(this) {
            Intent(this@HomeActivity, GalleryDetailActivity::class.java).apply {
                putExtra(GalleryDetailActivity.GALLERY_KEY, it)
            }.let { startActivity(it) }
        }

        bindView.setVariable(BR.viewModel, galleryViewModel)

        bindView.galleryList.layoutManager = GridLayoutManager(this, 1)

        galleryViewModel.validateIfDataIsCached()
    }

    /**
     * Change the UI of the recycler view between list and grid.
     */
    private fun toggleRecyclerUI(item: MenuItem) {
        val recyclerViewLayoutManager =
            bindView.galleryList.layoutManager as GridLayoutManager
        if (recyclerViewLayoutManager.spanCount == GalleryListAdapter.listSize) {
            recyclerViewLayoutManager.spanCount = GalleryListAdapter.gridSize
            item.title = getString(R.string.grid_menu_item)
        } else {
            recyclerViewLayoutManager.spanCount = GalleryListAdapter.listSize
            item.title = getString(R.string.list_menu_item)
        }
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }

    /**
     * Inflate the menu item in toolbar.
     */
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * Toggle the UI between list view and grid view when menu item is tapped.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.list_menu) toggleRecyclerUI(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onChanged(value: Result<List<GalleryImages>>) {
        val list = value.getOrNull()
        val error = value.exceptionOrNull()
        if (!list.isNullOrEmpty()) {
            bindView.galleryList.visibility = View.VISIBLE
            bindView.errorMessage.visibility = View.GONE
            galleryViewModel.setAdapterData(list)
        } else if (error != null) {
            bindView.galleryList.visibility = View.GONE
            bindView.errorMessage.visibility = View.VISIBLE
            bindView.errorMessage.text = error.message
        }
    }
}