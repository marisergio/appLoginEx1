package com.example.exlogin.intervaceUsuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exlogin.R
import com.example.exlogin.Users
import com.example.exlogin.databinding.ActivityCadastroBinding
import com.example.exlogin.util.EndPoint
import com.example.exlogin.util.ServerHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import org.jetbrains.anko.doAsync


class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener(this)
        binding.imgVoltar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_salvar){

            println("passou aqui")
            val nome = binding.editNomeAluno.text.toString()
            val email = binding.editLoginAluno.text.toString()
            val senha = binding.editSenhaAluno.text.toString()

            val user = Users(null,nome)

            val retrofitClient = ServerHttp
                .getRetrofitInstance("http://200.137.6.228/agenda/")

            val endpoint = retrofitClient.create(EndPoint::class.java)
            //val callback = endpoint.getUsers()

            val callback = endpoint.addUsers(user)

            // on below line we are executing our method.

            // on below line we are executing our method.
            callback.enqueue(object : Callback<Users?> {
                override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                    // this method is called when we get response from our api.
                    Toast.makeText(this@CadastroActivity, "Data added to API", Toast.LENGTH_SHORT)
                        .show()

                    // below line is for hiding our progress bar.
                    //loadingPB.setVisibility(View.GONE)

                    // on below line we are setting empty text
                    // to our both edit text.
                    //  jobEdt.setText("")
                    //   nameEdt.setText("")

                    // we are getting response from our body
                    // and passing it to our modal class.
                    val responseFromAPI: Users? = response.body()

                    binding.editNomeAluno.setText(responseFromAPI?.id)
                }


                override fun onFailure(call: Call<Users?>, t: Throwable) {
                    // setting text to our text view when
                    // we get error response from API.
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                   // responseTV.setText("Error found is : " + t.message)
                }
            })

            /*
            SALVANDO EM MEMORIA
            AdmDadosUsuario(this).salvar(Constantes.KEY.USER_NAME,email)

            Mock().cadastrarUsuario(nome,email,senha)
            */

            startActivity(Intent(this, MainActivity::class.java))

        }else if(view.id == R.id.img_voltar){
            startActivity(Intent(this,LoginActivity::class.java))
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