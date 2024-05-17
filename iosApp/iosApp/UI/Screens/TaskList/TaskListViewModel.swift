//
//  TaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class TaskListViewModel: ObservableObject {
    //    MARK: Props
    private let taskListUseCase = KoinHelper().getTaskListUseCase()
    @Published private(set) var unCompletedTaskListSignal = [TaskInfo]()
    @Published private(set) var completedTaskListSignal = [TaskInfo]()

    //    MARK: Methods
    func updateDataSource(_ id: UInt8) async {
        do {
            guard
                let optionalUnCompletedTaskList = try await taskListUseCase.getUncompletedTaskList(idProj: id) as? [TaskDTO?],
                let optionalCompletedTaskList = try await taskListUseCase.getCompletedTaskList(idProj: id) as? [TaskDTO?]
            else { return }

            unCompletedTaskListSignal = optionalUnCompletedTaskList.decodedDtoList()
            completedTaskListSignal = optionalCompletedTaskList.decodedDtoList()
        } catch {
            print(error.localizedDescription)
        }
    }

    func addUncompletedTask() {

        //        updateDataSource()
    }

    func addCompletedTask() {

        //        updateDataSource()
    }
}
