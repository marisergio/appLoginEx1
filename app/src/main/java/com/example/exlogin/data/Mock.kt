package com.example.exlogin.data

data class Usuario(var nome : String, var login : String, var senha : String)

class Mock {

    object bdUsuarios {
        var usuarios: MutableList<Usuario> = mutableListOf(
            Usuario("Igor", "igor", "igor123"),
            Usuario("Ilton", "ilton", "desenho"),
            Usuario("Simone", "simone", "senha"),
            Usuario("Marisergio", "marisergio", "123"),
            Usuario("Guilherme", "guilherme", "123")
        )
    }


    fun verificarLogin(login : String, senha: String) : Boolean{

        for (u : Usuario in bdUsuarios.usuarios){
            if(u.login==login && u.senha==senha){
                return true
            }
        }

        return false

       // val usuariosLogados = usuarios.filter { it.login.equals(login) && it.senha.equals(senha) }

       // return !usuariosLogados.isNullOrEmpty()

    }

    fun getNome(login : String):String{

        var nome : String = ""

        bdUsuarios.usuarios.forEach{
            if(it.login.equals(login)) {
                nome = it.nome
            }
        }
        return nome
    }

    fun cadastrarUsuario(nome : String, email : String, senha : String)
    {
        val usuario = Usuario(nome, email, senha)
        bdUsuarios.usuarios.add(usuario)
    }

}