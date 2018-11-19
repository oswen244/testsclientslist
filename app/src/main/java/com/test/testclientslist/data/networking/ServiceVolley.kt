package com.test.testclientslist.data.networking

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.test.testclientslist.utils.Contants

import org.json.JSONObject


class ServiceVolley: ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName

    val basePath = Contants.URL

    override fun get(path: String, token: String, completionHandler: (response: JSONObject?) -> Unit) {

        val jsonRequest = object : JsonObjectRequest(Method.GET, basePath +  path + token, null,
                Response.Listener { response ->
                    Log.d(TAG, "/get request OK response: $response")
                    completionHandler(response)
                },

                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/get resquest fail error: $error")
                    if (error.networkResponse.statusCode == 422){

                        // Response just return status 422 for this error
                        var errorResp: JSONObject = JSONObject("{\"code\":133,\"error\":\"El correo que ha ingresado no se encuentra registrado\"}")
                        completionHandler(errorResp)
                    }
                    completionHandler(null)
                }) {

        }

        BackendVolley.instance?.addToRequestQueue(jsonRequest, TAG)
    }

    override fun postString(path: String, token: String, params: HashMap<String, Any>, completionHandler: (response: String?) -> Unit) {

        val jsonObjRequest = object : StringRequest(Method.POST, basePath + path,

                Response.Listener { response ->
                    Log.d(TAG, "/post request OK response: $response")
                    completionHandler(response)
                },

                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post resquest fail error: $error")
                    Log.e(TAG, "/post resquest fail error: $error")
                    completionHandler(null)
                }) {

            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val p = HashMap<String, String>()
                p.put("email", params["email"] as String)
                p.put("password", params["password"] as String)
                return p
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjRequest, TAG)
    }

    override fun postJson(path: String, token: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {

        val jsonObjRequest = object : JsonObjectRequest(Method.POST, basePath + path, params,
                Response.Listener { response ->
                    Log.d(TAG, "/post request OK response: $response")
                    completionHandler(response)
                },

                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post resquest fail error: $error")
                    Log.e(TAG, "/post resquest fail error: $error")
                    completionHandler(null)
                }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjRequest, TAG)
    }

}
