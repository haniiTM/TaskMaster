//
//  ProjectCardView.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectCardView<ContextItems: View>: View {
    //    MARK: Props
    private let controller: any ProjectCardControllerProtocol
    @ViewBuilder private let contextItems: () -> ContextItems

    //    MARK: Init
    init(_ model: any TaskInfoProtocol,
         _ stateManager: any CardDeletionAlertPresentable,
         _ viewModel: any ProjectCardViewModelProtocol,

         @ViewBuilder contextItems: @escaping () -> ContextItems)
    {
        controller = ProjectCardController(model.id,
                                           model,
                                           stateManager,
                                           viewModel)

        self.contextItems = contextItems
    }

    init(controller: any ProjectCardControllerProtocol,
         @ViewBuilder contextItems: @escaping () -> ContextItems) 
    {
        self.controller = controller
        self.contextItems = contextItems
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
        TemplateTaskCardView {
            ViewBody
        } contextItems: {
            ContextItem
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
        contextItems()

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
