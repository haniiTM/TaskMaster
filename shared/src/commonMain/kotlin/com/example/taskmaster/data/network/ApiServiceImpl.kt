package com.example.taskmaster.data.network

import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.ActivityDTO
import com.example.taskmaster.data.network.models.CalendarPlan
import com.example.taskmaster.data.network.models.Dependence
import com.example.taskmaster.data.network.models.DescriptionDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import com.example.taskmaster.data.network.models.ManHoursReportDTO
import com.example.taskmaster.data.network.models.PersonDTO
import com.example.taskmaster.data.network.models.RegisterReceiveRemote
import com.example.taskmaster.data.network.models.StatusDTO
import com.example.taskmaster.data.network.models.TaskByID
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
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

    override suspend fun fetchTaskById(taskId: Number): TaskByID? {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/task/${taskId}")
            if (response.status.isSuccess()) {
                val tas = response.bodyAsText()
                println(tas)
                val tasks = response.body<TaskByID?>()
                println("Server returned task: ${tasks}")
                tasks
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

    override suspend fun updateDescriptionTask(taskId: Int, description: String) {
        try {
            val taskDescription = TaskDTO()
            taskDescription.content = description
            val response: HttpResponse = httpClient.put("http://5.35.85.206:8080/task/update/${taskId}") {
                contentType(ContentType.Application.Json)
                setBody(taskDescription)
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

    override suspend fun DeleteTaskOrProject(taskId: Int) {
        try {
            val response: HttpResponse = httpClient.delete("http://5.35.85.206:8080/task/${taskId}")
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
    override suspend fun fetchDescription(descrId: Int): MutableList<DescriptionDTO?> {
        val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/description/${descrId}")
        if (response.status.isSuccess()) {
            val json = response.bodyAsText()
            val description = Json.decodeFromString<MutableList<DescriptionDTO?>>(json)
            println("Server returned description: ${description}")
            return description
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
    override suspend fun registerUser(user: RegisterReceiveRemote) {
        try {
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/register") {
                contentType(ContentType.Application.Json)
                setBody(user)
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

    override suspend fun createManHours(manHour: ManHoursDTO, taskId: Int): Boolean {
        var success = false
        try {
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/manhours/${taskId}") {
                contentType(ContentType.Application.Json)
                setBody(manHour)
            }
            if (response.status.isSuccess()) {
                println("Server create project: ${response.status}")
                success = true
                return success
            } else {
                println("Server returned error status: ${response.status}")
                return success
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            return success
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            return success
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            return success
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return success
        }
    }

    override suspend fun fetchManHours(taskId: Int): MutableList<ManHoursDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/manhours/${taskId}")
            if (response.status.isSuccess()) {
                val json = response.bodyAsText()
                val manHours = Json.decodeFromString<MutableList<ManHoursDTO?>>(json)
                println("Server returned manHours: ${manHours}")
                manHours
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

    override suspend fun fetchActivity(): MutableList<ActivityDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/activity")
            if (response.status.isSuccess()) {
                val json = response.bodyAsText()
                val activity = Json.decodeFromString<MutableList<ActivityDTO?>>(json)
                println("Server returned acivity: ${activity}")
                activity
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

    override suspend fun fetchAllPerson(): MutableList<PersonDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/person")
            if (response.status.isSuccess()) {
                val json = response.bodyAsText()
                val personDTO = Json.decodeFromString<MutableList<PersonDTO?>>(json)
                println("Server returned PersonDTO: ${personDTO}")
                personDTO
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

    override suspend fun fetchPersonInTask(taskId: Int): MutableList<PersonDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/person/personintask/${taskId}")
            val json = response.bodyAsText()
            val personDTO = Json.decodeFromString<MutableList<PersonDTO?>>(json)
            println("Server returned PersonDTO: ${personDTO}")
            personDTO
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

    override suspend fun fetchPersonInProject(projId: Int): MutableList<PersonDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/person/personinproject/${projId}")
            val json = response.bodyAsText()
            val personDTO = Json.decodeFromString<MutableList<PersonDTO?>>(json)
            println("Server returned PersonDTO: ${personDTO}")
            personDTO
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

    override suspend fun fetchPersonFreeFromProject(projId: Int): MutableList<PersonDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/person/freeperson/${projId}")
            val json = response.bodyAsText()
            val personDTO = Json.decodeFromString<MutableList<PersonDTO?>>(json)
            println("Server returned PersonDTO: ${personDTO}")
            personDTO
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

    override suspend fun fetchPersonFreeForTask(taskId: Int): MutableList<PersonDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/person/freepersontask/${taskId}")
            val json = response.bodyAsText()
            val personDTO = Json.decodeFromString<MutableList<PersonDTO?>>(json)
            println("Server returned PersonDTO: ${personDTO}")
            personDTO
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

    override suspend fun linkUserTaskOrProject(urp: UserRoleProjectDTO): Boolean {
        var success = false
        try {
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/user_role_project") {
                contentType(ContentType.Application.Json)
                setBody(urp)
            }
            if (response.status.isSuccess()) {
                println("Server link user to task or project: ${response.status}")
                success = true
                return success
            } else {
                println("Server returned error status: ${response.status}")
                return success
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            return success
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            return success
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            return success
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return success
        }
    }

    override suspend fun deletePersonFromSystem(personId: MutableList<Int>) {
        try {
            val personIdList = mutableListOf<PersonDTO>()
            personId.forEach { id ->
                personIdList.add(
                    PersonDTO(
                        id = id,
                        surname = "",
                        name = "",
                        patronymic = "",
                        role = null
                    )
                )
            }
            val response: HttpResponse = httpClient.delete("http://5.35.85.206:8080/User") {
                contentType(ContentType.Application.Json)
                setBody(personIdList)
            }
            if (response.status.isSuccess()) {
                println("Server delete person: ${response.status}")
            } else {
                println("Server returned error person: ${response.status}")
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

    override suspend fun updateHoursSpent(urp: UserRoleProjectDTO, taskId: Int): Boolean {
        var success = false
        try {
            val response: HttpResponse = httpClient.put("http://5.35.85.206:8080/user_role_project/${taskId}") {
                contentType(ContentType.Application.Json)
                setBody(urp)
            }
            if (response.status.isSuccess()) {
                println("Server link user to task or project: ${response.status}")
                success = true
                return success
            } else {
                println("Server returned error status: ${response.status}")
                return success
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            return success
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            return success
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            return success
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return success
        }
    }

    override suspend fun updateTimeEstimation(taskDTO: TaskDTO, taskId: Int): Boolean {
        var success = false
        try {
            val response: HttpResponse = httpClient.put("http://5.35.85.206:8080/task/update/${taskId}") {
                contentType(ContentType.Application.Json)
                setBody(taskDTO)
            }
            if (response.status.isSuccess()) {
                println("Server link user to task or project: ${response.status}")
                success = true
                return success
            } else {
                println("Server returned error status: ${response.status}")
                return success
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            return success
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            return success
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            return success
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return success
        }
    }

    override suspend fun fetchTaskForDependence(projId: Int, taskId: Int): MutableList<TaskDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/task/taskdependence/${projId}/${taskId}")
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

    override suspend fun addDependenceForTask(taskDependent: Int, taskdependsOn: Int): Boolean{
        var success = false
        try {
            val dependence = Dependence(
                dependent = taskDependent,
                dependsOn = taskdependsOn
            )
            val response: HttpResponse = httpClient.post("http://5.35.85.206:8080/dependence") {
                contentType(ContentType.Application.Json)
                setBody(dependence)
            }
            if (response.status.isSuccess()) {
                println("Server addDependenceForTask: ${response.status}")
                success = true
                return success
            } else {
                println("Server returned error status: ${response.status}")
                return success
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            return success
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            return success
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            return success
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return success
        }
    }

    override suspend fun fetchCalenderPlan(projectId: Int): MutableList<CalendarPlan?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/user_role_project/calendar_plan/${projectId}")
            if (response.status.isSuccess()) {
                val calendarPlan = response.body<MutableList<CalendarPlan?>>()
                println("Server returned calendarPlan: ${calendarPlan}")
                calendarPlan
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

    override suspend fun fetchReportManHours(projectId: Int): MutableList<ManHoursReportDTO?> {
        return try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/manhours/report/${projectId}")
            if (response.status.isSuccess()) {
                val manHoursReportDTO = response.body<MutableList<ManHoursReportDTO?>>()
                println("Server returned ManHoursReportDTO: ${manHoursReportDTO}")
                manHoursReportDTO
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

    override suspend fun downloadFile(projectId: Int): String? {
        var name: String? = null

        try {
            val response: HttpResponse = httpClient.get("http://5.35.85.206:8080/user_role_project/excel/$projectId")
            if (response.status.isSuccess()) {
                name = "calendar_plan_$projectId.xlsx"
                return name
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
        return name
    }
}
