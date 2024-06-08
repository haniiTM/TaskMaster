//
//  TaskInfoViewModel.swift
//  TaskMaster
//
//  Created by  user on 18-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class TaskInfoViewModel: TaskInfoViewModelProtocol {
    //    MARK: Props
    private let taskInfoUseCase = KoinHelper().getTaskInfoUseCase()
    @Published private(set) var taskInfo = DetailedTaskInfo()
    @Published private(set) var activityListSignal = [ActivityDTO]()
    @Published private(set) var userListSignal = [PersonDTO]()
    @Published private(set) var freeFromParentUserListSignal = [PersonDTO]()
    @Published private(set) var taskListForDependency = [TaskDTO]()
    @Published private(set) var typeOfActivityList = [TypeOfActivityDTO]()
    @Published private(set) var statusList = [StatusDTO]()

    //    MARK: Methods
    func getTaskInfo(_ id: UInt16) async {
        do {
            guard
                let optionalTaskInfo = try await taskInfoUseCase.getTaskInfo(taskId: id)
            else { return }

            taskInfo = .init(optionalTaskInfo)
        } catch {
            print(error.localizedDescription)
        }
    }

    func getActivityList() async {
        do {
            guard
                let optionalActivityList = try await taskInfoUseCase.getActivityList() as? [ActivityDTO?]
            else { return }

            var activityList = [ActivityDTO]()

            optionalActivityList.forEach { activity in
                guard let activity = activity else { return }

                activityList.append(activity)
            }

            activityListSignal = activityList
        } catch {
            print(error.localizedDescription)
        }
    }

    func createLaborCost(_ taskId: UInt16, laborCost: ManHoursDTO) async {
        do {
            try await taskInfoUseCase.createLaborCost(manHour: laborCost, taskId: Int32(taskId))
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateUserList(_ parentId: UInt16) async {
        do {
            guard
                let optionalUserList = try await taskInfoUseCase.getTaskUserList(taskId: .init(parentId)) as? [PersonDTO?]
            else { return }

            var unwrappedUserList = [PersonDTO]()

            optionalUserList.forEach { user in
                guard let user = user else { return }

                unwrappedUserList.append(user)
            }

            userListSignal = unwrappedUserList
        } catch {
            print(error.localizedDescription)
        }
    }

    func deleteUser(_ userId: UInt16, parentId: UInt16) async {
        do {
            try await taskInfoUseCase.deleteUserFromTask(userId: .init(userId),
                                                         taskId: .init(parentId))
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateFreeFromParentUserList(_ projectId: UInt16) async {
        do {
            guard
                let optionalUserList = try await taskInfoUseCase.getFreeFromTaskUserList(taskId: .init(projectId)) as? [PersonDTO?]
            else { return }

            var unwrappedUserList = [PersonDTO]()

            optionalUserList.forEach { user in
                guard let user = user else { return }

                unwrappedUserList.append(user)
            }

            freeFromParentUserListSignal = unwrappedUserList
        } catch {
            print(error.localizedDescription)
        }
    }

    func addUser(_ parentId: UInt16, userIdList: [KotlinInt]) async {
        do {
            let urp = UserRoleProjectDTO(id: nil,
                                         userid: .init(array: userIdList),
                                         projectid: nil,
                                         type_of_activityid: nil,
                                         score: nil,
                                         current_task_id: .init(int: .init(parentId)),
                                         creater_project: nil)

            try await taskInfoUseCase.addUserToTask(urp: urp)
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateHoursSpent(_ taskId: UInt16, hours: String) async {
        do {
            let int32 = Int32(hours) ?? 0
            let kotlinInt = KotlinInt(int: int32)
            let urp = UserRoleProjectDTO(id: nil,
                                         userid: .init(),
                                         projectid: nil,
                                         type_of_activityid: nil,
                                         score: kotlinInt,
                                         current_task_id: nil,
                                         creater_project: nil)

            try await taskInfoUseCase.updateHoursSpent(urp: urp, taskId: .init(taskId))
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateEstimatedTime(_ taskId: UInt16, hours: String) async {
        do {
            let taskDto = TaskDTO()
            let int = Int32(hours) ?? 0
            taskDto.scope = .init(int: int)

            try await taskInfoUseCase.updateEstimatedTime(taskId: .init(taskId),
                                                          taskDTO: taskDto)
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateTaskListForDependency(projectId: UInt16, taskId: UInt16) async {
        do {
            guard
                let optionalList = try await taskInfoUseCase.getTaskListForDependence(projId: .init(projectId),
                                                                                      taskId: .init(taskId)) as? [TaskDTO?]
            else { return }

            var unwrappedList = [TaskDTO]()


            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            taskListForDependency = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func addDependency(taskDependent: UInt16, taskDependsOn: KotlinInt?) async {
        do {
            try await taskInfoUseCase.addDependency(taskDependent: .init(taskDependent),
                                                    taskDependsOn: .init(taskDependsOn ?? 0))
        } catch {
            print(error.localizedDescription)
        }
    }

    func deleteDependency(_ dependenceOn: UInt16) async {
        do {
            try await taskInfoUseCase.deleteDependency(dependenceOn: .init(dependenceOn))
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateTypeOfActivityList() async {
        do {
            guard
                let optionalList = try await taskInfoUseCase.getTypeOfActivityList() as? [TypeOfActivityDTO?]
            else { return }

            var unwrappedList = [TypeOfActivityDTO]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            typeOfActivityList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateTask(_ taskId: UInt16, taskDTO: TaskDTO) async {
        do {
            try await taskInfoUseCase.updateTask(taskId: .init(taskId),
                                                 taskDTO: taskDTO)
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateStatusList() async {
        do {
            guard
                let optionalList = try await taskInfoUseCase.getStatusList() as? [StatusDTO?]
            else { return }

            var unwrappedList = [StatusDTO]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            statusList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
