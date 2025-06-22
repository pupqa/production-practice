package com.bober.managerfull.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.bober.managerfull.model.FloorModel
import com.bober.managerfull.model.Workstation
import kotlinx.coroutines.tasks.await

class WorkstationService {
    private val database = Firebase.firestore
    private val collectionName = "office"

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
//                    )
//                }
//
//            )
//            database.collection(collectionName).document(floorId).set(floorData).await()
//        }
//    }

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
    ) {
        Log.d("WorkstationService", "Updating workstation with params: " +
                "floorId=$floorId, " +
                "workstationId=$workstationId, " +
                "employeeName=$employeeName, " +
                "position=$position")

        val floorRef = database.collection(collectionName).document(floorId)
        val floorData = floorRef.get().await().toObject(FloorModel::class.java)

        Log.d("WorkstationService", "Current floor data: ${floorData?.workstations?.find { it.id == workstationId }}")

        val updateWorkstation = floorData?.workstations?.map {
            if (it.id == workstationId) {
                val updatedWorkstation = it.copy(
                    employeeName = employeeName,
                    position = position,
                    isBusy = employeeName.isNotEmpty()
                )
                Log.d("WorkstationService", "Updated workstation: $updatedWorkstation")
                updatedWorkstation
            } else {
                it
            }
        }

        if (updateWorkstation != null) {
            Log.d("WorkstationService", "Sending to Firestore: $updateWorkstation")
            floorRef.update(
                "workstations",
                updateWorkstation
            ).await()
            Log.d("WorkstationService", "Update successful for workstation $workstationId on floor $floorId")
        } else {
            Log.e("WorkstationService", "Failed to update - workstation data is null")
        }
    }
}