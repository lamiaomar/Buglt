package com.example.buglt

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buglt.body.CreateTicketBody
import com.buglt.domin.repository.AppRepositoryImpl
import com.buglt.dto.Ticket
import com.buglt.enums.ResponseStatus
import com.example.buglt.images.UploadScreenShotManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for managing tickets related operations.
 *
 * @param appRepositoryImpl Repository for interacting with the data layer.
 */
@HiltViewModel
class TicketsViewModel(
    val appRepositoryImpl: AppRepositoryImpl
) : ViewModel() {

    val uploadScreenShotManager = UploadScreenShotManager()

    var createTicketBody: CreateTicketBody? = null
    var imageURI: Uri? = null

    private val _createTicketStatus = MutableLiveData<ResponseStatus>()
    val createTicketStatus: LiveData<ResponseStatus> = _createTicketStatus

    private val _ticketsList = MutableLiveData<List<Ticket>>()
    val ticketsList: LiveData<List<Ticket>> = _ticketsList

    /**
     * Function to create a new ticket.
     */
    fun createTicket() = viewModelScope.launch {
        try {
            if (createTicketBody != null) {
                if (imageURI != null) {
                    appRepositoryImpl.saveImageURL(imageURI)
                } else {
                    addTicketToSheet()
                }
            }
        } catch (e: Exception) {
            _createTicketStatus.postValue(ResponseStatus.ERROR)
        }
    }

    /**
     * Function to add a ticket to the sheet.
     */
    fun addTicketToSheet() = viewModelScope.launch {
        try {
            withContext(Dispatchers.IO) {
                if (createTicketBody != null) {
                    appRepositoryImpl.createTicket(createTicketBody!!)
                }
                _createTicketStatus.postValue(ResponseStatus.DONE)
            }
        } catch (e: Exception) {
            _createTicketStatus.postValue(ResponseStatus.ERROR)
        }
    }

    /**
     * Function to update the image storage URL in the ticket body.
     *
     * @param imageURL URL of the added image.
     */
    fun updateImageStorageURL(imageURL: String) {
        createTicketBody = createTicketBody?.copy(imageURL = imageURL)
    }

    private fun addUploadRecordToDb(uri: String) {
        val db = Firebase.firestore
        val data = HashMap<String, Any>()
        data["imageUrl"] = uri
        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                // Handle success
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    /**
     * Function to get the list of tickets.
     */
    fun getTickets() = viewModelScope.launch {
        try {
            val tickets = appRepositoryImpl.getTickets()
            _ticketsList.postValue(tickets.items)
        } catch (e: Exception) {
            // Handle exception
        }
    }

    /**
     * Function to set the body of the create ticket form.
     */
    fun setCreateTicketFormBody(
        title: String,
        description: String,
        platform: String,
        imageURL: Bitmap?
    ) {
        createTicketBody = CreateTicketBody(
            title = title,
            description = description,
            platform = platform,
            imageURL = imageURL.toString()
        )
    }
}