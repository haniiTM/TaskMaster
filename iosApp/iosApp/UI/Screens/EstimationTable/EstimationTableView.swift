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
    @State private var searchText = ""

    private let projectId: UInt16
    private let projectTitle: String

    //    MARK: Init
    init(_ model: ProjectInfo) {
        projectId = model.id
        projectTitle = model.title
    }

    //    MARK: Body
    var body: some View {
        tableView
            .navigationBarBackButtonHidden(true)
            .task { await updateDataSource() }
            .refreshable {
                Task { await updateDataSource() }
            }
    }

    private var tableView: some View {
        ProjectFrameView(projectTitle,
                         stateManager,
                         $searchText) {
            CalendarSection(
                title: "Календарный план"
            ) {
                await viewModel.downloadGanttTable(projectId)
            } content: {
                GanttTableView(projectTitle: projectTitle,
                               projectId: projectId,
                               ganttList: viewModel.ganttReportList)
            }

            CalendarSection(title: "Трудозатраты") {
                await viewModel.downloadLaborCostTable(projectId)
            } content: {
                LaborCostTableView(projectTitle: projectTitle,
                                   projectId: projectId,
                                   laborCostList: viewModel.laborCostReportList)
            }
        }
    }

    private func updateDataSource() async {
        await viewModel.updateGanttList(projectId)
        await viewModel.updateLaborCostReportList(projectId)
    }
}

struct CalendarSection<Content: View>: View {
    let title: String
    let download: () async -> Void
    let content: () -> Content

    var body: some View {
        VStack(spacing: 24) {
            HStack(spacing: 32) {
                Text(title)
                    .font(.title3)
                    .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)
                    .minimumScaleFactor(0.5)
                    .padding(8)
                    .tint(.primary)
                    .background(
                        .white,
                        in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                    )
                    .background(
                        .black,
                        in: RoundedRectangle(cornerRadius: 8, style: .continuous).stroke()
                    )

                Spacer()

                Button(
                    action: {
                        Task { await download() }
                    }
                ) {
                    Text("Скачать")
                        .font(.subheadline)
                        .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)

                    Image(systemName: "square.and.arrow.down")
                }
                .padding(8)
                .foregroundColor(.black)
                .background(
                    .white,
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )
                .background(
                    .black,
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous).stroke()
                )

            }

            content()
        }.padding(.horizontal)
    }
}
