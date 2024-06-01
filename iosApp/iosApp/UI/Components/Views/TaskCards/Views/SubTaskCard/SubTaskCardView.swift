//
//  SubTaskCardView.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct SubTaskCardView: View {
    //    MARK: Props
    private let controller: SubTaskCardControllerProtocol

    //    MARK: Init
    init(_ parentId: UInt16, model: TaskInfo, viewModel: any TaskCardViewModelProtocol) {
        controller = SubTaskCardController(parentId, model: model, viewModel: viewModel)
    }

    //    MARK: Body
    var body: some View {
        TaskCardView(controller: controller)
    }
}
