//
//  ProjectCardView.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectCardView: View {
    //    MARK: Props
    private let cardType: CardType
    private let controller: any ProjectCardControllerProtocol
    private let leadingAction: () -> Void
    private let trailingAction: () -> Void

    //    MARK: Init
    init(_ model: any TaskInfoProtocol,
         _ stateManager: any CardDeletionAlertPresentable,
         _ viewModel: any ProjectCardViewModelProtocol)
    {
        controller = ProjectCardController(model.id,
                                           model,
                                           stateManager,
                                           viewModel)
        cardType = .project
        leadingAction = {}
        trailingAction = controller.showDeletionAlert
    }

    init(_ cardType: CardType,
         _ controller: any ProjectCardControllerProtocol,
         leadingAction: @escaping () -> Void,
         trailingAction: @escaping () -> Void)
    {
        self.cardType = cardType
        self.controller = controller
        self.leadingAction = leadingAction
        self.trailingAction = trailingAction
    }

    //    MARK: Body
    var body: some View {
        projectCard
            .alert(isPresented: controller.isCardDeletionAlertPresentedBinding) {
                DestructiveAlertTemplate("Удаление " + controller.cardDeletionAlertTitle)
                {
                    Task { await controller.remove() }
                } secondaryButtonAction: {
                    controller.hideDeletionAlert()
                }.body
            }
    }

    private var projectCard: some View {
        TemplateTaskCardView(cardType) {
            leadingAction()
        } trailingAction: {
            trailingAction()
        } content: {
            ViewBody
        }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Text(controller.taskTitle)
            .multilineTextAlignment(.leading)

        HStack {
            Text(controller.timerTitle)
                .font(.subheadline)
                .multilineTextAlignment(.leading)

            Spacer()
        }

        Text(controller.participiantsTitle)
            .font(.subheadline)
    }

    @ViewBuilder
    private var ContextItem: some View {
//        contextItems()

        Button(
            role: .destructive,
            action: {
                controller.showDeletionAlert()
            }
        ) {
            Label("Удалить", systemImage: "trash")
        }
    }
}
