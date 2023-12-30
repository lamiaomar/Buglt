package com.example.buglt.images

import android.net.Uri
import androidx.lifecycle.MutableLiveData

class UploadScreenShotManager() {

    var isPermissionRequested = MutableLiveData(false)

    var imageURL: MutableLiveData<Uri>? = MutableLiveData(null)
}