//
//  TaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

final class TaskListViewModel: TaskListViewModelProtocol, ObservableObject {
    //    MARK: Props
    @Published var unCompletedTaskListSignal: Box<[TaskInfo]?> = .init(nil)
    @Published var completedTaskListSignal: Box<[TaskInfo]?> = .init(nil)
    private var model = TaskListModel()

    //    MARK: Methods
    func updateDataSource() {
        unCompletedTaskListSignal.value = model.uncompletedTaskList
        completedTaskListSignal.value = model.completedTaskList
    }

    func addUncompletedTask() {
        model.addUncompletedTask()
        updateDataSource()
    }

    func addCompletedTask() {
        model.addCompletedTask()
        updateDataSource()
    }
}
