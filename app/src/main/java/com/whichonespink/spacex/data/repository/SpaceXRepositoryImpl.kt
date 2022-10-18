package com.whichonespink.spacex.data.repository

import android.util.Log
import com.plcoding.stockmarketapp.util.Resource
import com.whichonespink.spacex.data.mapper.toLaunch
import com.whichonespink.spacex.data.mapper.toShip
import com.whichonespink.spacex.data.remote.SpaceXApi
import com.whichonespink.spacex.domain.model.Launch
import com.whichonespink.spacex.domain.model.Ship
import com.whichonespink.spacex.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpaceXRepositoryImpl @Inject constructor(
    private val api: SpaceXApi
) : SpaceXRepository {

    override suspend fun getShips(): Flow<Resource<List<Ship>>> {

        return flow {
            emit(Resource.Loading(true))
            val receiveShips = try{
                api.fetchShips()
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data ${e.printStackTrace()}"))
                null
            }

            receiveShips?.let { shipsResponse ->
                Log.d(TAG, "$shipsResponse")
                if(shipsResponse.isSuccessful && shipsResponse.body() != null){
                    emit(Resource.Success(
                        data = shipsResponse.body()!!.map {
                            it.toShip()
                        }
                    ))
                    emit(Resource.Loading(false))
                }else{
                    emit(Resource.Error("Failed To Fetch Data ${shipsResponse.errorBody()}"))
                }

            }
        }
    }

    override suspend fun getLaunch(flightNumber: Int): Flow<Resource<Launch>> {
        return flow {
            emit(Resource.Loading(true))
            val recieveLaunch = try {
                api.fetchLaunch(flightNumber)

            }catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            }catch (e: Exception){
                emit(Resource.Error("Couldn't load data"))
                null
            }

            recieveLaunch?.let {launch ->
                emit(Resource.Success(data = launch.toLaunch()))
                emit(Resource.Loading(false))
            }
        }
    }

    companion object{
        const val TAG = "SpaceXRepositoryImpl-TAG"
    }

}