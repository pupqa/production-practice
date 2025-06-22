//package com.bober.managerfull.network
//
//import com.bober.managerfull.model.Workstation
//
//class Util {
//}
//fun getCoworkingWorkstations(): List<Workstation> {
//    return listOf(
//        Workstation("1", 0.165f, 0.44f, "Сотрудник: Иван Петров", "Должность: Team Lead"),
//        Workstation("2", 0.3f, 0.44f, "Сотрудник: Анна Сидорова", "Должность: Android Developer"),
//        Workstation("3", 0.455f, 0.44f, "Сотрудник: Петр Иванов", "Должность: Backend Developer"),
//        Workstation("4", 0.595f, 0.44f, "Сотрудник: Мария Кузнецова", "Должность: QA Engineer"),
//        Workstation("5", 0.75f, 0.44f, "Сотрудник: Алексей Смирнов", "Должность: UI/UX Designer"),
//        Workstation("6", 0.16f, 0.512f, "Сотрудник: Елена Васильева", "Должность: Product Manager"),
//        Workstation("7", 0.3f, 0.512f, "Сотрудник: Дмитрий Попов", "Должность: Frontend Developer"),
//        Workstation("8", 0.45f, 0.512f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("9", 0.59f, 0.512f, "Сотрудник: Сергей Федоров", "Должность: DevOps Engineer"),
//        Workstation("10", 0.744f, 0.512f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("11", 0.31f, 0.35f, "Шкаф с фотоприборами", "фотоаппарат, камера, стенд"),
//        Workstation("12", 0.25f, 0.59f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("13", 0.31f, 0.59f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("14", 0.446f, 0.59f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("15", 0.505f, 0.59f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("16", 0.632f, 0.59f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("17", 0.69f, 0.59f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("18", 0.69f, 0.641f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("19", 0.632f, 0.641f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("20", 0.505f, 0.641f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("21", 0.446f, 0.641f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("22", 0.31f, 0.641f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("23", 0.25f, 0.641f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("!", 0.1f, 0.27f, "Коворкинг", "Расписание"),
//    )
//
//}
//
//fun getFloorThreeWorkstation(): List<Workstation> {
//    return listOf(
//        Workstation("1", 0.32f, 0.5f, "Сотрудник: Иван Петров", "Должность: Team Lead"),
//        Workstation("2", 0.32f, 0.53f, "Сотрудник: Анна Сидорова", "Должность: Android Developer"),
//        Workstation("3", 0.32f, 0.56f, "Сотрудник: Петр Иванов", "Должность: Backend Developer"),
//        Workstation("4", 0.32f, 0.59f, "Сотрудник: Мария Кузнецова", "Должность: QA Engineer"),
//        Workstation("5", 0.43f, 0.502f, "Сотрудник: Алексей Смирнов", "Должность: UI/UX Designer"),
//        Workstation("6", 0.43f, 0.532f, "Сотрудник: Елена Васильева", "Должность: Product Manager"),
//        Workstation("7", 0.43f, 0.562f, "Сотрудник: Дмитрий Попов", "Должность: Frontend Developer"),
//        Workstation("8", 0.43f, 0.592f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("9", 0.49f, 0.502f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("10", 0.49f, 0.532f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("11", 0.49f, 0.562f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("12", 0.49f, 0.592f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("13", 0.592f, 0.502f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("14", 0.592f, 0.532f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("15", 0.592f, 0.562f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("16", 0.592f, 0.592f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("17", 0.7f, 0.502f, "Шкаф №3", "Радиоприборы"),
//        Workstation("18", 0.09f, 0.507f, "Шкаф №4", "Документы"),
//        Workstation("19", 0.32f, 0.39f, "Шкаф №5", "Документы"),
//        Workstation("!", 0.1f, 0.3f, "3 Этаж", "Расписание"),
//
//        )
//}
//fun getFloorFourWorkstation(): List<Workstation> {
//    return listOf(
//        Workstation("1", 0.018f, 0.48f, "Сотрудник: Иван Петров", "Должность: Team Lead"),
//        Workstation("2", 0.018f, 0.512f, "Сотрудник: Анна Сидорова", "Должность: Android Developer"),
//        Workstation("3", 0.018f, 0.542f, "Сотрудник: Петр Иванов", "Должность: Backend Developer"),
//        Workstation("4", 0.1f, 0.48f, "Сотрудник: Мария Кузнецова", "Должность: QA Engineer"),
//        Workstation("5", 0.1f, 0.512f, "Сотрудник: Алексей Смирнов", "Должность: UI/UX Designer"),
//        Workstation("6", 0.1f, 0.542f, "Сотрудник: Елена Васильева", "Должность: Product Manager"),
//        Workstation("7", 0.276f, 0.42f, "Сотрудник: Дмитрий Попов", "Должность: Frontend Developer"),
//        Workstation("8", 0.213f, 0.51f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("9", 0.213f, 0.54f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("10", 0.31f, 0.51f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("11", 0.31f, 0.54f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("12", 0.4f, 0.43f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("13", 0.38f, 0.51f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("14", 0.38f, 0.54f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("15", 0.5f, 0.42f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("16", 0.48f, 0.479f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("17", 0.48f, 0.508f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("18", 0.48f, 0.537f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("19", 0.707f, 0.42f, "Шкаф №5", "Документы"),
//        Workstation("20", 0.67f, 0.47f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("21", 0.707f, 0.511f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("22", 0.707f, 0.539f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("23", 0.61f, 0.536f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("24", 0.61f, 0.507f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("25", 0.795f, 0.509f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("26", 0.795f, 0.537f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("27", 0.90f, 0.51f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("28", 0.90f, 0.538f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("29", 0.84f, 0.42f, "Шкаф №5", "Документы"),
//        Workstation("!", 0.1f, 0.37f, "4 Этаж", "Расписание"),
//
//        )
//}
//fun getFloorSixWorkstation(): List<Workstation> {
//    return listOf(
//        Workstation("1", 0.035f, 0.431f, "Сотрудник: Иван Петров", "Должность: Team Lead"),
//        Workstation("2", 0.23f, 0.5f, "Сотрудник: Анна Сидорова", "Должность: Android Developer"),
//        Workstation("3", 0.23f, 0.524f, "Сотрудник: Петр Иванов", "Должность: Backend Developer"),
//        Workstation("4", 0.23f, 0.546f, "Сотрудник: Мария Кузнецова", "Должность: QA Engineer"),
//        Workstation("5", 0.31f, 0.5f, "Сотрудник: Алексей Смирнов", "Должность: UI/UX Designer"),
//        Workstation("6", 0.31f, 0.524f, "Сотрудник: Елена Васильева", "Должность: Product Manager"),
//        Workstation("7", 0.31f, 0.546f, "Сотрудник: Дмитрий Попов", "Должность: Frontend Developer"),
//        Workstation("8", 0.37f, 0.478f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("9", 0.37f, 0.502f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("10", 0.37f, 0.526f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("11", 0.37f, 0.548f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("12", 0.457f, 0.478f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("13", 0.457f, 0.502f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("14", 0.457f, 0.526f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("15", 0.457f, 0.548f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("16", 0.515f, 0.478f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("17", 0.515f, 0.502f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("18", 0.515f, 0.526f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("19", 0.515f, 0.548f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("20", 0.598f, 0.478f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("21", 0.598f, 0.502f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("22", 0.598f, 0.526f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("23", 0.598f, 0.548f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("24", 0.652f, 0.478f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("25", 0.652f, 0.50f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("26", 0.652f, 0.524f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("27", 0.652f, 0.546f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("28", 0.74f, 0.478f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("29", 0.74f, 0.50f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("30", 0.74f, 0.524f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("31", 0.74f, 0.546f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("32", 0.785f, 0.501f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("33", 0.785f, 0.524f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("34", 0.785f, 0.546f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("35", 0.868f, 0.501f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("36", 0.868f, 0.524f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("37", 0.868f, 0.546f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("38", 0.914f, 0.505f, "Шкаф №5", "Документы"),
//        Workstation("39", 0.914f, 0.528f, "Шкаф №5", "Документы"),
//        Workstation("40", 0.914f, 0.55f, "Шкаф №5", "Документы"),
//        Workstation("41", 0.888f, 0.43f, "", "Документы"),
//        Workstation("!", 0.1f, 0.37f, "6 Этаж", "Расписание"),
//
//        )
//}
//fun getConferenceFourWorkstations(): List<Workstation> {
//    return listOf(
//        Workstation("1", 0.464f, 0.43f, "Сотрудник: Петр Иванов", "Должность: Backend Developer"),
//        Workstation("2", 0.595f, 0.43f, "Сотрудник: Мария Кузнецова", "Должность: QA Engineer"),
//        Workstation("3", 0.731f, 0.43f, "Сотрудник: Алексей Смирнов", "Должность: UI/UX Designer"),
//        Workstation("4", 0.464f, 0.505f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("5", 0.591f, 0.505f, "Сотрудник: Сергей Федоров", "Должность: DevOps Engineer"),
//        Workstation("6", 0.725f, 0.505f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("7", 0.75f, 0.54f, "Шкаф с фотоприборами", "фотоаппарат, камера, стенд"),
//        Workstation("8", 0.87f, 0.54f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("!", 0.1f, 0.37f, "Переговорка 4 этаж", "Расписание"),
//
//        )
//
//}
//fun getConferenceSixWorkstations(): List<Workstation> {
//    return listOf(
//        Workstation("1", 0.45f, 0.42f, "Сотрудник: Петр Иванов", "Должность: Backend Developer"),
//        Workstation("2", 0.61f, 0.42f, "Сотрудник: Мария Кузнецова", "Должность: QA Engineer"),
//        Workstation("3", 0.79f, 0.42f, "Сотрудник: Алексей Смирнов", "Должность: UI/UX Designer"),
//        Workstation("4", 0.45f, 0.522f, "Сотрудник: Ольга Новикова", "Должность: HR Manager"),
//        Workstation("5", 0.605f, 0.522f, "Сотрудник: Сергей Федоров", "Должность: DevOps Engineer"),
//        Workstation("6", 0.78f, 0.522f, "Сотрудник: Татьяна Морозова", "Должность: Business Analyst"),
//        Workstation("7", 0.83f, 0.6f, "Шкаф с фотоприборами", "фотоаппарат, камера, стенд"),
//        Workstation("!", 0.1f, 0.3f, "Переговорка 6 этаж", "Расписание"),
//
//        )
//
//}