package com.devdreamerx.cinehub.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class SearchSpecificShow constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val score: Double,
    val show: Show
) : Serializable
