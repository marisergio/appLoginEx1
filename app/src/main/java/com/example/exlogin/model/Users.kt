package com.example.exlogin.model

data class Users(
    var id : String?,
    var nome : String
    ){
    override fun toString(): String {
        return nome
    }
}


