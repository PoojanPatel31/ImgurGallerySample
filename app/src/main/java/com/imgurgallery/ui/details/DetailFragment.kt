package com.imgurgallery.ui.details

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.imgurgallery.ui.HomeViewModel
import com.imgurgallery.ui.ToolBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    @ExperimentalMaterial3Api
    @ExperimentalGlideComposeApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Surface {
                    Scaffold(topBar = { ToolBar() }) {
                        ImageList(viewModel, it)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.galleryId?.let {
            viewModel.setGalleryId(it)
        } ?: findNavController().popBackStack()
    }
}