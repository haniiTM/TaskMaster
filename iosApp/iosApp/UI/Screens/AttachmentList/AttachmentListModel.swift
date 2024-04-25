//
//  AttachmentListModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct AttachmentListModel {
    //    MARK: Props
    var attachmentList: [String] { privateAttachmentList }
    private var privateAttachmentList: [String] = .init()

    //    MARK: Init
    init() {
        setupDefaultAttachmentList()
    }

    //    MARK: Methods
    private mutating func setupDefaultAttachmentList() {
        let localAttachmentList: [String] = .init(repeating: "Вложение", count: 10)

        localAttachmentList.enumerated().forEach { index, attachment in
            let newAttachment = "\(attachment) \(index + 1)"
            privateAttachmentList.append(newAttachment)
        }
    }
}
