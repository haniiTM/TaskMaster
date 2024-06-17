//
//  TaskCardView.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskCardView: View {
    //    MARK: Props
    private let controller: any TaskCardControllerProtocol

    //    MARK: Init
    /// Initializes the view with specified controller.
    /// - Parameters:
    ///   - controller: An object conforming to the TaskCardControllerProtocol that will serve as the controller for this view.
    init(controller: any TaskCardControllerProtocol) {
        self.controller = controller
    }

    /// Initializes the view with default realization.
    init(_ parentId: UInt16, 
         _ isPendingTaskAlertPresented: Binding<Bool>,
         _ model: any TaskInfoProtocol,
         _ stateManager: any CardDeletionAlertPresentable,
         _ viewModel: any TaskCardViewModelProtocol) {
        controller = TaskCardController(parentId,
                                        isPendingTaskAlertPresented,
                                        model,
                                        stateManager,
                                        viewModel)
    }

    //    MARK: Body
    var body: some View {
        ProjectCardView(controller: controller, contextItems: { ContextItem })
    }

    private var ContextItem: some View {
        Button(
            action: {
                Task { await controller.changeStatus() }
            }
        ) {
            Label(controller.isCompleted ? "Продолжить" : "Выполнить",
                  systemImage: controller.isCompleted ? "checkmark.circle.badge.xmark" : "checkmark.circle")
        }
    }
}
