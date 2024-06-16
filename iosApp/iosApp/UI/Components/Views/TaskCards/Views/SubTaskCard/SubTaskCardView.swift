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
    init(_ parentId: UInt16,
         _ model: TaskInfo,
         _ stateManager: any CardDeletionAlertPresentable,
         _ viewModel: any TaskCardViewModelProtocol)
    {
        controller = SubTaskCardController(parentId,
                                           model,
                                           stateManager,
                                           viewModel)
    }

    //    MARK: Body
    var body: some View {
        TaskCardView(controller: controller)
    }
}
