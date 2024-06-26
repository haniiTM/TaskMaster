//
//  ProjectCardControllerProtocol.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol ProjectCardControllerProtocol: TaskCardActions,
                                        TaskTitleProvider,
                                        TimerTitleProvider,
                                        ParticipiantsTitleProvider,
                                        CardDeletionAlertTitleProvider,
                                        CardDeletionAlertBindingProvider,
                                        DestructiveAlertPresentable {}
