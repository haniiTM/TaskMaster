//
//  ActionButton.swift
//  iosApp
//
//  Created by evilgen on 16.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ActionButton: View {
    //    MARK: - Props
    private let isImageNeeded: Bool

    init(isImageNeeded: Bool) {
        self.isImageNeeded = isImageNeeded
    }

    //    MARK: - Init
    var body: some View {
        ActionButtonBody()
    }

    //    MARK: - Methods
    private func ActionButtonBody() -> some View {
        HStack {
            if isImageNeeded {
                Image(systemName: getTaskImageName())
            }

            Text(isImageNeeded ? getTaskTitle() : getLaborCostTitle())
        }
        .padding()
        .border(.primary)
    }
}

//    MARK: - Constants
private extension ActionButton {
    func getTaskTitle() -> String {
        "Добавить задачу"
    }

    func getTaskImageName() -> String {
        "plus"
    }

    func getLaborCostTitle() -> String {
        "Добавить трудозатраты"
    }
}
