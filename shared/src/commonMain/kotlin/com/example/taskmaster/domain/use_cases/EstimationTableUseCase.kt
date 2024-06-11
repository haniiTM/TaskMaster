package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.CalendarPlan
import org.koin.core.component.KoinComponent

class EstimationTableUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getCalendarPlanList(projectId: Int): MutableList<CalendarPlan?> {
        return apiService.fetchCalenderPlan(projectId)
    }
}