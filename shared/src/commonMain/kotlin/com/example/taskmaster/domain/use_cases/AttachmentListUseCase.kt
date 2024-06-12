package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.FileDTO
import org.koin.core.component.KoinComponent

class AttachmentListUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getAttachmentList(taskId: Int): List<FileDTO>? {
        return apiService.listFileInTask(taskId)?.file_resources
    }

    suspend fun deleteAttachment(attachmentId: Int, descriptionId: Int) {
        return apiService.deleteFile(descriptionId, attachmentId)
    }
}