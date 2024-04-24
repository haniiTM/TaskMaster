//
//  LaborCostListView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct LaborCostListView: View {
    //    MARK: Props
    private let viewModel: LaborCostListViewModelProtocol
    private let title: String
    private let laborCostList: [String]

    //    MARK: Init
    init(_ title: String) {
        self.title = title
        viewModel = LaborCostListViewModel()
        viewModel.updateDataSource()
        laborCostList = viewModel.laborCostListSignal.value ?? .init()
    }

    //    MARK: Body
    var body: some View {
//        ProjectFrameView(title) {
            List(laborCostList, id: \.self) { laborCost in
                Button(laborCost) {}.padding(8)
            }.padding()
//        }
    }
}
