package com.test.testclientslist.data.models

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView

class Client(var id: String,
             var name: String,
             var lastname: String,
             var phone: String,
             var propectId: String,
             var address: String,
             var statusCode: Int,
             var zoneCode: String,
             var neighborhoodCode: String,
             var cityCode: String,
             var sectionCode: String,
             var roleId: Int,
             var appointedTableId: String,
             var rejectedObservation: String,
             var observation: String,
             var disable: Boolean,
             var visited: Boolean,
             var callcenter: Boolean,
             var acceptSearch: Boolean,
             var campaignCode: String,
             var userId: String) {


    companion object {

        @BindingAdapter("android:statusCode")
        @JvmStatic
        fun getStatus(view: View, code: Int){
            val txt: TextView = view as TextView
            var status = ""
            when(code){
                0 -> status = "Pending"
                1 -> status = "Approved"
                2 -> status = "Accepted"
                3 -> status = "Rejected"
                4 -> status = "Disabled"
            }

            txt.text = status
        }
    }

}
