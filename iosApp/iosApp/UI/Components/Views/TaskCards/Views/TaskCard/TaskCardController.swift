//
//  TaskCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

final class TaskCardController: TaskCardControllerProtocol {
    //    MARK: Props
    let model: any TaskInfoProtocol
    private let viewModel: TaskCardViewModelProtocol

    //    MARK: Init
    init(model: any TaskInfoProtocol, viewModel: TaskCardViewModelProtocol) {
        self.model = model
        self.viewModel = viewModel
    }

    //    MARK: Methods
    func open() {}

    func remove() {}
}
