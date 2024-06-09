//
//  TaskInfoViewModelProtocoll.swift
//  TaskMaster
//
//  Created by  user on 08-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

protocol TaskInfoViewModelProtocol: Searchable,
                                    UserListable,
                                    TaskUserListUpdater,
                                    UserDeletable,
                                    FreeFromParentUserListUpdater,
                                    UserAppendable {}
