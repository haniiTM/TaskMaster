//
//  ViewModelProtocols.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol Searchable {
    func search() async
}

protocol UserSearchable {
    func searchUser() async
}
