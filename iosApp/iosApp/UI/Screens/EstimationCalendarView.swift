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
    private let projectId: UInt16
    private let projectTitle: String
    private let viewModel: Searchable

    //    MARK: Init
    init(_ model: ProjectInfo, viewModel: Searchable) {
        projectId = model.id
        projectTitle = model.title
        self.viewModel = viewModel
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(projectTitle, viewModel: viewModel) {
            CalendarSection("Календарный план")
            CalendarSection("Расчет трудозатрат")
        }
    }

    //    MARK: Methods
    private func CalendarSection(_ title: String) -> some View {
        VStack(spacing: 24) {
            Text(title).font(.title3)

            HStack(spacing: 32) {
                Button(action: {}) {
                    Text("Выбор таблицы")
                        .font(.subheadline)
                        .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)

                    Spacer()

                    Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
                }
                .padding(8)
                .tint(.primary)
                .background(
                    .ultraThinMaterial,
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )

                Button(action: {}) {
                    Text("Скачать")
                        .font(.subheadline)
                        .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)

                    Image(systemName: "square.and.arrow.down")
                }
                .padding(8)
                .tint(.white)
                .background(
                    .tint,
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
