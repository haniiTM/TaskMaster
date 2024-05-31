//
//  ViewModelProtocols.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol ProjectCardViewModelProtocol {
    func deleteCard(_ id: UInt16) async
    func updateDataSource(_ parentId: UInt16) async
}

protocol TaskCardViewModelProtocol: ProjectCardViewModelProtocol {
    func updateTaskStatus(_ id: UInt16, title: String, statusId: UInt8) async
}

protocol Searchable {
    func search() async
}

protocol UserSearchable {
    func searchUser() async
}
