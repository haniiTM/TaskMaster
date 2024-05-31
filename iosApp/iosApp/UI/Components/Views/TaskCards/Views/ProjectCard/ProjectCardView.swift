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
    private let controller: ProjectCardControllerProtocol
    @ViewBuilder private let contextItems: () -> ContextItems

    //    MARK: Init
    init(model: any TaskInfoProtocol,
         viewModel: ProjectCardViewModelProtocol,
         @ViewBuilder contextItems: @escaping () -> ContextItems) {
        controller = ProjectCardController(model.id, model: model, viewModel: viewModel)
        self.contextItems = contextItems
    }

    init(controller: ProjectCardControllerProtocol,
         @ViewBuilder contextItems: @escaping () -> ContextItems) {
        self.controller = controller
        self.contextItems = contextItems
    }

    //    MARK: Body
    var body: some View {
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
                Task { await controller.remove() }
            }
        ) {
            Label("Удалить", systemImage: "trash")
        }
    }
}
