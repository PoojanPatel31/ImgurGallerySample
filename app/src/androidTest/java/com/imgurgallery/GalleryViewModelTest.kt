package com.imgurgallery

import android.os.SystemClock
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imgurgallery.network.APIFactory
import com.imgurgallery.ui.home.GalleryViewModel
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit test for GalleryViewModel class
 */
@RunWith(AndroidJUnit4::class)
class GalleryViewModelTest {

    /**
     * Validate if gallery list is fetched.
     */
    @Test
    fun is_gallery_fetched(){
        val viewModel = GalleryViewModel(APIFactory.restApi)
        viewModel.fetchGalleryAndCache()
        //Wait for response to receive.
        SystemClock.sleep(7000)
        assert(viewModel.galleryList.value?.isNotEmpty() == true)
    }

}