//
//  SubTaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class SubTaskListViewModel: ObservableObject {
    //    MARK: Props
    private let subTaskListUseCase = KoinHelper().getTaskListUseCase()
    @Published private(set) var unCompletedSubTaskListSignal = [TaskInfo]()
    @Published private(set) var completedSubTaskListSignal = [TaskInfo]()

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
}
