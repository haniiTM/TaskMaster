//
//  TaskCardControllerProtocol.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol TaskCardControllerProtocol: TaskCardActions,
                                     TaskNumberTitleProvider,
                                     TaskTitleProvider,
                                     TimerTitleProvider,
                                     UrgencyValueProvider,
                                     UrgentImageNameProvider,
                                     CategoriesTitleProvider {}
