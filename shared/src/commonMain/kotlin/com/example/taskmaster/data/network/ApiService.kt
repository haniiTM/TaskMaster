package com.example.taskmaster.data.network


import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.ActivityDTO
import com.example.taskmaster.data.network.models.CalendarPlan
import com.example.taskmaster.data.network.models.DescriptionDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import com.example.taskmaster.data.network.models.ManHoursReportDTO
import com.example.taskmaster.data.network.models.PersonDTO
import com.example.taskmaster.data.network.models.RegisterReceiveRemote
import com.example.taskmaster.data.network.models.StatusDTO
import com.example.taskmaster.data.network.models.TaskByID
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TaskDependenceOn
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import com.example.taskmaster.data.network.models.UserRoleProjectDTO

interface ApiService {
    suspend fun fetchUserToken(login: String, password: String): AccessTokenDto?
    suspend fun fetchProject(): MutableList<TaskDTO?>
    suspend fun fetchTask(idProj: Number): MutableList<TaskDTO?>
    suspend fun fetchTaskById(taskId: Number): TaskByID?
    suspend fun createProject(nameProject: String)
    suspend fun createTask(task: TaskDTO, parentId: Int)
    suspend fun fetchTypeOfActivity(): MutableList<TypeOfActivityDTO?>
    suspend fun fetchStatus(): MutableList<StatusDTO?>
    suspend fun fetchCompletedTask(idProj: Number): MutableList<TaskDTO?>
    suspend fun fetchUnfulfilleddTask(idProj: Number): MutableList<TaskDTO?>
    suspend fun updateStatusTask(taskId: Int, statusId: Int, nameTask: String)
    suspend fun updateDescriptionTask(taskId: Int, description: String)
    suspend fun DeleteTaskOrProject(taskId: Int)
    suspend fun fetchDescription(descrId: Int): MutableList<DescriptionDTO?>
    suspend fun registerUser(user: RegisterReceiveRemote)
    suspend fun createManHours(manHour: ManHoursDTO, taskId: Int): Boolean
    suspend fun fetchManHours(taskId: Int): MutableList<ManHoursDTO?>
    suspend fun fetchActivity(): MutableList<ActivityDTO?>
    // Извлечение всех пользователей (используется для удаления)
    suspend fun fetchAllPerson(): MutableList<PersonDTO?>
    // Извлечение всех пользователей, которые привязаны к задача
    suspend fun fetchPersonInTask(taskId: Int): MutableList<PersonDTO?>
    // Извлечение всех пользователей, которые привязаны к проекту
    suspend fun fetchPersonInProject(taskId: Int): MutableList<PersonDTO?>
    // Извлечение всех пользователей, которые еще не были добавлены в проект
    suspend fun fetchPersonFreeFromProject(projId: Int): MutableList<PersonDTO?>
    // Извлечение пользователей, которые еще не были добавлены к задаче
    suspend fun fetchPersonFreeForTask(projId: Int): MutableList<PersonDTO?>
    suspend fun linkUserTaskOrProject(urp: UserRoleProjectDTO): Boolean
    suspend fun deletePersonFromSystem(personId: MutableList<Int>)
    // Обновление затрачиваемых часов для задачи
    suspend fun updateHoursSpent(urp: UserRoleProjectDTO, taskId: Int): Boolean
    // Обновление оценки часов для выполения задачи
    suspend fun updateTimeEstimation(taskDTO: TaskDTO, taskId: Int): Boolean
    // Список задач для зависимости
    suspend fun fetchTaskForDependence(projId: Int, taskId: Int): MutableList<TaskDTO?>
    // Добавление зависимости
    suspend fun addDependenceForTask(taskDependent: Int, taskdependsOn: Int): Boolean
    // Получение отчета календарного плана по опредленному проекту
    suspend fun fetchCalenderPlan(projectId: Int): MutableList<CalendarPlan?>
    // Получение отчета трудозатрат по опредленному проекту
    suspend fun fetchReportManHours(projectId: Int): MutableList<ManHoursReportDTO?>
    // Удаление зависимости
    suspend fun deleteDependence(dependenceOn: Int): Boolean
}

