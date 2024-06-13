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

    @State private var isSearching = false
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
            .task {
                await viewModel.updateDataSource(taskId)
            }
            .sheet(isPresented: $stateManager.isInfoAlertShown) {
                LaborCostInfoAlert(model, stateManager: stateManager)
            }
            .searchable(text: $searchText,
                        placement: .navigationBarDrawer(displayMode: .always))
    }

    private var ViewBody: some View {
        //        ProjectFrameView(title) {
        List(filteredItems, id: \.id) { laborCost in
            Button(laborCost.comment ?? "Трудозатрата \(laborCost.id ?? 0)") {
                model = laborCost
                stateManager.isInfoAlertShown.toggle()
            }
            .padding(8)
            .tint(.primary)
        }.navigationTitle(projectTitle)
        //            .padding()
        //        }
    }
}
