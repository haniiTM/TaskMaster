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
    @ObservedObject var ganttViewModel = EstimationTableViewModel()
    let projectId: UInt16

    var body: some View {
        let ganttList = ganttViewModel.ganttList

        let ganttDatesAsString = ganttList
            .flatMap { $0.execution_date }

        let ganttDatesAsDates = ganttDatesAsString
            .map { ($0 as? String ?? .init()).toGanttDate() }
            .sorted()

        let ganttValue = ganttList
            .map { ($0.taskId, $0.haveExecuter) }
            .sorted(by: { $0.0 < $1.0 })
        //            .distinct()

        let hoursGanttData = ganttList.flatMap { task in
            task.execution_date.map { dateString in
                let date = dateString as? String ?? .init()
                return (date.toGanttDate(), task.haveExecuter, task.taskId)
            }
        }

        ScrollView(.horizontal) {
            LazyHStack {
                LazyVStack(spacing: 0) {
                    TableHeader(dateList: ganttDatesAsDates)

                    ForEach(ganttValue, id: \.0) { rowData in
                        TableRow(ganttData: rowData,
                                 dateList: ganttDatesAsDates,
                                 hoursGanttData: hoursGanttData)
                    }
                }
            }
        }
        .task {
            await ganttViewModel.updateGanttList(projectId)
        }
    }
}

struct TableHeader: View {
    let dateList: [Date]

    var body: some View {
        HStack(spacing: 0) {
            Group {
                TableHeaderCell(text: "Задача/ \nДата")

                ForEach(dateList, id: \.self) { date in
                    TableHeaderCell(text: date.toGanttString())
                }
            }
            .frame(width: 100, height: 50)
            .padding(8)
            .border(.primary)
        }
    }
}

struct TableRow: View {
    let ganttData: (Int32, Bool)
    let dateList: [Date]
    let hoursGanttData: [(Date, Bool, Int32)]

    var body: some View {
        HStack(spacing: 0) {
            Group {
                FirstTableDataCell(text: ganttData.0.description)

                ForEach(dateList, id: \.self) { date in
                    let matchingEntry = hoursGanttData.first { $0.0 == date && $0.2 == ganttData.0 }
                    let matchingHourColor: Color = (matchingEntry?.1 == true) ? .green : .white

                    TableDataCell(backgroundColor: matchingHourColor)
                }
            }
            .frame(width: 100, height: 50)
            .padding(8)
            .border(.primary)
        }
    }
}

struct TableHeaderCell: View {
    let text: String

    var body: some View {
        Text(text)
    }
}

struct FirstTableDataCell: View {
    let text: String

    var body: some View {
        Text(text)
    }
}

struct TableDataCell: View {
    let backgroundColor: Color

    var body: some View {
        Rectangle()
            .fill(backgroundColor)
    }
}
