package com.buglt.domin.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.buglt.body.CreateTicketBody
import com.buglt.datasource.BaseRemoteDataSource
import com.buglt.dto.TicketDataDto
import com.buglt.dto.TicketsDto
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.storage
import java.util.UUID
import javax.inject.Inject

/**
 * Repository implementation for handling application data interactions.
 */
class AppRepositoryImpl @Inject constructor(
    private val baseRemoteDataSource: BaseRemoteDataSource,
    private val firebaseInstance: FirebaseFirestore
) {

    // LiveData to observe the generated file path after image upload
    val generatedFilePath = MutableLiveData("")

    /**
     * Suspended function to create a new ticket using the provided [CreateTicketBody].
     */
    suspend fun createTicket(createTicketBody: CreateTicketBody): TicketDataDto {
        return baseRemoteDataSource.createTicket(
            createTicketBody = createTicketBody
        )
    }

    /**
     * Function to save the image URL to Firebase Storage.
     *
     * @param imageUri URI of the image to be saved.
     */
    fun saveImageURL(imageUri: Uri?) {
        val storage = Firebase.storage
        val storageReference = storage.getReference("images/${UUID.randomUUID()}")
        var generatedFilePath = ""

        if (imageUri != null) {
            storageReference.putFile(imageUri)
                .addOnSuccessListener { result ->
                    result.metadata?.reference?.downloadUrl?.addOnCompleteListener { task ->
                        generatedFilePath = task.result.toString()
                        // Set the generated file path in the LiveData for observation
                        this@AppRepositoryImpl.generatedFilePath.postValue(generatedFilePath)
                    }
                }
                .addOnFailureListener {
                    // Handle failure case
                }
        }
    }

    /**
     * Suspended function to retrieve the list of tickets from the remote data source.
     *
     * @return [TicketsDto] representing the list of tickets.
     */
    suspend fun getTickets(): TicketsDto {
        return baseRemoteDataSource.getTickets()
    }
}