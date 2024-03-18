package com.imgurgallery.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.imgurgallery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
    }
}