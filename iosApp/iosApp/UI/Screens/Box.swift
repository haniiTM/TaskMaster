//
//  Box.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

class Box<T> {
    //    MARK: Props
    typealias Listener = (T) -> Void
    private var listener: Listener?

    var value: T {
        didSet { listener?(value) }
    }

    //    MARK: Init
    init(_ v: T) {
        value = v
    }


    //    MARK: Methods
    func bind(_ l: Listener?) {
        listener = l
    }
}
