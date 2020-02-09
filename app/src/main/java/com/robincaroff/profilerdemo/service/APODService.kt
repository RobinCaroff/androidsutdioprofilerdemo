package com.robincaroff.profilerdemo.service

import com.robincaroff.profilerdemo.model.APOD

interface APODService {
    suspend fun getAPODs(): List<APOD>
}