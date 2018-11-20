package com.test.testclientslist.data.repositories.prospects

import com.test.testclientslist.data.localDB.SqliteHelper
import com.test.testclientslist.data.models.Client
import com.test.testclientslist.utils.NetManager

class ProspectsRepository(val netManager: NetManager, val token: String, val db: SqliteHelper) {

    val remoteDataSource = ProspectsRemoteRepository(token)
    val localDataSource = ProspectsLocalRepository(db)

    fun getProspects(onRepositoryReadyCallback: OnRepositoryReadyCallback){
        netManager.isConnectedToInternet?.let {
            if(it){
                remoteDataSource.getProspects(object : OnRepoRemoteReadyCallback{
                    override fun onDataReady(data: ArrayList<Client>) {
                        localDataSource.saveProspects(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }

                })
            }else{
                localDataSource.getProspects(object : OnRepoLocalReadyCallBack{
                    override fun onLocalDataReady(data: ArrayList<Client>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }

                })
            }
        }
    }
}

interface OnRepositoryReadyCallback{
    fun onDataReady(data: ArrayList<Client>)
}