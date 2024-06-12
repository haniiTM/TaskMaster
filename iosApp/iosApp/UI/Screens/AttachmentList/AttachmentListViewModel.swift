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

    func deleteAttachment(taskId: UInt16,
                          attachmentId: KotlinInt,
                          descriptionId: KotlinInt) async {
        do {
            try await attachmentListUseCase.deleteAttachment(attachmentId: .init(attachmentId),
                                                             descriptionId: .init(descriptionId))
            await updateDataSource(taskId)
        } catch {
            print(error.localizedDescription)
        }
    }

    func downloadAttachment(taskId: UInt16,
                            attachmentId: KotlinInt) async {
        do {
            let documentDirectory = FileManager.default.urls(for: .documentDirectory,
                                                             in: .userDomainMask).first!

            let imageName = documentDirectory.appendingPathComponent("myImage.png")

            let urlString = "https://raw.githubusercontent.com/programmingwithswift/HowToSaveFileFromUrl/master/testFile.png"

            guard let imageUrl = URL(string: urlString) else { return }

            let urlRequest = URLRequest(url: imageUrl)

            let session = URLSession.shared

            let tempFileUrl = try await session.download(for: urlRequest).0

            let imageData = try Data(contentsOf: tempFileUrl)

            try imageData.write(to: imageName)

            if FileManager.default.fileExists(atPath: imageName.path) {
                print("Image successfully saved at \(imageName.path)")
            } else {
                print("Failed to save image")
            }
        } catch {
            print(error.localizedDescription)
        }
    }

    func search() async {}
}
