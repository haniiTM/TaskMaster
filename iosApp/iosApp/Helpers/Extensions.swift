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

extension String {
    func formatInput() -> String {
        var filtered = self.filter { "0123456789".contains($0) }

        if filtered.countOccurrences(of: ":") > 1 {
            filtered.removeLast()
        }

        if filtered.count > 2 && !filtered.contains(":") {
            filtered.insert(":", at: filtered.index(filtered.startIndex, offsetBy: 2))
        }

        if filtered.count > 5 {
            filtered = String(filtered.prefix(5))
        }

        let components = filtered.split(separator: ":")
        if components.count == 2 {
            let part1 = String(components[0]).prefix(2)
            let part2 = String(components[1]).prefix(2)
            return "\(part1):\(part2)"
        } else if components.count == 1 && filtered.count > 2 {
            let part1 = String(components[0]).prefix(2)
            return "\(part1):"
        }

        return filtered
    }

    func countOccurrences(of character: Character) -> Int {
        return self.filter { $0 == character }.count
    }
}

extension Date {
    func toGanttString() -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd/MM/yy"
        return formatter.string(from: self)
    }
}

extension KotlinByteArray {
    convenience init(_ data: Data) {
        let array = data.map { NSNumber(value: $0) }

        self.init(size: .init(data.count))

        for (i, v) in array.enumerated() {
            self.set(index: .init(i),
                     value: v.int8Value)
        }
    }
}
