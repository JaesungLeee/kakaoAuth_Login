package com.example.kakaoauthtest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kakao.auth.Session
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.util.helper.Utility.getKeyHash
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var callback : SessionCallback = SessionCallback(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Session.getCurrentSession().addCallback(callback)

        logout_btn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("로그아웃 하시겠습니까?")
            builder.setPositiveButton("확인") { dialogInterface, i ->
                UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                    override fun onCompleteLogout() {
                        Log.d("CHECK", "LOGOUT!!!!!!")
                        Toast.makeText(this@MainActivity, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                })
                dialogInterface.dismiss()
            }
            builder.setNegativeButton("취소") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


//        var hash_key = getKeyHash(this)
//        Log.i("HASH_KEY?", hash_key)
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        Session.getCurrentSession().removeCallback(callback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            Log.i("LOG", "SESSION GET CURRENT SESSION")
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}