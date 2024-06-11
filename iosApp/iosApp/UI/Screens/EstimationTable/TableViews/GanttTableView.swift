//
//  CalculationTableView.swift
//  TaskMaster
//
//  Created by  user on 09-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct GanttTableView: View {
    let projectId: UInt16
    @ObservedObject var viewModel: EstimationTableViewModel

    var body: some View {
        let ganttList = viewModel.ganttReportList

        let ganttDatesAsString = ganttList
            .flatMap { $0.execution_date }

        let ganttDatesAsDates = ganttDatesAsString
            .map { ($0 as? String ?? .init()).toGanttDate() }
            .sorted()

        let ganttValue = ganttList
            .map { ($0.taskId, String($0.haveExecuter)) }
            .sorted { $0.0 < $1.0 }

        let hoursGanttData = ganttList.flatMap { task in
            task.execution_date.map { dateString in
                let date = dateString as? String ?? .init()
                return (date.toGanttDate(), String(task.haveExecuter), task.taskId)
            }
        }

        EstimationTableViewTemplate(soloData: ganttDatesAsDates,
                                    pairData: ganttValue,
                                    tripleData: hoursGanttData)
        { await viewModel.updateGanttList(projectId) }
    }
}
