//
//  EstimationTableView.swift
//  TaskMaster
//
//  Created by evilgen on 25.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct EstimationTableView: View {
    //    MARK: Props
    @StateObject private var viewModel = EstimationTableViewModel()
    @StateObject private var stateManager = EstimationTableStateManager()

    private let projectId: UInt16
    private let projectTitle: String

    //    MARK: Init
    init(_ model: ProjectInfo) {
        projectId = model.id
        projectTitle = model.title
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(projectTitle,
                         stateManager: stateManager,
                         viewModel: viewModel) {
            CalendarSection(title: "Календарный план") {
                GanttTableView(projectId: projectId, viewModel: viewModel)
            }

            CalendarSection(title: "Расчет трудозатрат") {
                LaborCostTableView(projectId: projectId, viewModel: viewModel)
            }
        }
    }
}

struct CalendarSection<Content: View>: View {
    let title: String
    let content: () -> Content

    var body: some View {
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

            content()
        }.padding(.horizontal)
    }
}
