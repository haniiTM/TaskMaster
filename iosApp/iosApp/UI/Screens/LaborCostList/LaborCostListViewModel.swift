//
//  LaborCostListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class LaborCostListViewModel: ObservableObject {
    //    MARK: Props
    private let laborCostListUseCase = KoinHelper().getLaborCostListUseCase()
    @Published var laborCostList = [ManHoursDTO]()

    //    MARK: Methods
    func updateDataSource(_ taskId: UInt16) async {
        do {
            guard
                let unwrappedLaborCostList = try await laborCostListUseCase.getLaborCostList(taskId: Int32(taskId)) as? [ManHoursDTO?]
            else { return }

            var nonOptionalLaborCostList = [ManHoursDTO]()

            unwrappedLaborCostList.forEach { laborCost in
                guard let laborCost = laborCost else { return }

                nonOptionalLaborCostList.append(laborCost)
            }

            laborCostList = nonOptionalLaborCostList
        } catch {
            print(error.localizedDescription)
        }
    }
}
