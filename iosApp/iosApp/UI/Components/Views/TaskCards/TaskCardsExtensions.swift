//
//  TaskCardsExtensions.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

// MARK: - General
extension TaskTitleProvider {
    var taskTitle: String { model.title }
}

extension TimerTitleProvider {
    var timerTitle: String {
        let localTimerTitle = model.timerValue
        let resultString = TaskCardsConstants.Strings.Titles.timerTitle(localTimerTitle)
        return resultString
    }
}

//extension UrgencyValueProvider {
//    var isUrgent: Bool {
//        model.isUrgent
//    }
//}

extension UrgentImageNameProvider {
    var urgentImageName: String {
        TaskCardsConstants.Strings.ImageNames.urgentImageName
    }
}

// MARK: - Project
extension ParticipiantsTitleProvider {
    var participiantsTitle: String {
        let participiantsTitle = TaskCardsConstants.Strings.Titles.participiantsTitle
//        let emptyTitle = TaskCardsConstants.Strings.EmptyTitles.emptyStringTitle

        let participiantsValueNumber = model.participiantsValue
        let participiantsValueString =
        participiantsValueNumber.description
//        emptyTitle

        let resultString =
        participiantsTitle +
        participiantsValueString

        return resultString
    }
}

// MARK: - Task
//extension TaskNumberTitleProvider {
//    var taskNumberTitle: String {
//        let localNumberTitle = TaskCardsConstants.Strings.Titles.numberTitle
//        let emptyNumberTitle = TaskCardsConstants.Strings.EmptyTitles.emptyNumberTitle
//
//        var finalString = localNumberTitle
//
//        if let parentNumberValue = model.parentNumber {
//            let parentNumberTitle = parentNumberValue.description
//            let numbersSeparator = TaskCardsConstants.Strings.Separators.numbersSeparator
//
//            finalString +=
//            parentNumberTitle +
//            numbersSeparator
//        }
//
//        let numberValue = model.numberValue
//        let numberTitle =
//        numberValue?.description ??
//        emptyNumberTitle
//
//        finalString += numberTitle
//
//        return finalString
//    }
//}

//extension CategoriesTitleProvider {
//    var categoriesTitle: String {
//        let emptyCategoriesTitle = TaskCardsConstants.Strings.EmptyTitles.emptyCategoriesTitle
//        let categoriesSeparator = TaskCardsConstants.Strings.Separators.categoriesSeparator
//        let categoriesValue = model.categories
//
//        let resultString =
//        categoriesValue?.joined(separator: categoriesSeparator) ??
//        emptyCategoriesTitle
//
//        return resultString
//    }
//}
