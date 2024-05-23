//
//  ProjectListModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct ProjectListModel {
    //    MARK: Props
    var projectList: [ProjectInfo] { privateProjectList }
    private var privateProjectList: [ProjectInfo] = .init()

    //    MARK: Init
    init() {
        setupDefaultProjects()
    }

    //    MARK: Methods
    private mutating func setupDefaultProjects() {
        let nissan = ProjectInfo(title: "Сайт Nissan",
                                 timerValue: 72,
                                 participiantsValue: 7)

        let fanzilla = ProjectInfo(title: "Мобильное приложение FanZilla",
                                   timerValue: 27,
                                   participiantsValue: 3)

        let kommersant = ProjectInfo(title: "Мобильное приложение Kommersant",
                                     timerValue: 42,
                                     participiantsValue: 4)

        let vedomosti = ProjectInfo(title: "Мобильное приложение Vedomosti",
                                    timerValue: 54,
                                    participiantsValue: 5)

        let kalibro = ProjectInfo(title: "Мобильное приложение Kalibro",
                                  timerValue: 24,
                                  participiantsValue: 2)

        privateProjectList = [nissan, fanzilla, kommersant, vedomosti, kalibro]
    }
}
