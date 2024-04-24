//
//  SubTaskListViewModelProtocol.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol SubTaskListViewModelProtocol: AnyObject {
    //    MARK: Props
    var subTaskListSignal: Box<[TaskInfo]?> { get }

    //    MARK: Methods
    func updateDataSource()
}
