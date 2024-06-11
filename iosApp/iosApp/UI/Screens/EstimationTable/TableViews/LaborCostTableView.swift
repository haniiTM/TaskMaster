//
//  LaborCostTableView.swift
//  TaskMaster
//
//  Created by  user on 11-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct LaborCostTableView: View {
    let projectId: UInt16
    @ObservedObject var viewModel: EstimationTableViewModel

    var body: some View {
        let laborCostList = viewModel.laborCostReportList

        let dateList = laborCostList.compactMap { $0.createdAt?.toGanttDate() }
        let uniqueDateList = Array(Set(dateList)).sorted()

        let uniqueTaskIdList = Array(Set(laborCostList.compactMap { $0.taskId }))
        let laborList = uniqueTaskIdList.map { taskId  in
            let hoursSpent = laborCostList.first { $0.taskId == taskId }?.hoursSpent ?? "-"
            return (taskId, hoursSpent)
        }.sorted { $0.0 < $1.0 }

        let hourList = laborCostList.map { report in
            return (report.createdAt?.toGanttDate() ?? .init(), report.hoursSpent ?? "-", report.taskId)
        }

        EstimationTableViewTemplate(soloData: uniqueDateList,
                                    pairData: laborList,
                                    tripleData: hourList)
        { await viewModel.updateLaborCostReportList(projectId) }
    }
}
