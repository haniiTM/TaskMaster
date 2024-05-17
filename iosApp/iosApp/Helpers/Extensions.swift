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
