//
//  TaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

class TaskListViewModel: TaskListViewModelProtocol, ObservableObject {
    //    MARK: Props
    var taskListSignal: Box<[TaskInfo]?> = .init(nil)
    private let model = TaskListModel()

    func updateDataSource() {
        taskListSignal.value = model.taskList
    }
}
