package com.example.taskmaster.data.network

import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.TaskDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

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

    override suspend fun fetchTaskDto(): TaskDTO? {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/task/project")
            if (response.status.isSuccess()) {
                println("Server returned TaskDTO: ${response.body<TaskDTO>()}")
                response.body<TaskDTO>()
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


}