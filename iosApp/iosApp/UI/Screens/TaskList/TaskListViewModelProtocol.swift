//
//  TaskListViewModelProtocol.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

protocol TaskListViewModelProtocol: AnyObject {
    //    MARK: Props
    var unCompletedTaskListSignal: Box<[TaskInfo]?> { get }
    var completedTaskListSignal: Box<[TaskInfo]?> { get }

    //    MARK: Methods
    func updateDataSource()
    func addUncompletedTask()
    func addCompletedTask()
}
