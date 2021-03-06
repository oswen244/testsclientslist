package com.test.testclientslist.data.networking

import org.json.JSONArray
import org.json.JSONObject

interface ServiceInterface {
    fun get(path: String, token: String, completionHandler: (response: JSONObject?) -> Unit )
    fun getJsonArray(path: String, token: String, completionHandler: (response: JSONArray?) -> Unit )
    fun postJson(path: String, token: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)
    fun postString(path: String, token: String, params: HashMap<String, Any>, completionHandler: (response: String?) -> Unit)
}