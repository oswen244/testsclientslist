package com.test.testclientslist.data.models

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
             var appointedTableId: Int,
             var rejectedObservation: String,
             var observation: String,
             var disable: Boolean,
             var visited: Boolean,
             var callcenter: Boolean,
             var acceptSearch: Boolean,
             var campaignCode: String,
             var userId: Int) {

}