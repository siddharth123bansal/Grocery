package com.example.grocery.RazorPay

class RazorPayClient {



//    fun createRazorPayOrder(amount: Int, currency: String, context: Context, completionHandler: (String) -> Unit, errorHandler: (Exception) -> Unit) {
//        val url = "http://192.168.1.36:3000/create-order"
//        val queue: RequestQueue = Volley.newRequestQueue(context)
//
//        val body = JSONObject()
//        body.put("amount", amount)
//        body.put("currency", currency)
//
//        val request = JsonObjectRequest(
//            Request.Method.POST, url, body,
//            { response ->
//                try {
//                    val orderId = response.getString("orderId")
//                    completionHandler(orderId)
//                } catch (e: Exception) {
//                    errorHandler(e)
//                }
//            },
//            { error ->
//                errorHandler(error)
//            }
//        )
//
//        queue.add(request)
//    }
}