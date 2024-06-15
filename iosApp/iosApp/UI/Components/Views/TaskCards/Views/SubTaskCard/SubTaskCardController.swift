//
//  SubTaskCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

final class SubTaskCardController: SubTaskCardControllerProtocol {
    //    MARK: Props
    private let viewModel: any TaskCardViewModelProtocol
    var stateManager: any CardDeletionAlertPresentable
    let model: any TaskInfoProtocol

    private let parentId: UInt16
    var isCompleted: Bool {
        model.statusId == 1
    }
    var cardDeletionAlertTitle = "задачи"

    //    MARK: Init
    init(_ projectId: UInt16,
         _ model: any TaskInfoProtocol,
         _ stateManager: any CardDeletionAlertPresentable,
         _ viewModel: any TaskCardViewModelProtocol)
    {
        parentId = projectId
        self.model = model
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    //    MARK: Methods
    func remove() async {
        Task {
            await viewModel.deleteCard(model.id)
            await viewModel.updateDataSource(parentId)
        }
    }

    func changeStatus() async {
        Task {
            var statusId: UInt8 = 3
            if model.statusId == 1 || model.statusId == 2 {
                statusId = model.statusId == 1 ? 2 : 1
            }

            await viewModel.updateTaskStatus(model.id,
                                             title: model.title,
                                             statusId: statusId)
            await viewModel.updateDataSource(parentId)
        }
    }

    func open() {}
}
