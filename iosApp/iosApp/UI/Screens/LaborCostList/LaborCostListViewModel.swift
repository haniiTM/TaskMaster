//
//  LaborCostListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

final class LaborCostListViewModel: LaborCostListViewModelProtocol, ObservableObject {
    //    MARK: Props
    var laborCostListSignal: Box<[String]?> = .init(nil)
    private let model = LaborCostListModel()

    //    MARK: Methods
    func updateDataSource() {
        laborCostListSignal.value = model.laborCostList
    }
}
