package com.example.exlogin.util


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerHttp {

    companion object {

        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         */
        fun getRetrofitInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    /*fun post(dados: String) : String {
        val URL = "http://200.137.6.228/agenda/agenda-procedimento"

       // val headerHttp = "application/json; charset=utf-8".toMediaTypeOrNull()

        val client = OkHttpClient()

       // val body = dados.toRequestBody(headerHttp)

        val requestBody = dados.toRequestBody("application/json".toMediaTypeOrNull())

       // val request = Request.Builder().url(URL).post(body).build()
       // val response = client.newCall(request).execute()

        val request = Request.Builder()
            .url(URL)
            .post(requestBody)
            .build()

        var dadosRetorno : String = ""

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Erro: ao salvar")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    dadosRetorno = response.body?.string().toString()
                    println(dadosRetorno)

                } else {
                    println("Erro na resposta: ${response.code}")
                }
            }
        })



        return dadosRetorno


    }*/

}