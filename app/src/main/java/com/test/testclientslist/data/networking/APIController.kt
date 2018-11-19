package com.test.testclientslist.data.networking

import org.json.JSONObject

class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun get(path: String, token: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.get(path, token, completionHandler)
    }

    override fun postJson(path: String, token: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.postJson(path, token, params, completionHandler)
    }

    override fun postString(path: String, token: String, params: HashMap<String, Any>, completionHandler: (response: String?) -> Unit) {
        service.postString(path, token, params, completionHandler)
    }

}