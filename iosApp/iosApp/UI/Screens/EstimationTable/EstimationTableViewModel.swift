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

    @Published private(set) var ganttReportList = [CalendarPlan]()
    @Published private(set) var laborCostReportList = [ManHoursReportDTO]()

    func updateGanttList(_ projectId: UInt16) async {
        do {
            guard
                let optionalList = try await useCase.getGanttReport(projectId: .init(projectId)) as? [CalendarPlan?]
            else { return }

            var unwrappedList = [CalendarPlan]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            ganttReportList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateLaborCostReportList(_ projectId: UInt16) async {
        do {
            guard
                let optionalList = try await useCase.getLaborCostReport(projectId: .init(projectId)) as? [ManHoursReportDTO?]
            else { return }

            var unwrappedList = [ManHoursReportDTO]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            laborCostReportList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
