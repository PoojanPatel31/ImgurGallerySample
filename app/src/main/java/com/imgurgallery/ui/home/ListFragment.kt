package com.imgurgallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.imgurgallery.ui.HomeViewModel
import com.imgurgallery.ui.ToolBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    @ExperimentalMaterial3Api
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Surface {
                    Scaffold(topBar = { ToolBar() }) { innerPadding ->
                        GalleryList(viewModel, innerPadding) {
                            findNavController().navigate(
                                ListFragmentDirections.navigateToDetailScreen(
                                    it.gallery.id
                                )
                            )
                        }
                    }

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.validateIfDataIsCached()
        viewModel.fetchGalleryList()
    }

//    /**
//     * Change the UI of the recycler view between list and grid.
//     */
//    private fun toggleRecyclerUI(item: MenuItem) {
//        if (viewModel.listOrientation.value) {
//            item.title = getString(R.string.grid_menu_item)
//        } else {
//            item.title = getString(R.string.list_menu_item)
//        }
//        viewModel.toggleOrientation()
//    }
//
//    /**
//     * Inflate the menu item in toolbar.
//     */
//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.home_menu, menu)
//        return super.onPrepareOptionsMenu(menu)
//    }
//
//    /**
//     * Toggle the UI between list view and grid view when menu item is tapped.
//     */
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.list_menu) toggleRecyclerUI(item)
//        return super.onOptionsItemSelected(item)
//    }
}