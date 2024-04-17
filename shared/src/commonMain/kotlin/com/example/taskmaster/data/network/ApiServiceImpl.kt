package com.example.taskmaster.data.network

import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.StatusDTO
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class LoginRequest(val login: String, val password: String)

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    // Запрос на получение токена
    override suspend fun fetchUserToken(login: String, password: String): AccessTokenDto? {
        return try {
            val jsonBody = """{"login": "$login", "password": "$password"}"""
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/login") {
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }
            if (response.status.isSuccess()) {
                response.body<AccessTokenDto>()
            } else {
                println("Server returned error status: ${response.status}")
                null
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            null
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            null
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchProject(): MutableList<TaskDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/user_role_project/project")
            if (response.status.isSuccess()) {
                val projects = response.body<MutableList<TaskDTO?>>()
                println("Server returned projects: ${projects}")
                projects
            } else {
                println("Server returned error status: ${response.status}")
                mutableListOf() // возвращаем пустой список
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            mutableListOf()
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            mutableListOf()
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            mutableListOf()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            mutableListOf()
        }
    }

    override suspend fun fetchTask(idProj: Number): MutableList<TaskDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/task/downtask/${idProj}")
            if (response.status.isSuccess()) {
                val tasks = response.body<MutableList<TaskDTO?>>()
                println("Server returned projects: ${tasks}")
                tasks
            } else {
                println("Server returned error status: ${response.status}")
                mutableListOf() // возвращаем пустой список
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            mutableListOf()
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            mutableListOf()
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            mutableListOf()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            mutableListOf()
        }
    }

    override suspend fun fetchCompletedTask(idProj: Number): MutableList<TaskDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/task/downtask/completed/${idProj}")
            if (response.status.isSuccess()) {
                val tasks = response.body<MutableList<TaskDTO?>>()
                println("Server returned projects: ${tasks}")
                tasks
            } else {
                println("Server returned error status: ${response.status}")
                mutableListOf() // возвращаем пустой список
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            mutableListOf()
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            mutableListOf()
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            mutableListOf()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            mutableListOf()
        }
    }

    override suspend fun fetchUnfulfilleddTask(idProj: Number): MutableList<TaskDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/task/downtask/unfulfilled/${idProj}")
            if (response.status.isSuccess()) {
                val tasks = response.body<MutableList<TaskDTO?>>()
                println("Server returned projects: ${tasks}")
                tasks
            } else {
                println("Server returned error status: ${response.status}")
                mutableListOf() // возвращаем пустой список
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            mutableListOf()
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            mutableListOf()
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            mutableListOf()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            mutableListOf()
        }
    }

    override suspend fun createProject(nameProject: String){
        try {
            val project = TaskDTO()
            project.name = nameProject
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/task") {
                contentType(ContentType.Application.Json)
                setBody(project)
            }
            if (response.status.isSuccess()) {
                println("Server create project: ${response.status}")
            } else {
                println("Server returned error status: ${response.status}")
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    override suspend fun createTask(task: TaskDTO, parentId: Int) {
        try {
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/task/${parentId}") {
                contentType(ContentType.Application.Json)
                setBody(task)
            }
            if (response.status.isSuccess()) {
                println("Server create project: ${response.status}")
            } else {
                println("Server returned error status: ${response.status}")
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    override suspend fun updateStatusTask(taskId: Int, statusId: Int, nameTask: String) {
        try {
            val status = TaskDTO()
            status.status = statusId
            status.name = nameTask
            val response: HttpResponse = httpClient.put("http://5.35.85.206:8080/task/update/${taskId}") {
                contentType(ContentType.Application.Json)
                setBody(status)
            }
            if (response.status.isSuccess()) {
                println("Server create project: ${response.status}")
            } else {
                println("Server returned error status: ${response.status}")
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    override suspend fun fetchTypeOfActivity(): MutableList<TypeOfActivityDTO?> {
        val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/type_of_activity")
        if (response.status.isSuccess()) {
            val json = response.bodyAsText()
            val typeActiv = Json.decodeFromString<MutableList<TypeOfActivityDTO?>>(json)
            println("Server returned typeActiv: ${typeActiv}")
            return typeActiv
        } else {
            println("Server returned error status: ${response.status}")
            return  mutableListOf() // возвращаем пустой список
        }
    }

    override suspend fun fetchStatus(): MutableList<StatusDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/status")
            if (response.status.isSuccess()) {
                val json = response.bodyAsText()
                val status = Json.decodeFromString<MutableList<StatusDTO?>>(json)
                println("Server returned status: ${status}")
                status
            } else {
                println("Server returned error status: ${response.status}")
                mutableListOf() // возвращаем пустой список
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            mutableListOf()
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            mutableListOf()
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            mutableListOf()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            mutableListOf()
        }
    }
}