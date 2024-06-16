//
//  EstimationTableViewModel.swift
//  TaskMaster
//
//  Created by  user on 11-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

@MainActor
final class EstimationTableViewModel: ObservableObject, Searchable {
    private let useCase = KoinHelper().getEstimationTableUseCase()

    @AppStorage("userToken") private var userToken: String?

    @Published private(set) var ganttReportList = [CalendarPlan]()
    @Published private(set) var laborCostReportList = [ManHoursReportDTO]()

    @Published private var ganttTableFileName = ""
    @Published private var laborCostTableFileName = ""

    func updateGanttList(_ projectId: UInt16) async {
        do {
            guard
                let optionalList = try await useCase.getGanttReport(projectId: .init(projectId)) as? [CalendarPlan?]
            else { return }

            var unwrappedList = [CalendarPlan]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            ganttReportList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func updateLaborCostReportList(_ projectId: UInt16) async {
        do {
            guard
                let optionalList = try await useCase.getLaborCostReport(projectId: .init(projectId)) as? [ManHoursReportDTO?]
            else { return }

            var unwrappedList = [ManHoursReportDTO]()

            optionalList.forEach { item in
                guard let item = item else { return }

                unwrappedList.append(item)
            }

            laborCostReportList = unwrappedList
        } catch {
            print(error.localizedDescription)
        }
    }

    func getGanttTableFileName(_ projectId: UInt16) async {
        do {
            guard
                let fileName = try await useCase.getGanttTableName(projectId: .init(projectId))
            else { return }

            ganttTableFileName = fileName
        } catch {
            print(error.localizedDescription)
        }
    }

    func getLaborCostTableFileName(_ projectId: UInt16) async {
        do {
            guard
                let fileName = try await useCase.getLaborCostTableName(projectId: .init(projectId))
            else { return }

            laborCostTableFileName = fileName
        } catch {
            print(error.localizedDescription)
        }
    }

    private func downloadTable(
        _ urlString: String,
        _ fileName: String
    ) async {
        do {
            let documentDirectory = FileManager.default.urls(for: .documentDirectory,
                                                             in: .userDomainMask).first!
            let filePath = documentDirectory.appendingPathComponent(fileName)

            guard
                let fileUrl = URL(string: urlString),
                let token = userToken
            else { return }

            var urlRequest = URLRequest(url: fileUrl)
            urlRequest.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization")

            let session = URLSession.shared
            let tempFileUrl = try await session.download(for: urlRequest).0

            let imageData = try Data(contentsOf: tempFileUrl)
            try imageData.write(to: filePath)
        } catch {
            print(error.localizedDescription)
        }
    }

    func downloadGanttTable(_ projectId: UInt16) async {
        let urlString = "http://5.35.85.206:8080/user_role_project/excel/\(projectId)"

        await getGanttTableFileName(projectId)
        await downloadTable(urlString,
                            ganttTableFileName)
    }

    func downloadLaborCostTable(_ projectId: UInt16) async {
        let urlString = "http://5.35.85.206:8080/manhours/excelreport/\(projectId)"

        await getLaborCostTableFileName(projectId)
        await downloadTable(urlString,
                            laborCostTableFileName)
    }

    func search() async {}
}
