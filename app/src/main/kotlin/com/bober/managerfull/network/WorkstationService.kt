package com.bober.managerfull.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.bober.managerfull.model.FloorModel
import com.bober.managerfull.model.FloorModelWardrobe
import com.bober.managerfull.model.Workstation
import kotlinx.coroutines.tasks.await

class WorkstationService {
    private val database = Firebase.firestore
    private val collectionName = "office"
    private val collectionName2 = "officeWardrobe"

//    suspend fun initializeWorkstation() {
//        val floorsData = mapOf(
//            "coworking" to getCoworkingWorkstations(),
//            "floor3" to getFloorThreeWorkstation(),
//            "floor4" to getFloorFourWorkstation(),
//            "floor6" to getFloorSixWorkstation(),
//            "conference4" to getConferenceFourWorkstations(),
//            "conference6" to getConferenceSixWorkstations(),
//        )
//        floorsData.forEach { (floorId, workstations) ->
//            val floorData = hashMapOf(
//                "floorName" to when (floorId) {
//                    "coworking" -> "Коворкинг"
//                    "floor3" -> "3 Этаж"
//                    "floor4" -> "4 Этаж"
//                    "floor6" -> "6 Этаж"
//                    "conference4" -> "Переговорка 4 этаж"
//                    "conference6" -> "Переговорка 6 этаж"
//                    else -> floorId
//                },
//                "workstations" to workstations.map {
//                    mapOf(
//                        "id" to it.id,
//                        "x" to it.x,
//                        "y" to it.y,
//                        "employeeName" to "",
//                        "position" to "",
//                        "isBusy" to false,
//                        "number" to ""
//                    )
//                }
//
//            )
//            database.collection(collectionName).document(floorId).set(floorData).await()
//        }
//    }
//
//    suspend fun initializeWardrobe() {
//        val floorsData = mapOf(
//            "coworking" to getWardrobeCoworking(),
//            "floor3" to getWardrobeThree(),
//            "floor4" to getWardrobeFour(),
//            "floor6" to getWardrobeSix(),
//            "conference4" to getWardrobeFourConferense(),
//            "conference6" to getWardrobeSixConferense(),
//        )
//        floorsData.forEach { (floorId, wardrobe) ->
//            val floorData = hashMapOf(
//                "floorName" to when (floorId) {
//                    "coworking" -> "Коворкинг"
//                    "floor3" -> "3 Этаж"
//                    "floor4" -> "4 Этаж"
//                    "floor6" -> "6 Этаж"
//                    "conference4" -> "Переговорка 4 этаж"
//                    "conference6" -> "Переговорка 6 этаж"
//                    else -> floorId
//                },
//                "wardrobe" to wardrobe.map {
//                    mapOf(
//                        "id" to it.id,
//                        "x" to it.x,
//                        "y" to it.y,
//                        "employeeName" to "",
//                        "position" to "",
//                        "isBusy" to false,
//                        "number" to ""
//
//                    )
//                }
//
//            )
//            database.collection(collectionName2).document(floorId).set(floorData).await()
//        }
//    }

    suspend fun getCoworkingWardrobe(): FloorModelWardrobe? {
        return database.collection(collectionName2).document("coworking").get().await()
            .toObject(FloorModelWardrobe::class.java)
    }

    suspend fun getFloorThreeWardrobe(): FloorModelWardrobe? {
        return database.collection(collectionName2).document("floor3").get().await()
            .toObject(FloorModelWardrobe::class.java)
    }

    suspend fun getFloorFourWardrobe(): FloorModelWardrobe? {
        return database.collection(collectionName2).document("floor4").get().await()
            .toObject(FloorModelWardrobe::class.java)
    }

    suspend fun getFloorSixWardrobe(): FloorModelWardrobe? {
        return database.collection(collectionName2).document("floor6").get().await()
            .toObject(FloorModelWardrobe::class.java)
    }

