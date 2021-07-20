package com.moricode.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.moricode.todoapp.core.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        GlobalScope.launch {
            val map = mutableMapOf<String, Any>()
            map["id"] = "test"
            val result = db
                    .collection("todos")
                    .document("test")
                    .set(map)
                    .await()
        }


    }
}
