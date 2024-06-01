//
//  ProjectCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class ProjectCardController: ProjectCardControllerProtocol {
    //    MARK: Props
    private var viewModel: any ProjectCardViewModelProtocol
    let model: any TaskInfoProtocol

    //    MARK: Init
    init(_ projectId: UInt16, model: any TaskInfoProtocol, viewModel: any ProjectCardViewModelProtocol) {
        self.model = model
        self.viewModel = viewModel
    }

    //    MARK: Methods
    func open() {}

    func remove() async {
        Task {
            await viewModel.deleteCard(model.id)
        }
    }
}
