package com.test.testclientslist.data.repositories.prospects

import com.test.testclientslist.data.models.Client
import com.test.testclientslist.utils.NetManager

class ProspectsRepository(val netManager: NetManager, val token: String) {

    val remoteDataSource = ProspectsRemoteRepository(token)
    val localDataSource = ProspectsLocalRepository()

    fun getProspects(onRepositoryReadyCallback: OnRepositoryReadyCallback){
        netManager.isConnectedToInternet?.let {
            if(it){
                remoteDataSource.getProspects(object : OnRepoRemoteReadyCallback{
                    override fun onDataReady(data: ArrayList<Client>) {
                        // TODO: Local save
                        onRepositoryReadyCallback.onDataReady(data)
                    }

                })
            }else{
                // Local list
            }
        }
    }
}

interface OnRepositoryReadyCallback{
    fun onDataReady(data: ArrayList<Client>)
}