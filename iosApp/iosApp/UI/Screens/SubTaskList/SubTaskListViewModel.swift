//
//  SubTaskListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

final class SubTaskListViewModel: SubTaskListViewModelProtocol, ObservableObject {
    //    MARK: Props
    var subTaskListSignal: Box<[TaskInfo]?> = .init(nil)
    private let model = SubTaskListModel()

    func updateDataSource() {
        subTaskListSignal.value = model.subTaskList
    }
}
