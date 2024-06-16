//
//  EstimationTableViewTemplate.swift
//  TaskMaster
//
//  Created by  user on 11-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct EstimationTableViewTemplate: View {
    let soloData: [Date]
    let pairData: [(Int32, String)]
    let tripleData: [(Date, String, Int32)]

    var body: some View {
        ScrollView(.horizontal) {
            LazyHStack {
                LazyVStack(spacing: 0) {
                    TableHeader(dateList: soloData)

                    ForEach(pairData, id: \.0) { data in
                        TableRow(data: data,
                                 dateList: soloData,
                                 hourDataList: tripleData)
                    }
                }
            }
        }
    }
}
