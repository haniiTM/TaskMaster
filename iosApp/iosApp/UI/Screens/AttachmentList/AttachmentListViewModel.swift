//
//  AttachmentListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

@MainActor final class AttachmentListViewModel: ObservableObject {
    //    MARK: Props
    @Published var attachmentListSignal = [ManHoursDTO]()
    private let attachmentListUseCase = KoinHelper().getLaborCostListUseCase()

    //    MARK: Methods
    func updateDataSource(_ taskId: UInt16) async {
        do {
            guard
                let unwrappedAttachmentList = try await attachmentListUseCase.getLaborCostList(taskId: .init(taskId)) as? [ManHoursDTO?]
            else { return }

            var attachmentList = [ManHoursDTO]()
            unwrappedAttachmentList.forEach { attachment in
                guard let attachment = attachment else { return }

                attachmentList.append(attachment)
            }

            attachmentListSignal = attachmentList
        } catch {
            print(error.localizedDescription)
        }
    }
}
