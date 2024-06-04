//
//  StateManagerProtocols.swift
//  TaskMaster
//
//  Created by  user on 04-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

protocol TaskListStateManagerProtocol: ObservableObject {
    var isUserListVisible: Bool { get set }
}