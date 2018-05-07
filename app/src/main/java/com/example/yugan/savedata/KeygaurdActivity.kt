package com.example.yugan.savedata

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Toast
import com.example.yugan.savedata.view.MainActivity

class KeygaurdActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keygaurd)

        val keyguardManager: KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if(keyguardManager.isKeyguardSecure)
        {
            val intent=keyguardManager.createConfirmDeviceCredentialIntent("Authentication required"
                    ,"Confirm your scrren lock pattren or PIN or lock")
            startActivityForResult(intent,100)
        }else {
            Toast.makeText(this,"No Authentication...", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK && requestCode==100)
        {
            Toast.makeText(this,"SUCCESS: Verified user authentication...",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this," FAILURE :  unable to verify user authentication...",Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
