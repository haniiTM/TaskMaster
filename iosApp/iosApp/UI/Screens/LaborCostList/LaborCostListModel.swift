//
//  LaborCostListModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct LaborCostListModel {
    //    MARK: Props
    var laborCostList: [String] { privateLaborCostList }
    private var privateLaborCostList: [String] = .init()

    //    MARK: Init
    init() {
        setupDefaultLaborCostList()
    }

    //    MARK: Methods
    private mutating func setupDefaultLaborCostList() {
        let localLaborCostList: [String] = .init(repeating: "Трудозатрата", count: 15)

        localLaborCostList.enumerated().forEach { index, laborCost in
            let newLaborCost = "\(laborCost) \(index + 1)"
            privateLaborCostList.append(newLaborCost)
        }
    }
}
