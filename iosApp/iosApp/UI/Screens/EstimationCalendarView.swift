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
    private let projectId: UInt8
    private let projectTitle: String

    //    MARK: Init
    init(_ model: ProjectInfo) {
        projectId = model.id
        projectTitle = model.title
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(projectTitle) {
            CalendarSection("Календарный план")
            CalendarSection("Расчет трудозатрат")
        }.navigationTitle("Таблицы")
    }

    //    MARK: Methods
    private func CalendarSection(_ title: String) -> some View {
        VStack(spacing: 24) {
            Text(title).font(.title3)

            HStack(spacing: 32) {
                Group {
                    Button(action: {}) {
                        Text("Выбор таблицы")
                            .font(.subheadline)
                            .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)

                        Spacer()

                        Image(systemName: "arrow.down.circle")
                    }.foregroundColor(.primary)

                    Button(action: {}) {
                        Text("Скачать")
                            .font(.subheadline)
                            .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)

                        Image(systemName: "square.and.arrow.down")
                    }.foregroundColor(.primary)
                }
                .padding(8)
                .background(
                    Color(uiColor: .secondarySystemBackground),
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )
            }

            Text("Calendar")
                .font(.title)
                .frame(maxWidth: .infinity)
                .padding(70)
                .background(
                    Color(uiColor: .secondarySystemBackground),
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )
        }.padding(.horizontal)
    }
}
