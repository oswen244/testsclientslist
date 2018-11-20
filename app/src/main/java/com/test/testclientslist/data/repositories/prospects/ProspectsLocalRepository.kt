package com.test.testclientslist.data.repositories.prospects

import com.google.gson.Gson
import com.test.testclientslist.data.localDB.SqliteHelper
import com.test.testclientslist.data.models.Client
import org.json.JSONObject

class ProspectsLocalRepository(val db:SqliteHelper) {

    val table: String = "prospects"

    fun getProspects(onRepoLocalReadyCallBack: OnRepoLocalReadyCallBack){

        val prospectList = ArrayList<Client>()
        val arrayList = db.getItems(table)

        var prospect: JSONObject

        for (i in 0 until arrayList.size){
            prospect = JSONObject(arrayList[i])
            prospectList.add(Client(
                prospect.getString("id"),
                prospect.getString("name"),
                prospect.getString("lastname"),
                prospect.getString("phone"),
                prospect.getString("propectId"),
                prospect.getString("address"),
                prospect.getInt("statusCode"),
                prospect.getString("zoneCode"),
                prospect.getString("neighborhoodCode"),
                prospect.getString("cityCode"),
                prospect.getString("sectionCode"),
                prospect.getInt("roleId"),
                prospect.getString("appointedTableId"),
                prospect.getString("rejectedObservation"),
                prospect.getString("observation"),
                prospect.getBoolean("disable"),
                prospect.getBoolean("visited"),
                prospect.getBoolean("callcenter"),
                prospect.getBoolean("acceptSearch"),
                prospect.getString("campaignCode"),
                prospect.getString("userId")))

        }

        onRepoLocalReadyCallBack.onLocalDataReady(prospectList)

    }

    fun saveProspects(arrayList: ArrayList<Client>){
        db.deleteAllRows(table)
        var prospect: Client
        val gson = Gson()
        var temp: String
        for (i in 0 until arrayList.size){
            prospect = arrayList[i]
            temp = gson.toJson(prospect)
            db.addItem(table, temp)
        }
    }
}

interface OnRepoLocalReadyCallBack{
    fun onLocalDataReady(data: ArrayList<Client>)
}