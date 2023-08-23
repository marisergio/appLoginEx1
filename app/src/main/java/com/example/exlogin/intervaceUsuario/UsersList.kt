package com.example.exlogin.intervaceUsuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.exlogin.R
import com.example.exlogin.Users
import com.example.exlogin.databinding.ActivityCadastroBinding
import com.example.exlogin.databinding.ActivityMainBinding
import com.example.exlogin.databinding.ActivityUsersListBinding
import com.example.exlogin.util.EndPoint
import com.example.exlogin.util.ServerHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersList : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityUsersListBinding
    private lateinit var usuarios : ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.listUsers.setOnClickListener(this)

        binding.listUsers.setOnItemClickListener{parent, view, position, id->
            binding.editNome.setText(usuarios.get(position).nome)
        }

        carregarUsers()
    }

    override fun onClick(v: View) {
       // if(v.id == R.id.list_users){

      //  }
    }

    fun carregarUsers(){

        val retrofitClient = ServerHttp
            .getRetrofitInstance("http://200.137.6.228/agenda/")

        val endpoint = retrofitClient.create(EndPoint::class.java)
        //val callback = endpoint.getUsers()

        val callback = endpoint.getUsers()

        callback.enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                usuarios = ArrayList<Users>()
                response.body()?.forEach {
                    usuarios.add(it)
                   // Toast.makeText(baseContext, it.nome, Toast.LENGTH_SHORT).show()
                }

                val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, usuarios)
                binding.listUsers.adapter = adapter
            }
        })
    }
}