//
//  SubTaskCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

final class SubTaskCardController: SubTaskCardControllerProtocol {
    //    MARK: Props
    private let parentId: UInt16
    private let viewModel: TaskCardViewModelProtocol
    let model: any TaskInfoProtocol
    var isCompleted: Bool {
        model.statusId == 1
    }

    //    MARK: Init
    init(_ projectId: UInt16, model: any TaskInfoProtocol, viewModel: any TaskCardViewModelProtocol) {
        self.parentId = projectId
        self.model = model
        self.viewModel = viewModel
    }

    //    MARK: Methods
    func open() {}

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
}
