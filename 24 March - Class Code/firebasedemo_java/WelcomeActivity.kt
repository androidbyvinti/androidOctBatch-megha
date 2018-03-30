package com.bmpl.android.firebasedemo_java

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    var firebaseUser: FirebaseUser? = null
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("userdata")
        firebaseUser = firebaseAuth.currentUser

//        databaseReference.child("chats").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // class<User> user
//                //String value = dataSnapshot.getValue(String.class);
//                for (dataSnapshot1 in dataSnapshot.children) {
//                    Log.i("WelcomeActivity", "" + dataSnapshot1.child("name").value)
//                    Log.i("WelcomeActivity", "" + dataSnapshot1.child("phn").value)
//                    //Log.i("Welcome Activity", dataSnapshot1.value!!.toString())
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//
//            }
//        })


        nextScreenButton.setOnClickListener {
            var intent = Intent(this, Main2Activity :: class.java)
            startActivity(intent)
        }
    }
}
