//
//  ProjectListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

@MainActor final class ProjectListViewModel: ObservableObject {
    //    MARK: Props
    private let projectListUseCase = KoinHelper().getProjectListUseCase()
    @Published private(set) var projectList = [TaskInfo]()

    //    MARK: Methods
    func updateDataSource() async {
        //        Task {
        do {
            guard
                let taskList = try await projectListUseCase.getProjectList() as? [TaskDTO?]
            else { return }

            var list = [TaskInfo]()
            taskList.forEach { task in
                guard let task = task else { return }

                let project = TaskInfo(id: task.id as? UInt8 ?? 0,
                                       title: task.name ?? "Нет названия",
                                       timerValue: task.scope as? UInt8 ?? 0,
                                       isUrgent: false,
                                       participiantsValue: task.userCount as? UInt8)
                list.append(project)
            }

            projectList = list
        } catch {
            print(error.localizedDescription)
        }
        //        }
    }
}
