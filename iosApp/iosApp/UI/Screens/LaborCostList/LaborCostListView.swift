//
//  LaborCostListView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct LaborCostListView: View {
    //    MARK: Props
    @StateObject private var viewModel = LaborCostListViewModel()
    @StateObject private var stateManager = LaborCostListStateManager()
    @State private var model: ManHoursDTO

    private let taskId: UInt16
    private let projectTitle: String

    @State private var searchText = ""
    private var filteredItems: [ManHoursDTO] {
        searchText.isEmpty
        ? viewModel.laborCostList
        : viewModel.laborCostList
            .filter {
                $0.comment?.localizedCaseInsensitiveContains(searchText)
                ?? false
            }
    }

    //    MARK: Init
    init(_ projectTitle: String, taskId: UInt16) {
        self.taskId = taskId
        self.projectTitle = projectTitle
        model = .init(id: nil,
                      created_at: nil,
                      hours_spent: nil,
                      comment: nil,
                      taskid: nil,
                      projectid: nil,
                      activityid: nil)
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .navigationTitle(projectTitle)
            .navigationBarBackButtonHidden(true)
            .task { await updateDataSource() }
            .refreshable {
                Task { await updateDataSource() }
            }
            .sheet(isPresented: $stateManager.isInfoAlertShown) {
                LaborCostInfoAlert(taskId,
                                   model,
                                   stateManager,
                                   viewModel)
            }
    }

    private var ViewBody: some View {
        ProjectFrameView(projectTitle,
                         stateManager,
                         $searchText)
        { itemList }
    }

    private var itemList: some View {
        VStack(spacing: 0) {
            ForEach(filteredItems, id: \.id) { laborCost in
                VStack(spacing: 0) {
                    Button {
                        model = laborCost
                        stateManager.isInfoAlertShown.toggle()
                    } label: {
                        Text(laborCost.comment ?? "Трудозатрата \(laborCost.id ?? 0)")
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(8)
                            .background(.white)
                    }

                    Divider()
                }
            }
        }
        .background(
            .white,
            in: RoundedRectangle(cornerRadius: 15, style: .continuous)
        )
    }

    private func updateDataSource() async {
        await viewModel.updateDataSource(taskId)
    }
}
