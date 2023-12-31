package com.example.buglt.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.buglt.enums.ResponseStatus
import com.example.buglt.R
import com.example.buglt.TicketsViewModel
import com.example.buglt.di.ServiceLocator
import com.example.buglt.di.TicketViewModelFactory
import com.example.buglt.navigation.Screens
import com.example.buglt.navigation.SetUpNavGraph
import com.example.buglt.ui.theme.BugltTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseFragment : Fragment() {

    private val viewModel: TicketsViewModel by activityViewModels {
        TicketViewModelFactory(ServiceLocator.provideAppRepository())
    }

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {
                BugltTheme {
                    navController = rememberNavController()
                    SetUpNavGraph(
                        navController = navController,
                        context = context ?: requireContext(),
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRequestPermission()
        observeResponseStatus()
    }


    private fun observeResponseStatus() {
        viewModel.createTicketStatus.observe(viewLifecycleOwner) {
            when (it) {
                ResponseStatus.DONE -> {
                    navController.navigate(Screens.Home.route)
                }

                ResponseStatus.ERROR -> {
                    // TODO Show error view
                }

                ResponseStatus.LOADING -> {
                    // TODO Show loading view
                }
            }
        }
    }

    private fun observeRequestPermission() {
        viewModel.uploadScreenShotManager.isPermissionRequested.observe(viewLifecycleOwner) {
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
                viewModel.uploadScreenShotManager.imageURL?.postValue(imgUri)
            }
        }
}

