//
//  TaskInfoViewModel.swift
//  TaskMaster
//
//  Created by  user on 18-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class TaskInfoViewModel: ObservableObject, Searchable {
    func search() async {
        
    }

    //    MARK: Props
    private let taskInfoUseCase = KoinHelper().getTaskInfoUseCase()
    @Published private(set) var taskInfo = DetailedTaskInfo()

    //    MARK: Methods
    func updateDataSource(_ id: UInt16) async {
        do {
            guard
                let optionalTaskInfo = try await taskInfoUseCase.getTaskInfo(taskId: id)
            else { return }

            taskInfo = .init(optionalTaskInfo)
        } catch {
            print(error.localizedDescription)
        }
    }
}
