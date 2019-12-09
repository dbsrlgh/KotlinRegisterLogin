package com.example.kotlinregisterlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinregisterlogin.Common.Common
import com.example.kotlinregisterlogin.Model.APIResponse
import com.example.kotlinregisterlogin.Remote.IMyAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    internal lateinit var mService: IMyAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.api

        txt_register.setOnClickListener{ startActivity(Intent(this@MainActivity, RegisterActivity::class.java)) }

        /**28, 41 : Unnecessary non-null assertion (!!) on a non-null receiver of type Response<APIResponse>**/

        btn_login.setOnClickListener {
            authenticateUser(edt_email.text.toString(), edt_password.text.toString())
        }
    }

    private fun authenticateUser(edt_email: String, edt_password: String) {

        mService.loginUser(edt_email, edt_password)
            .enqueue(object: Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,t!!.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if(response!!.body()!!.error){
                        Toast.makeText(this@MainActivity,response.body()!!.error_msg,Toast.LENGTH_SHORT).show()
                    }

                    else{
                        Toast.makeText(this@MainActivity,"Login Success",Toast.LENGTH_SHORT).show()
                    }            }

            })

    }
}
