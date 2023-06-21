package com.example.exlogin.util

import android.content.Context
import android.content.SharedPreferences

class AdmDadosUsuario(context: Context) {

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)

    fun salvar(key : String, valor : String){
       // if(!buscar(Constantes.KEY.USER_NAME).equals("")){
       //     sharedPreferences.edit().remove(Constantes.KEY.USER_NAME).apply()
      //  }
        sharedPreferences.edit().putString(key,valor).apply()
    }

    fun buscar(key : String) : String{
        return sharedPreferences.getString(Constantes.KEY.USER_NAME,"") ?: ""
    }





}