//
//  ProjectListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

@MainActor final class ProjectListViewModel: ObservableObject, ProjectListViewModelProtocol {
    //    MARK: Props
    private let projectListUseCase = KoinHelper().getProjectListUseCase()

    @Published private(set) var projectList = [TaskInfo]()
    @Published private(set) var userRoleListSignal = [TypeOfActivityDTO]()
    @Published private(set) var userListSignal = [PersonDTO]()
    @Published private(set) var notificationItemList = [NotificationItem]()

    //    MARK: Methods
    func updateDataSource(_ parentId: UInt16) async {
        do {
            guard
                let taskList = try await projectListUseCase.getProjectList() as? [TaskDTO?]
            else { return }

            projectList = taskList.decodedDtoList()
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateUserRoleList() async {
        do {
            guard
                let optionalUserRoleList = try await projectListUseCase.getUserRoleList() as? [TypeOfActivityDTO?]
            else { return }

            var userRoleList = [TypeOfActivityDTO]()

            optionalUserRoleList.forEach { role in
                guard let role = role else { return }

                userRoleList.append(role)
            }

            userRoleListSignal = userRoleList
        } catch {
            print(error.localizedDescription)
        }
    }

    func createProject(_ title: String) async {
        do {
            try await projectListUseCase.createProject(projectName: title)
            await updateDataSource(0)
        } catch {
            print(error.localizedDescription)
        }
    }

    func deleteCard(_ id: UInt16) async {
        do {
            try await projectListUseCase.deleteProject(projectId: Int32(id))
            await updateDataSource(0)
        } catch {
            print(error.localizedDescription)
        }
    }

    func createUser(_ user: RegisterReceiveRemote) async {
        do {
            try await projectListUseCase.createUser(user: user)
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateUserList() async {
        do {
            guard
                let userDtoList = try await projectListUseCase.getUserList() as? [PersonDTO?]
            else { return }

            var userList = [PersonDTO]()

            userDtoList.forEach { user in
                guard let user = user else { return }

                userList.append(user)
            }

            userListSignal = userList
        } catch {
            print(error.localizedDescription)
        }
    }

    func deleteUser(_ idList: [KotlinInt]) async {
        do {
            try await projectListUseCase.deleteUser(id: .init(array: idList))
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateNotificationItemList() async {
        do {
            guard
                let unwrappedItemList = try await projectListUseCase.getNotificationTaskList() as? [NotificationItem?]
            else { return }

            var itemList = [NotificationItem]()

            unwrappedItemList.forEach { item in
                guard let item = item else { return }

                itemList.append(item)
            }

            notificationItemList = itemList
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
