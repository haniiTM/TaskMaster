//
//  TaskSectionBG.swift
//  iosApp
//
//  Created by evilgen on 17.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

//    MARK: - Template Task Section BG
struct TemplateTaskSectionBG<Content: View>: View {
    //    MARK: Props
    private let title: String
    private let content: () -> Content

    //    MARK: Init
    init(title: String, @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.content = content
    }

    var body: some View {
        TaskSectionBGBody()
    }

    //    MARK: Methods
    private func TaskSectionBGBody() -> some View {
        VStack(spacing: 32) {
            Text(title)
                .font(.largeTitle)

            content()
        }
        .frame(maxWidth: .infinity)
        .padding()
        .border(.primary)
    }
}

//    MARK: - Task Section BG
struct TaskSectionBG<Content: View>: View {
    private let content: () -> Content

    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    var body: some View {
        TemplateTaskSectionBG(title: getTaskSectionTitle(), content: content)
    }
}

//    MARK: Task Section BG Constants
private extension TaskSectionBG {
    func getTaskSectionTitle() -> String {
        "Задачи"
    }
}

//    MARK: - Sub Task Section BG
struct SubTaskSectionBG<Content: View>: View {
    private let content: () -> Content

    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    var body: some View {
        TemplateTaskSectionBG(title: getSubTaskSectionTitle(), content: content)
    }
}

//    MARK: Sub Task Section BG Constants
private extension SubTaskSectionBG {
    func getSubTaskSectionTitle() -> String {
        "Подзадачи"
    }
}

//    MARK: - Completed Task Section BG
struct CompletedTaskSectionBG<Content: View>: View {
    private let content: () -> Content

    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    var body: some View {
        TemplateTaskSectionBG(title: getCompletedTaskSectionTitle(), content: content)
    }
}

//    MARK: Completed Task Section BG Constants
private extension CompletedTaskSectionBG {
    func getCompletedTaskSectionTitle() -> String {
        "Выполнено"
    }
}
