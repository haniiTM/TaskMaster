//
//  AttachmentListViewModelProtocol.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol AttachmentListViewModelProtocol: AnyObject {
    //    MARK: Props
    var attachmentListSignal: Box<[String]?> { get }

    //    MARK: Methods
    func updateDataSource()
}
