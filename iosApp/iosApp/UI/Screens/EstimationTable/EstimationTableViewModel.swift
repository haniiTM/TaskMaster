//
//  EstimationTableViewModel.swift
//  TaskMaster
//
//  Created by  user on 11-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

@MainActor
final class EstimationTableViewModel: ObservableObject, Searchable {
    private let useCase = KoinHelper().getEstimationTableUseCase()
    
    @Published private(set) var ganttList = [CalendarPlan]()

    func updateGanttList(_ projectId: UInt16) async {
        do {
            guard
                let optionalList = try await useCase.getCalendarPlanList(projectId: .init(projectId)) as? [CalendarPlan?]
            else { return }

            var unwrappedList = [CalendarPlan]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            ganttList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
