//
//  TableComponents.swift
//  TaskMaster
//
//  Created by  user on 11-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

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
            .background(
                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
        }
    }
}

struct TableRow: View {
    let data: (Int32, String)
    let dateList: [Date]
    let hourDataList: [(Date, String, Int32)]

    let projectTitle: String
    let projectId: UInt16
    let taskId: UInt16

    var body: some View {
        HStack(spacing: 0) {
            FirstTableDataCell(text: data.0.description,
                               projectTitle: projectTitle,
                               projectId: projectId,
                               taskId: taskId)

            ForEach(dateList, id: \.self) { date in
                let matchingEntry = hourDataList.first { $0.0 == date && $0.2 == data.0 }
                let matchingHour = matchingEntry?.1 ?? "-"
                let matchingHourColor: Color = (matchingEntry?.1 == "true") ? .green : .white

                TableDataCell(text: matchingHour, bgColor: matchingHourColor)
            }
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
    let projectTitle: String
    let projectId: UInt16
    let taskId: UInt16

    var body: some View {
        NavigationLink(destination: TaskInfoView(projectTitle,
                                                 projectId,
                                                 taskId)) {
            Text(text)
                .frame(width: 100, height: 50)
                .padding(8)
                .border(.primary)
                .background(
                    Color(uiColor: .secondarySystemBackground),
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )
        }
        .tint(.primary)
    }
}

struct TableDataCell: View {
    let text: String
    let bgColor: Color

    var body: some View {
        Text(text == "true" ? "" : text)
            .foregroundColor(.black)
            .frame(width: 100, height: 50)
            .padding(8)
            .background(bgColor)
            .border(.primary)
    }
}
