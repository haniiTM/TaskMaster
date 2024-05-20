package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionDTOFileDTO(
    val id: Int?,
    val file_resources: List<FileDTO>?
)

@Serializable
class FileDTO (
    val id : Int?,
    val orig_filename : String?,
    val descriptionId: Int?,
    val type : String?
)