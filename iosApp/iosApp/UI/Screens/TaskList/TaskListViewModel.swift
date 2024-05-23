//
//  TaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class TaskListViewModel: ObservableObject, TaskListViewModelProtocol {
    //    MARK: Props
    private let taskListUseCase = KoinHelper().getTaskListUseCase()
    @Published private(set) var unCompletedTaskListSignal = [TaskInfo]()
    @Published private(set) var completedTaskListSignal = [TaskInfo]()
    @Published private(set) var categoryListSignal = [TypeOfActivityDTO]()

    //    MARK: Methods
    func updateDataSource(_ parentId: UInt16) async {
        do {
            guard
                let optionalUnCompletedTaskList = try await taskListUseCase.getUncompletedTaskList(idProj: parentId) as? [TaskDTO?],
                let optionalCompletedTaskList = try await taskListUseCase.getCompletedTaskList(idProj: parentId) as? [TaskDTO?]
            else { return }

            unCompletedTaskListSignal = optionalUnCompletedTaskList.decodedDtoList()
            completedTaskListSignal = optionalCompletedTaskList.decodedDtoList()
        } catch {
            print(error.localizedDescription)
        }
    }

    func getCategoryList() async {
        do {
            guard
                let optionalCategoryList = try await taskListUseCase.getTaskCategoryList() as? [TypeOfActivityDTO?]
            else { return }

            var categoryList = [TypeOfActivityDTO]()

            optionalCategoryList.forEach { category in
                guard let category = category else { return }

                categoryList.append(category)
            }

            categoryListSignal = categoryList
        } catch {
            print(error.localizedDescription)
        }
    }

    func createTask(_ parentId: UInt16, taskDto: TaskDTO) async {
        do {
            try await taskListUseCase.createTask(task: taskDto, parentId: Int32(parentId))

            await updateDataSource(parentId)
        } catch {
            print(error.localizedDescription)
        }
    }

    func addCompletedTask() {}

    func deleteCard(_ id: UInt16) async {
        do {
            try await taskListUseCase.deleteTask(id: Int32(id))
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
