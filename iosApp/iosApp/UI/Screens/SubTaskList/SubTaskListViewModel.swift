//
//  SubTaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class SubTaskListViewModel: ObservableObject, SubTaskListViewModelProtocol {
    //    MARK: Props
    private let subTaskListUseCase = KoinHelper().getTaskListUseCase()
    @Published private(set) var unCompletedSubTaskListSignal = [TaskInfo]()
    @Published private(set) var completedSubTaskListSignal = [TaskInfo]()
    @Published private(set) var categoryListSignal = [TypeOfActivityDTO]()

    //    MARK: Methods
    func updateDataSource(_ id: UInt16) async {
        do {
            guard
                let optionalUnCompletedSubTaskList = try await subTaskListUseCase.getUncompletedTaskList(idProj: id) as? [TaskDTO?],
                let optionalCompletedSubTaskList = try await subTaskListUseCase.getCompletedTaskList(idProj: id) as? [TaskDTO?]
            else { return }

            unCompletedSubTaskListSignal = optionalUnCompletedSubTaskList.decodedDtoList()
            completedSubTaskListSignal = optionalCompletedSubTaskList.decodedDtoList()
        } catch {
            print(error.localizedDescription)
        }
    }

    func getCategoryList() async {
        do {
            guard
                let optionalCategoryList = try await subTaskListUseCase.getTaskCategoryList() as? [TypeOfActivityDTO?]
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
            try await subTaskListUseCase.createTask(task: taskDto, parentId: Int32(parentId))

            await updateDataSource(parentId)
        } catch {
            print(error.localizedDescription)
        }
    }

    func deleteCard(_ id: UInt16) async {
        do {
            try await subTaskListUseCase.deleteTask(id: Int32(id))
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateTaskStatus(_ id: UInt16, title: String, statusId: UInt8) async {
        do {
//            try await taskListUseCase.updateStatus(taskId: .init(id),
//                                                   statusId: .init(statusId),
//                                                   nameTask: title)
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
