//
//  ViewModelProtocols.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

protocol ProjectCardViewModelProtocol: ObservableObject {
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

protocol UserListable: ObservableObject {
    var userListSignal: [PersonDTO] { get }
}

protocol UserAppendable {
    func addUser(_ parentId: UInt16, userIdList: [KotlinInt]) async
}

protocol UserDeletable: ObservableObject {
    func deleteUser(_ userId: UInt16, parentId: UInt16) async
}

protocol ProjectUserListUpdater {
    func updateUserList() async
}

protocol TaskUserListUpdater: ObservableObject {
    func updateUserList(_ parentId: UInt16) async
}
