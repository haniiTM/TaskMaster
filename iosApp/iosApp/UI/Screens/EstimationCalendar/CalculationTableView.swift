//
//  CalculationTableView.swift
//  TaskMaster
//
//  Created by  user on 09-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct CalculationTableView: View {
    //    @ObservedObject var laborCostViewModel: ManHoursViewModel
    //    @ObservedObject var ganttViewModel: UserroleprojectViewModel
    //    @ObservedObject var testViewModel: UserroleprojectViewModel
    //    let id: Int?

    let ganttDatesAsDates: [Date]
    let ganttValue: [(Int, Bool)]
    let hoursGanttData: [(Date, String, Int)]

    var body: some View {
        //        let gantt = ganttViewModel.state.value.itemState
        //        let ganttDatesAsString = gantt.compactMap { $0 }.flatMap { $0.execution_date }
        //        let ganttDatesAsDates = ganttDatesAsString.compactMap { $0.toGanttDate() }

        //        let ganttValue = gantt.map {
        //            ($0?.taskId ?? -1, $0?.haveExecuter ?? false)
        //        }.sorted(by: { $0.0 < $1.0 }).unique()
        //
        //        let hoursGanttData = gantt.flatMap { task in
        //            task?.execution_date.map { date in
        //                (date.toGanttDate(), task.haveExecuter?.description, task.taskId)
        //            } ?? []
        //        }

        ScrollView(.horizontal) {
            LazyHStack {
                LazyVStack(spacing: 0) {
                    TableHeader(dates: ganttDatesAsDates)

                    ForEach(ganttValue, id: \.0) { rowData in
                        TableRow(ganttData: rowData,
                                 dates: ganttDatesAsDates,
                                 hoursGanttData: hoursGanttData)
                    }
                }
            }
        }
        //        .onAppear {
        //            if let id = id {
        //                laborCostViewModel.getReportManHours(id: id)
        //                testViewModel.getCalendarPlan(id: id)
        //                ganttViewModel.getCalendarPlan(id: id)
        //            }
        //        }
    }
}

struct TableHeader: View {
    let dates: [Date]

    var body: some View {
        let uniqueDates = dates.compactMap { $0 }.sorted()

        HStack(spacing: 0) {
            Group {
                TableHeaderCell(text: "Задача/ \nДата")

                ForEach(uniqueDates, id: \.self) { date in
                    TableHeaderCell(text: formatDate(date))
                }
            }
            .frame(width: 100, height: 50)
            .padding(8)
            .border(.primary)
        }
    }
}

struct TableRow: View {
    let ganttData: (Int, Bool)
    let dates: [Date]
    let hoursGanttData: [(Date, String, Int)]

    var body: some View {
        let uniqueDates = dates.compactMap { $0 }.sorted()

        HStack(spacing: 0) {
            Group {
                FirstTableDataCell(text: "\(ganttData)")

                ForEach(uniqueDates, id: \.self) { date in
                    let matchingEntry = hoursGanttData.first { $0.0 == date && $0.2 == ganttData.0 }
                    let matchingHourColor: Color = (matchingEntry?.1 == "true") ? .green : .white

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

func formatDate(_ date: Date) -> String {
    let formatter = DateFormatter()
    formatter.dateFormat = "dd/MM/yy"
    return formatter.string(from: date)
}

//extension String {
//    func toGanttDate() -> Date? {
//        let formatter = DateFormatter()
//        formatter.dateFormat = "yyyy-MM-dd"
//        return formatter.date(from: self)
//    }
//}
//
//extension Array where Element: Hashable {
//    func unique() -> [Element] {
//        var seen = Set<Element>()
//        return filter { seen.insert($0).inserted }
//    }
//}
