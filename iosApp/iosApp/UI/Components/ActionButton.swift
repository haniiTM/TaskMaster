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

    //    MARK: - Init
    init(isImageNeeded: Bool) {
        self.isImageNeeded = isImageNeeded
    }

    var body: some View {
        ActionButtonBody()
    }

    //    MARK: - Methods
    private func ActionButtonBody() -> some View {
        Button(action: isImageNeeded ? openTaskCreationView : openLaborCostCreationView) {
            HStack {
                if isImageNeeded {
                    Image(systemName: getTaskImageName())
                }

                Text(isImageNeeded ? getTaskTitle() : getLaborCostTitle())
            }
        }
        .padding()
        .border(.primary)
    }

    private func openTaskCreationView() {}
    private func openLaborCostCreationView() {}
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
