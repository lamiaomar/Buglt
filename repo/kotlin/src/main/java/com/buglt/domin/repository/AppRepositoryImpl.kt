package com.buglt.domin.repository

import android.net.Uri
import android.util.Log
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

class AppRepositoryImpl @Inject constructor(
    private val baseRemoteDataSource: BaseRemoteDataSource,
    private val firebaseInstance: FirebaseFirestore
) {

    val generatedFilePath = MutableLiveData("")

    suspend fun createTicket(createTicketBody: CreateTicketBody): TicketDataDto {
        return baseRemoteDataSource.createTicket(
            createTicketBody = createTicketBody
        )
    }

    fun saveImageURL(imageUri: Uri?) {
        val storage = Firebase.storage
        val storageReference = storage.getReference("images/${UUID.randomUUID()}")
        var generatedFilePath = ""

        if (imageUri != null) {
            storageReference.putFile(imageUri)
                .addOnSuccessListener { result ->
                    result.metadata?.reference?.downloadUrl?.addOnCompleteListener { task ->
                        generatedFilePath = task.result.toString()
                        this@AppRepositoryImpl.generatedFilePath.postValue(generatedFilePath)
                    }
                }
                .addOnFailureListener {
                    // Handle fail case
                }
        }
    }


    suspend fun getTickets(): TicketsDto {
        return baseRemoteDataSource.getTickets()
    }
}