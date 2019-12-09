package com.example.kotlinregisterlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinregisterlogin.Common.Common
import com.example.kotlinregisterlogin.Model.APIResponse
import com.example.kotlinregisterlogin.Remote.IMyAPI
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    internal lateinit var mService: IMyAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        mService = Common.api

        txt_login.setOnClickListener { finish()}

        btn_register.setOnClickListener {
            createNewUser(edt_name.text.toString(), edt_email.text.toString(), edt_password.text.toString())
        }
    }

    private fun createNewUser(edt_name: String, edt_email: String, edt_password: String) {

        mService.registerUser(edt_name, edt_email, edt_password)
            .enqueue(object: Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,t!!.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if(response!!.body()!!.error){
                        Toast.makeText(this@RegisterActivity,response.body()!!.error_msg,Toast.LENGTH_SHORT).show()
                    }

                    else{
                        Toast.makeText(this@RegisterActivity,"Register Success" + response.body()!!.uid,Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            })
    }
}