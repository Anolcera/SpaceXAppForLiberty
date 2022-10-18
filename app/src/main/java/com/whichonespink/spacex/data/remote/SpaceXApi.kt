package com.whichonespink.spacex.data.remote

import com.whichonespink.spacex.data.remote.dto.LaunchDto
import com.whichonespink.spacex.data.remote.dto.ShipDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {
    @GET("v3/ships")
    suspend fun fetchShips(): Response<List<ShipDto>>

    @GET("v3/launches/{flight_number}")
    suspend fun fetchLaunch(
        @Path("flight_number")
        flightNumber: Int
    ): LaunchDto


    companion object {
        const val BASE_URL = "https://api.spacexdata.com/"
    }
}