//
//  SubTaskCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

final class SubTaskCardController: SubTaskCardControllerProtocol {
    //    MARK: Props
    let model: any TaskInfoProtocol

    //    MARK: Init
    required init(model: any TaskInfoProtocol) {
        self.model = model
    }

    //    MARK: Methods
    func open() {}

    func remove() {}
}
