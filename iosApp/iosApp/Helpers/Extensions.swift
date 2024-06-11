//
//  Extensions.swift
//  TaskMaster
//
//  Created by  user on 17-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

extension Array where Element == TaskDTO? {
    func decodedDtoList() -> [TaskInfo] {
        var taskList = [TaskInfo]()

        self.forEach { dto in
            guard let dto = dto else { return }

            let task = TaskInfo(dto)
            taskList.append(task)
        }

        return taskList
    }
}

extension Array where Element: Equatable {
    func distinct() -> [Element] {
        var result = [Element]()
        for value in self {
            if !result.contains(value) {
                result.append(value)
            }
        }
        return result
    }
}

extension String {
    func getIconByRole() -> String {
        var icon = ""

        switch self {
        case "Backend":
            icon = "terminal"
        case "Frontend":
            icon = "globe"
        case "Тестирование":
            icon = "wrench.and.screwdriver"
        case "UI/UX дизайн":
            icon = "paintbrush.pointed"
        case "Проектный менеджмент":
            icon = "star"
        case "Админ":
            icon = "crown"
        default:
            icon = "exclamationmark.triangle"
        }

        return icon
    }

    func decodeCategory() -> String {
        var categoryTitle = ""

        switch self {
        case "1":
            categoryTitle = "Backend"
        case "2":
            categoryTitle = "Frontend"
        case "3":
            categoryTitle = "Тестирование"
        case "4":
            categoryTitle = "UI/UX дизайн"
        case "5":
            categoryTitle = "Проектный менеджмент"
        default:
            categoryTitle = "-"
        }

        return categoryTitle
    }

    func decodeTaskStatus() -> String {
        var statusTitle = ""

        switch self {
        case "1":
            statusTitle = "Готово"
        case "2":
            statusTitle = "В работе"
        case "3":
            statusTitle = "В ожидании"
        default:
            statusTitle = "-"
        }

        return statusTitle
    }
}

extension String {
    func toGanttDate() -> Date {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        return formatter.date(from: self) ?? .init()
    }
}

extension Date {
    func toGanttString() -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd/MM/yy"
        return formatter.string(from: self)
    }
}
