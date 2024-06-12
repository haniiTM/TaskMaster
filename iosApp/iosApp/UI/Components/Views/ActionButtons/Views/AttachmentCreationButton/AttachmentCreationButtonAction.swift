//
//  AttachmentCreationButtonAction.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AttachmentCreationButtonAction: Openable {
    @ObservedObject private var stateManager: AttachmentListStateManager

    init(_ stateManager: AttachmentListStateManager) {
        self.stateManager = stateManager
    }

    func open() {
        stateManager.isFileImporterVisible.toggle()
    }
}
