//
//  TaskInfoViewModel.swift
//  TaskMaster
//
//  Created by  user on 18-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class TaskInfoViewModel: ObservableObject, Searchable {
    //    MARK: Props
    private let taskInfoUseCase = KoinHelper().getTaskInfoUseCase()
    @Published private(set) var taskInfo = DetailedTaskInfo()
    @Published private(set) var activityListSignal = [ActivityDTO]()

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

    func search() async {}
}
