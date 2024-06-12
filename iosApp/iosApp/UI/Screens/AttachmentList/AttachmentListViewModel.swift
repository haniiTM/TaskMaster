//
//  AttachmentListViewModel.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

@MainActor
final class AttachmentListViewModel: ObservableObject, Searchable {
    //    MARK: Props
    private let attachmentListUseCase = KoinHelper().getAttachmentListUseCase()
    @AppStorage("userToken") private var userToken: String?
    @Published var attachmentList = [FileDTO]()

    //    MARK: Methods
    func updateDataSource(_ taskId: UInt16) async {
        do {
            guard
                let unwrappedList = try await attachmentListUseCase.getAttachmentList(taskId: .init(taskId))
            else { return }

            attachmentList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func deleteAttachment(_ attachment: FileDTO,
                          taskId: UInt16) async {
        do {
            let attachmentId = Int32(attachment.id ?? 0)
            let descriptionId = Int32(attachment.descriptionId ?? 0)
            try await attachmentListUseCase.deleteAttachment(attachmentId: attachmentId,
                                                             descriptionId: descriptionId)
            await updateDataSource(taskId)
        } catch {
            print(error.localizedDescription)
        }
    }

    func downloadAttachment(_ attachmentDto: FileDTO,
                            taskId: UInt16) async {
        do {
            let documentDirectory = FileManager.default.urls(for: .documentDirectory,
                                                             in: .userDomainMask).first!

            guard
                let attachmentId = attachmentDto.id,
                let attachmentName = attachmentDto.orig_filename,
                let attachmentType = attachmentDto.type
            else { return }

            let attachmentPath = documentDirectory.appendingPathComponent(attachmentName +
                                                                          "." +
                                                                          attachmentType)

            let urlString = "http://5.35.85.206:8080/description/download/\(taskId)/\(attachmentId)"

            guard
                let attachmentUrl = URL(string: urlString),
                let token = userToken
            else { return }

            var urlRequest = URLRequest(url: attachmentUrl)
            urlRequest.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization")

            let session = URLSession.shared

            let tempFileUrl = try await session.download(for: urlRequest).0

            let imageData = try Data(contentsOf: tempFileUrl)

            try imageData.write(to: attachmentPath)
        } catch {
            print(error.localizedDescription)
        }
    }

    func addAttachment(
        _ url: URL,
        _ taskId: UInt16
    ) async {
        do {
            let title = url.lastPathComponent
            let data = try Data(contentsOf: url)

            try await attachmentListUseCase.addAttachment(taskId: .init(taskId),
                                                          title: title,
                                                          data: .init(data))
            await updateDataSource(taskId)
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
