package com.example.exlogin.intervaceUsuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.exlogin.R
import com.example.exlogin.data.Mock
import com.example.exlogin.databinding.ActivityCadastroBinding
import com.example.exlogin.util.AdmDadosUsuario
import com.example.exlogin.util.Constantes


class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_salvar){
            val nome = binding.editNomeAluno.text.toString()
            val email = binding.editLoginAluno.text.toString()
            val senha = binding.editSenhaAluno.text.toString()

            AdmDadosUsuario(this).salvar(Constantes.KEY.USER_NAME,email)
            Mock().cadastrarUsuario(nome,email,senha)

            startActivity(Intent(this, MainActivity::class.java))

        }
    }


}


































/*class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        if(view.id == R.id.btn_salvar){
            val nome = binding.editNomeAluno.text.toString()
            val email = binding.editLoginAluno.text.toString()
            val senha = binding.editSenhaAluno.text.toString()

            Mock().cadastrarUsuario(nome,email,senha)
            AdmDadosUsuario(this).salvar(Constantes.KEY.USER_NAME,email)

        }

        //binding.btnSalvar.setText(AdmDadosUsuario(this).buscar(Constantes.KEY.USER_NAME))

        startActivity(Intent(this,MainActivity::class.java))
    }
}*/