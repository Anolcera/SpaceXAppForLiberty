package com.whichonespink.spacex.domain.repository

import com.plcoding.stockmarketapp.util.Resource
import com.whichonespink.spacex.domain.model.Launch
import com.whichonespink.spacex.domain.model.Ship
import kotlinx.coroutines.flow.Flow

interface SpaceXRepository {
    suspend fun getShips(): Flow<Resource<List<Ship>>>

    suspend fun getLaunch(flightNumber: Int): Flow<Resource<Launch>>
}