//
//  ProjectListModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct ProjectListModel {
    //    MARK: Props
    var projectList: [TaskInfo] { privateProjectList }
    private var privateProjectList: [TaskInfo] = .init()

    //    MARK: Init
    init() {
        setupDefaultProjects()
    }

    //    MARK: Methods
    private mutating func setupDefaultProjects() {
        let nissan = TaskInfo(title: "Сайт Nissan",
                              timerValue: 72,
                              isUrgent: true,
                              participiantsValue: 7)

        let fanzilla = TaskInfo(title: "Мобильное приложение FanZilla",
                                timerValue: 27,
                                isUrgent: false,
                                participiantsValue: 3)

        let kommersant = TaskInfo(title: "Мобильное приложение Kommersant",
                                  timerValue: 42,
                                  isUrgent: false,
                                  participiantsValue: 4)

        let vedomosti = TaskInfo(title: "Мобильное приложение Vedomosti",
                                timerValue: 54,
                                isUrgent: true,
                                participiantsValue: 5)

        let kalibro = TaskInfo(title: "Мобильное приложение Kalibro",
                                  timerValue: 24,
                                  isUrgent: true,
                                  participiantsValue: 2)

        privateProjectList = [nissan, fanzilla, kommersant, vedomosti, kalibro]
    }
}
