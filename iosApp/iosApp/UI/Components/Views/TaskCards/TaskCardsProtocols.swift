//
//  TaskCardsProtocols.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

// MARK: - General
protocol TaskCardActions: Openable & Removable {}

protocol CardInfoProtocol {
    var model: any TaskInfoProtocol { get }

    //    init(_ projectId: UInt16, model: any TaskInfoProtocol, viewModel: TaskCardViewModelProtocol)
}

protocol TaskTitleProvider: CardInfoProtocol {
    var taskTitle: String { get }
}

protocol TimerTitleProvider: CardInfoProtocol {
    var timerTitle: String { get }
}

protocol UrgencyValueProvider: CardInfoProtocol {
    var isUrgent: Bool { get }
}

protocol UrgentImageNameProvider: CardInfoProtocol {
    var urgentImageName: String { get }
}

// MARK: - Project
protocol ParticipiantsTitleProvider: CardInfoProtocol {
    var participiantsTitle: String { get }
}

// MARK: - Task
protocol TaskNumberTitleProvider: CardInfoProtocol {
    var taskNumberTitle: String { get }
}

protocol CategoriesTitleProvider: CardInfoProtocol {
    var categoriesTitle: String { get }
}

protocol CardDeletionAlertTitleProvider {
    var cardDeletionAlertTitle: String { get }
}

protocol TaskCardDeletionAlertTitleProvider: CardDeletionAlertTitleProvider {}

extension TaskCardDeletionAlertTitleProvider {
    var cardDeletionAlertTitle: String { "задачи"}
}

protocol CardDeletionAlertBindingProvider {
    var stateManager: any CardDeletionAlertPresentable { get }
    var isCardDeletionAlertPresentedBinding: Binding<Bool> { get }
}

extension CardDeletionAlertBindingProvider {
    var isCardDeletionAlertPresentedBinding: Binding<Bool> {
        Binding(
            get: { self.stateManager.isCardDeletionAlertPresented },
            set: { self.stateManager.isCardDeletionAlertPresented = $0 }
        )
    }
}

protocol DestructiveAlertPresentable: CardDeletionAlertBindingProvider {
    func showDeletionAlert()
    func hideDeletionAlert()
}

extension DestructiveAlertPresentable {
    func showDeletionAlert() {
        stateManager.isCardDeletionAlertPresentedBinding.wrappedValue = true
    }

    func hideDeletionAlert() {
        stateManager.isCardDeletionAlertPresentedBinding.wrappedValue = false
    }
}
