//
//  ScreenInfoButton.swift
//  iosApp
//
//  Created by evilgen on 17.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ScreenInfoButton: View {
    //    MARK: - Props
    private let text: String
    private let isUrgent: Bool
    private let isImageNeeded: Bool

    //    MARK: - Init
    init(_ text: String, isUrgent: Bool = false) {
        self.text = text
        self.isUrgent = isUrgent
        self.isImageNeeded = true
    }

    init() {
        self.text = .init()
        self.isUrgent = false
        self.isImageNeeded = false
    }

    var body: some View {
        ScreenInfoButton()
    }

    //    MARK: - Methods
    private func ScreenInfoButton() -> some View {
        Button(action: isImageNeeded ? openInfoView : openEstimateView) {
            HStack {
                if isUrgent {
                    Image(systemName: ComponentsConstants.Strings.urgentImageName)
                }

                Spacer()

                Text(isImageNeeded ? text : getEstimateTitle())

                Spacer()

                if isImageNeeded {
                    Image(systemName: getInfoImageName())
                }
            }
        }
        .padding()
        .border(.primary)
    }

    private func openEstimateView() {}
    private func openInfoView() {}
}

//    MARK: - Constants
private extension ScreenInfoButton {
    func getInfoImageName() -> String {
        "arrow.right.circle"
    }

    func getEstimateTitle() -> String {
        "Рассчет времени по проекту"
    }
}
