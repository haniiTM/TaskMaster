//
//  TaskCardsProtocols.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

// MARK: - General
protocol TaskCardActions: Openable & Removable {}

protocol CardInfoProtocol {
    var model: TaskInfo { get }

    init(model: TaskInfo)
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
