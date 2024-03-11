package com.imgurgallery.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import com.imgurgallery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: GalleryViewModel by viewModels()

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                GalleryList(this)
            }
        }
    }

    /**
     * Change the UI of the recycler view between list and grid.
     */
    private fun toggleRecyclerUI(item: MenuItem) {
        if (viewModel.listOrientation.value) {
            item.title = getString(R.string.grid_menu_item)
        } else {
            item.title = getString(R.string.list_menu_item)
        }
        viewModel.toggleOrientation()
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
}