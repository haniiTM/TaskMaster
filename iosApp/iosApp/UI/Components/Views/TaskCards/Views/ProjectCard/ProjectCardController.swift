//
//  ProjectCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class ProjectCardController: ProjectCardControllerProtocol, CardDeletionAlertTitleProvider {
    //    MARK: Props
    private var viewModel: any ProjectCardViewModelProtocol
    var stateManager: any CardDeletionAlertPresentable
    let model: any TaskInfoProtocol

    var cardDeletionAlertTitle = "проекта"

    //    MARK: Init
    init(_ projectId: UInt16,
         _ model: any TaskInfoProtocol,
         _ stateManager: any CardDeletionAlertPresentable,
         _ viewModel: any ProjectCardViewModelProtocol)
    {
        self.model = model
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    //    MARK: Methods
    func remove() async {
        Task {
            await viewModel.deleteCard(model.id)
        }
    }

    func open() {}
}
