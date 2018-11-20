package com.test.testclientslist.data.repositories.prospects

import android.util.Log
import com.test.testclientslist.data.models.Client
import com.test.testclientslist.data.networking.APIController
import com.test.testclientslist.data.networking.ServiceVolley
import com.test.testclientslist.utils.Contants
import org.json.JSONObject

class ProspectsRemoteRepository(val token: String) {

    private val service = ServiceVolley()
    private val apiController = APIController(service)

    fun getProspects(onRepoRemoteReadyCallback: OnRepoRemoteReadyCallback){

        apiController.getJsonArray(Contants.CLIENTS, token){ response ->

            if(response != null){
                val list: ArrayList<Client> = ArrayList()
                var temp: JSONObject

                for (i in 0 until response.length()){
                    temp = response.getJSONObject(i)
                    list.add(Client(
                        temp.getString("id"),
                        temp.getString("name"),
                        temp.getString("surname"),
                        temp.getString("telephone"),
                        temp.getString("schProspectIdentification"),
                        temp.getString("address"),
                        temp.getInt("statusCd"),
                        temp.getString("zoneCode"),
                        temp.getString("neighborhoodCode"),
                        temp.getString("cityCode"),
                        temp.getString("sectionCode"),
                        temp.getInt("roleId"),
                        temp.getString("appointableId"),
                        temp.getString("rejectedObservation"),
                        temp.getString("observation"),
                        temp.getBoolean("disable"),
                        temp.getBoolean("visited"),
                        temp.getBoolean("callcenter"),
                        temp.getBoolean("acceptSearch"),
                        temp.getString("campaignCode"),
                        temp.getString("userId")))
                }

                onRepoRemoteReadyCallback.onDataReady(list)

            }
        }
    }

}

interface OnRepoRemoteReadyCallback {
    fun onDataReady(data: ArrayList<Client>)
}