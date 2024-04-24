//
//  ProjectListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class ProjectListViewModel: ProjectListViewModelProtocol, ObservableObject {
    //    MARK: Props
    var projectListSignal: Box<[TaskInfo]?> = .init(nil)
    private let model = ProjectListModel()

    func updateDataSource() {
        projectListSignal.value = model.projectList
    }
}
