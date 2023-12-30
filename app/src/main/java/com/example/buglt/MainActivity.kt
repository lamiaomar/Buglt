package com.example.buglt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.buglt.images.UploadScreenShotManager
import com.example.buglt.navigation.SetUpNavGraph
import com.example.buglt.ui.theme.BugltTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    private lateinit var navController: NavHostController
    private val uploadScreenShotManager = UploadScreenShotManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BugltTheme {
                navController = rememberNavController()
                SetUpNavGraph(
                    navController = navController,
                    context = baseContext,
                    uploadScreenShotManager = uploadScreenShotManager
                )
            }
        }
        observeRequestPermission()
    }

    private fun observeRequestPermission() {
        uploadScreenShotManager.isPermissionRequested.observe(this) {
            if (it) {
                getImage()
            }
        }
    }

    private fun getImage() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }


    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                uploadScreenShotManager.imageURL?.postValue(imgUri)
            }
        }
}