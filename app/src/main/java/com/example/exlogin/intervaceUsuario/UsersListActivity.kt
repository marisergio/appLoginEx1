package com.example.exlogin.intervaceUsuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.exlogin.model.Users
import com.example.exlogin.databinding.ActivityUsersListBinding
import com.example.exlogin.util.EndPoint
import com.example.exlogin.util.ServerHttp
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityUsersListBinding
    private lateinit var usuarios : ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.listUsers.setOnItemClickListener{parent, view, position, id->
            binding.editNome.setText(usuarios.get(position).nome)
            val intent = Intent(this, UsersExibirActivity::class.java)
            intent.putExtra("idCadastro",usuarios.get(position).id)
            startActivity(intent)
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


        //val pageNumber = 1 // Página que você deseja obter
        val call = endpoint.getPaginatedItems()

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val jsonObject = response.body()
                    if (jsonObject != null) {
                        val contentArray = jsonObject.getAsJsonArray("content")

                        usuarios = ArrayList<Users>()

                        for (jsonElement in contentArray) {
                            val jsonObject = jsonElement.asJsonObject

                            val id = jsonObject.get("id").asString
                            val nome = jsonObject.get("nome").asString
                            usuarios.add(Users(id,nome))
                        }


                        val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, usuarios)
                        binding.listUsers.adapter = adapter
                    } else {
                        Toast.makeText(this@UsersListActivity, "RESULTADO VAZIO", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@UsersListActivity, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                binding.editNome.setText(t.message)
                Toast.makeText(this@UsersListActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })


        //CONSUMINDO API SEM PAGINAÇÃO
      /*  val callback = endpoint.getUsers()

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
        })*/
    }
}