package com.imgurgallery.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.imgurgallery.R

@ExperimentalMaterial3Api
@Composable
fun ToolBar(menuAction: (() -> Unit)? = null) {
    val context = LocalContext.current
    val navHostController = rememberNavController()
    TopAppBar(
        title = { Text(context.getString(R.string.app_name)) },
        modifier = Modifier.shadow(4.dp),
        navigationIcon = {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "go back"
                )
            }
        },
        actions = {
            if (menuAction != null) {
                IconButton(onClick = menuAction) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}