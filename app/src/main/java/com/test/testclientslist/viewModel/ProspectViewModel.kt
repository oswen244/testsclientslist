package com.test.testclientslist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.test.testclientslist.data.localDB.SqliteHelper
import com.test.testclientslist.data.models.Client
import com.test.testclientslist.data.repositories.prospects.OnRepositoryReadyCallback
import com.test.testclientslist.data.repositories.prospects.ProspectsRepository
import com.test.testclientslist.utils.Contants
import com.test.testclientslist.utils.LZString
import com.test.testclientslist.utils.LocalStorage
import com.test.testclientslist.utils.NetManager
import org.json.JSONObject

class ProspectViewModel(application: Application) : AndroidViewModel(application) {

    val localStorage: LocalStorage = LocalStorage(application)

    val db: SqliteHelper = SqliteHelper(application, Contants.DB, null, 1)

    val token = LZString.decompress(localStorage.token)

    var repository: ProspectsRepository = ProspectsRepository(NetManager(application), token, db)

    var empty = ObservableField(false)

    var isLoading = ObservableField(false)

    var prospects = MutableLiveData<ArrayList<Client>>()

    fun loadProspects(){
        isLoading.set(true)
        repository.getProspects(object : OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Client>) {
                isLoading.set(false)

                if(data.isEmpty()){
                    empty.set(true)
                }
                prospects.value = data
            }

        })
    }

    fun getOneProspect(pos: String): String{
        val prospect = db.getItem("prospects", pos)
        return  prospect
    }
}