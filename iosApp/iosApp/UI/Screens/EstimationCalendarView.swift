//
//  EstimationCalendarView.swift
//  TaskMaster
//
//  Created by evilgen on 25.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct EstimationCalendarView: View {
    //    MARK: Props
    private let projectTitle: String

    //    MARK: Init
    init(_ projectTitle: String) {
        self.projectTitle = projectTitle
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(projectTitle) {
            CalendarSection("Календарный план")
            CalendarSection("Расчет трудозатрат")
        }
    }

    //    MARK: Methods
    private func CalendarSection(_ title: String) -> some View {
        VStack(spacing: 24) {
            Text(title).font(.title3)

            HStack(spacing: 32) {
                Group {
                    Button(action: {}) {
                        Text("Выбор таблицы")

                        Spacer()

                        Image(systemName: "arrow.down.circle")
                    }

                    Button(action: {}) {
                        Text("Скачать")

                        Image(systemName: "square.and.arrow.down")
                    }
                }
                .padding(8)
                .border(Color.secondary)
            }

            Text("Calendar")
                .font(.title)
                .frame(maxWidth: .infinity)
                .padding(100)
                .background(.secondary)
        }.padding(.horizontal)
    }
}
