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
        }
    }
}

struct TableRow: View {
    let data: (Int32, String)
    let dateList: [Date]
    let hourDataList: [(Date, String, Int32)]

    var body: some View {
        HStack(spacing: 0) {
            Group {
                FirstTableDataCell(text: data.0.description)

                ForEach(dateList, id: \.self) { date in
                    let matchingEntry = hourDataList.first { $0.0 == date && $0.2 == data.0 }
                    let matchingHour = matchingEntry?.1 ?? "-"
                    let matchingHourColor: Color = (matchingEntry?.1 == "true") ? .green : .white

                    TableDataCell(text: matchingHour, bgColor: matchingHourColor)
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
    let text: String
    let bgColor: Color

    var body: some View {
        Text(text == "true" ? "" : text)
            .foregroundColor(.black)
            .padding()
            .background(bgColor)
    }
}
