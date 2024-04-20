//
//  SplashScreen.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct SplashScreen: View {
    var body: some View {
        MainFrameView {
            ScreenInfoButton("Изучение Kotlin Multiplatform", isUrgent: true)

            TaskSectionBG {

                ProjectCardView(taskInfo: .init(title: "Сайт Nissan",
                                                timerValue: 72,
                                                isUrgent: true,
                                                participiantsValue: 4))

                TaskCardView(taskInfo: .init(numberValue: 1,
                                             title: "Изучение Kotlin Multiplatform",
                                             timerValue: 42,
                                             isUrgent: false,
                                             categories: ["Backend", "Mobile"]))

                SubTaskCardView(taskInfo: .init(parentNumber: 1,
                                                numberValue: 1,
                                                title: "Изучение синтаксиса",
                                                timerValue: 24,
                                                isUrgent: false,
                                                categories: ["Backend", "Mobile"]))

                TaskCreationButton()
            }

            CompletedTaskSectionBG {
                LaborCostCreationButton()
            }
        }
    }
}
