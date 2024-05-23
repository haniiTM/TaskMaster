//
//  TaskCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

final class TaskCardController: TaskCardControllerProtocol {
    //    MARK: Props
    private let parentId: UInt16
    let model: any TaskInfoProtocol
    private let viewModel: TaskCardViewModelProtocol

    //    MARK: Init
    init(_ projectId: UInt16, model: any TaskInfoProtocol, viewModel: TaskCardViewModelProtocol) {
        parentId = projectId
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
}
