//
//  AttachmentListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

final class AttachmentListViewModel: AttachmentListViewModelProtocol, ObservableObject {
    //    MARK: Props
    var attachmentListSignal: Box<[String]?> = .init(nil)
    private let model = AttachmentListModel()

    //    MARK: Methods
    func updateDataSource() {
        attachmentListSignal.value = model.attachmentList
    }
}
