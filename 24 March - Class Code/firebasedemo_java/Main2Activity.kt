package com.bmpl.android.firebasedemo_java

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.File


class Main2Activity : AppCompatActivity() {

    lateinit var storageReference : StorageReference
    lateinit private var nameOfFile: String
    lateinit var riversRef : StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://fir-demo-26e13.appspot.com/myimages")

        uploadButton.setOnClickListener {

            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/jpeg"
            startActivityForResult(intent, 1000)
        }

        downloadButton.setOnClickListener {
            val localFile = File.createTempFile("images", "jpg")
            riversRef.getFile(localFile)
                    .addOnSuccessListener({
                        Toast.makeText(this, "File Downloaded", Toast.LENGTH_SHORT).show()
                    }).addOnFailureListener(OnFailureListener {
                        Toast.makeText(this, "Unable to download", Toast.LENGTH_SHORT).show()
                    })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1000){
            val uri = data!!.data
            nameOfFile = uri.path

            Log.d("WelcomeActivity", "name = " + nameOfFile + "uri data= " + uri)

            riversRef = storageReference.child(nameOfFile)

            riversRef.putFile(uri)
                    .addOnSuccessListener({ Toast.makeText(this@Main2Activity, "Uploaded", Toast.LENGTH_LONG).show() })
                    .addOnFailureListener({ exception ->
                        Toast.makeText(this@Main2Activity, "Error Occurred" + exception.message, Toast.LENGTH_LONG).show()
                        Log.d("WelcomeActivity", exception.message)
                    })
        }
    }
}