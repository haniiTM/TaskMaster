package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.ManHoursDTO
import org.koin.core.component.KoinComponent

class LaborCostListUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getLaborCostList(taskId: Int): MutableList<ManHoursDTO?> {
        return apiService.fetchManHours(taskId)
    }

    suspend fun updateLaborCost(laborCostId: Int, laborCostDesc: String) {
        return apiService.updateManHours(laborCostId, laborCostDesc)
    }
}