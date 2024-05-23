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

    //    MARK: Init
    init(_ projectId: UInt16, model: any TaskInfoProtocol, viewModel: any TaskCardViewModelProtocol) {
        self.parentId = projectId
        self.model = model
        self.viewModel = viewModel
    }

    //    MARK: Methods
    func open() {}

    func remove() {}
}
