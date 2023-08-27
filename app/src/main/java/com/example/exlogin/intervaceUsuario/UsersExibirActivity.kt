package com.example.exlogin.intervaceUsuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.exlogin.R
import com.example.exlogin.databinding.ActivityUsersExibirBinding
import com.example.exlogin.databinding.ActivityUsersListBinding
import com.example.exlogin.model.Users
import com.example.exlogin.util.EndPoint
import com.example.exlogin.util.ServerHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersExibirActivity : AppCompatActivity() {

    lateinit var binding: ActivityUsersExibirBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsersExibirBinding.inflate(layoutInflater)

        setContentView(binding.root)

        carregarUser()

      //  binding.textNomeAluno.text = intent.getStringExtra("idCadastro")
    }

    fun carregarUser(){
        val retrofitClient = ServerHttp
            .getRetrofitInstance("http://200.137.6.228/agenda/")

        val endpoint = retrofitClient.create(EndPoint::class.java)
        val idUser = intent.getStringExtra("idCadastro")
        val callback = endpoint.getUserById(idUser)

        callback.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {

                val responseFromAPI: Users? = response.body()

                binding.textNomeAluno.text = responseFromAPI?.nome
            }


            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()

            }
        })
    }
}