package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.domain.entity.DestinationEntity
import com.cmc.mytravelcompany.domain.repository.DestinationRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : DestinationRepository {

    // Caché en memoria para evitar peticiones repetidas a Firestore
    private var cachedDestinations: List<DestinationEntity>? = null

    override suspend fun getDestinations(): List<DestinationEntity> {
        // 1. Si ya tenemos los datos en memoria, los devolvemos instantáneamente
        cachedDestinations?.let { return it }

        // 2. Si no, consultamos Firestore una sola vez (petición one-shot)
        return try {
            val snapshot = firestore.collection("destination")
                .get()
                .await()
            
            val destinations = snapshot.documents.mapNotNull { doc ->
                // Mapeamos a la entidad y nos aseguramos de asignar el ID del documento
                doc.toObject(DestinationEntity::class.java)?.copy(id = doc.id)
            }

            // Guardamos en la caché
            cachedDestinations = destinations
            destinations
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getDestinationById(id: String): DestinationEntity? {
        // Si entramos directamente al detalle, cargamos la lista completa primero
        if (cachedDestinations == null) {
            getDestinations()
        }

        // Filtramos en memoria
        return cachedDestinations?.find { it.id == id }
    }
}
