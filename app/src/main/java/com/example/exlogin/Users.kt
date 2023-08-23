package com.example.exlogin

data class Users(
    var id : String?,
    var nome : String
    ){
    override fun toString(): String {
        return nome
    }
}


