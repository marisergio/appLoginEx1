package com.example.exlogin.intervaceUsuario

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exlogin.data.Mock
import com.example.exlogin.databinding.ActivityLoginBinding
import com.example.exlogin.databinding.ActivityMainBinding
import com.example.exlogin.util.AdmDadosUsuario
import com.example.exlogin.util.Constantes

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setMensagem()
    }

    fun setMensagem(){
        val login = AdmDadosUsuario(this).buscar(Constantes.KEY.USER_NAME);
        val nome = Mock().getNome(login)
        binding.textMensagem.text = "Olá, $nome!"
    }
}