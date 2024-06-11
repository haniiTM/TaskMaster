package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.CalendarPlan
import com.example.taskmaster.data.network.models.ManHoursReportDTO
import org.koin.core.component.KoinComponent

class EstimationTableUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getGanttReport(projectId: Int): MutableList<CalendarPlan?> {
        return apiService.fetchCalenderPlan(projectId)
    }

    suspend fun getLaborCostReport(projectId: Int): MutableList<ManHoursReportDTO?> {
        return apiService.fetchReportManHours(projectId)
    }
}