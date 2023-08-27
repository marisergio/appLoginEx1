package com.example.exlogin.intervaceUsuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.exlogin.R
import com.example.exlogin.data.Mock
import com.example.exlogin.databinding.ActivityLoginBinding
import com.example.exlogin.util.AdmDadosUsuario
import com.example.exlogin.util.Constantes

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        isLogado()

        binding.btnEntrar.setOnClickListener(this)
        binding.textCadastrar.setOnClickListener(this)
        binding.textListar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id== R.id.btn_entrar){
            logar()
        }else if(view.id == R.id.text_cadastrar){
            startActivity(Intent(this,CadastroActivity::class.java))
        }else if(view.id == R.id.text_listar){
            startActivity(Intent(this,UsersListActivity::class.java))
        }
    }

    private fun logar(){

        val login = binding.editLogin.text.toString()
        val senha = binding.editSenha.text.toString()

        if(isLogar(login, senha)) {
            if(Mock().verificarLogin(login, senha)) {
                AdmDadosUsuario(this).salvar(Constantes.KEY.USER_NAME,login)
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Dados incorretos.",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Preencha os campos corretamente",Toast.LENGTH_SHORT).show()
        }
    }

    private fun isLogar(login : String, senha : String) : Boolean{
        return login!="" && senha != ""
    }

    private fun isLogado(){
        if(AdmDadosUsuario(this).buscar(Constantes.KEY.USER_NAME)!=""){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}