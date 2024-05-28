//
//  LaborCostListView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct LaborCostListView: View {
    //    MARK: Props
    @StateObject private var viewModel = LaborCostListViewModel()
    @StateObject private var stateManager = LaborCostListStateManager()

    private let taskId: UInt16
    private let projectTitle: String

    //    MARK: Init
    init(_ projectTitle: String, taskId: UInt16) {
        self.taskId = taskId
        self.projectTitle = projectTitle
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task {
                await viewModel.updateDataSource(taskId)
            }
            .sheet(isPresented: $stateManager.isInfoAlertShown) {
                LaborCostInfoAlert(stateManager)
            }
    }

    private var ViewBody: some View {
        //        ProjectFrameView(title) {
        List(viewModel.laborCostList, id: \.id) { laborCost in
            Button(laborCost.comment ?? "Трудозатрата \(laborCost.id ?? 0)") {
                stateManager.isInfoAlertShown.toggle()
            }
                .padding(8)
                .tint(.primary)
        }.navigationTitle(projectTitle)
        //            .padding()
        //        }
    }
}
