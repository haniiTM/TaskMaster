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
    @Published private(set) var projectList = [ProjectInfo]()

    //    MARK: Methods
    func updateDataSource() async {
        do {
            guard
                let taskList = try await projectListUseCase.getProjectList() as? [TaskDTO?]
            else { return }

            projectList = taskList.decodedDtoList()
        } catch {
            print(error.localizedDescription)
        }
    }
}
