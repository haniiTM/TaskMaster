//
//  LaborCostCreationButtonAction.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct LaborCostCreationButtonAction: Openable {
    @ObservedObject private var stateManager: TaskInfoStateManager
    
    init(_ stateManager: TaskInfoStateManager) {
        self.stateManager = stateManager
    }
    
    func open() {
        stateManager.isCreationAlertShown.toggle()
    }
}
