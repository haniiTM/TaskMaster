package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class TaskDTO(
    var id: Int?,
    val name: String, // Название проекта, задачи/подзадачи
    var status: Int?, // Id статуса
    val start_date: String?, // Дата начала
    var scope: Int?, // Общее кол-во часов на выполнение
    var description: Int?, // Описание
    var parent: Int?, // Принадлежность к проекту, задаче/подзадаче
    val userCount: Int? = null, // Убрать
    var generation : Int? = 1, // Поколение для индентификации проекта, задачи/подзадачи
    val typeofactivityid: Int?, // Тип активности (front, back  и т.д)
    var position: Int?, // Убрать
    var gruop: Int?, // Убрать
    var dependence: String? // Убрать
)
{
    constructor() : this(
        id = null,
        name = "",
        status = null,
        start_date = null,
        scope = null,
        description = null,
        parent = null,
        userCount = null,
        generation =1,
        typeofactivityid = null,
        position = null,
        gruop = null,
        dependence = null
    )
}
