//
//  StateManagerProtocols.swift
//  TaskMaster
//
//  Created by  user on 04-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

protocol UserListVisible: ObservableObject {
    var isUserListVisible: Bool { get set }
}

protocol UserAdditionAlertVisible: ObservableObject {
    var isUserAdditionAlertVisible: Bool { get set }
}

protocol TaskListStateManagerProtocol: UserListVisible, UserAdditionAlertVisible, CardDeletionAlertPresentable {}

protocol CardDeletionAlertPresentable: ObservableObject {
    var isCardDeletionAlertPresented: Bool { get set }
    var isCardDeletionAlertPresentedBinding: Binding<Bool> { get }
}

extension CardDeletionAlertPresentable {
    var isCardDeletionAlertPresentedBinding: Binding<Bool> {
        Binding(
            get: { self.isCardDeletionAlertPresented },
            set: { self.isCardDeletionAlertPresented = $0 }
        )
    }
}
