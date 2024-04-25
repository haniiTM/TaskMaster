//
//  ProjectListViewModelProtocol.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

protocol ProjectListViewModelProtocol: AnyObject {
    //    MARK: Props
    var projectListSignal: Box<[TaskInfo]?> { get }

    //    MARK: Methods
    func updateDataSource()
}