    suspend fun getConferenceFourWardrobe(): FloorModelWardrobe? {
        return database.collection(collectionName2).document("conference4").get().await()
            .toObject(FloorModelWardrobe::class.java)
    }

    suspend fun getConferenceSixWardrobe(): FloorModelWardrobe? {
        return database.collection(collectionName2).document("conference6").get().await()
            .toObject(FloorModelWardrobe::class.java)
    }


    suspend fun getCoworkingWorkStations(): FloorModel? {
        val dataGet = database.collection(collectionName).document("coworking").get().await()

        return dataGet.toObject(FloorModel::class.java)

    }

    suspend fun getFloorThreeWorkStation(): FloorModel? {
        val dataGet = database.collection(collectionName).document("floor3").get().await()

        return dataGet.toObject(FloorModel::class.java)

    }

    suspend fun getFloorFourWorkStation(): FloorModel? {
        val dataGet = database.collection(collectionName).document("floor4").get().await()

        return dataGet.toObject(FloorModel::class.java)

    }

    suspend fun getFloorSixWorkStation(): FloorModel? {
        val dataGet = database.collection(collectionName).document("floor6").get().await()

        return dataGet.toObject(FloorModel::class.java)

    }

    suspend fun getConferenceFourWorkStations(): FloorModel? {
        val dataGet = database.collection(collectionName).document("conference4").get().await()

        return dataGet.toObject(FloorModel::class.java)

    }

    suspend fun getConferenceSixWorkStations(): FloorModel? {
        val dataGet = database.collection(collectionName).document("conference6").get().await()

        return dataGet.toObject(FloorModel::class.java)

    }

    suspend fun updateWorkstation(
        floorId: String,
        workstationId: String,
        employeeName: String,
        position: String,
        number: String = ""
    ) {
        Log.d(
            "WorkstationService", "Updating workstation with params: " +
                    "floorId=$floorId, " +
                    "workstationId=$workstationId, " +
                    "employeeName=$employeeName, " +
                    "position=$position, " +
                    "number=$number"
        )

        val floorRef = database.collection(collectionName).document(floorId)

        // Получаем текущие данные
        val floorSnapshot = floorRef.get().await()
        val workstations = floorSnapshot.get("workstations") as? List<Map<String, Any>>
            ?: throw IllegalStateException("Workstations data is missing")

        // Обновляем нужную рабочую станцию
        val updatedWorkstations = workstations.map { workstation ->
            if (workstation["id"] == workstationId) {
                mutableMapOf<String, Any>().apply {
                    putAll(workstation)
                    put("employeeName", employeeName)
                    put("position", position)
                    put("isBusy", employeeName.isNotEmpty())
                    if (number.isNotEmpty()) {
                        put("number", number)
                    }
                }
            } else {
                workstation
            }
        }

        // Обновляем документ в Firestore
        floorRef.update("workstations", updatedWorkstations).await()
        Log.d("WorkstationService", "Workstation $workstationId updated successfully")
    }
    suspend fun updateWardrobe(
        floorId: String,
        wardrobeId: String,
        wardrobeName: String,
        content: String,
        additionalFields: List<String>
    ) {
        val floorRef = database.collection(collectionName2).document(floorId)
        val floorSnapshot = floorRef.get().await()
        val wardrobes = floorSnapshot.get("wardrobe") as? List<Map<String, Any>>
            ?: throw IllegalStateException("Wardrobes data is missing")

        val updatedWardrobes = wardrobes.map { wardrobe ->
            if (wardrobe["id"] == wardrobeId) {
                mutableMapOf<String, Any>().apply {
                    putAll(wardrobe)
                    put("wardrobeName", wardrobeName)
                    put("content", content)
                    put("isBusy", wardrobeName.isNotEmpty())
                    // Сохраняем дополнительные поля как массив
                    put("additionalFields", additionalFields)
                }
            } else {
                wardrobe
            }
        }

        floorRef.update("wardrobe", updatedWardrobes).await()
    }
}