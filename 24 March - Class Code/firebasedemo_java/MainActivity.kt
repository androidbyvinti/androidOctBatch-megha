package com.bmpl.android.firebasedemo_java

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mCallbackManager: CallbackManager? = null
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseUser : FirebaseUser

    lateinit var registerButton: Button
    lateinit var loginButton: Button

    lateinit var firebaseAuth: FirebaseAuth

    lateinit var name: String
    lateinit var email: String
    lateinit var password: String

    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("userdata")
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        //firebaseUser = firebaseAuth.currentUser!!

        mCallbackManager = CallbackManager.Factory.create();

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val googleSignInButton =  findViewById<SignInButton>(R.id.googleSignIn)

        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)

        facebookLogin.text = "Login with Facebook"

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        // Facebook Login Button
       // facebookLogin.setReadPermissions("email", "public_profile")
        facebookLogin.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("MainActivity", "facebook:onSuccess:" + loginResult)
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("MainActivity", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("MainActivity", "facebook:onError", error)
                Toast.makeText(this@MainActivity, "Error Occured while Facebook Login", Toast.LENGTH_LONG).show()

            }
        })

        registerButton.setOnClickListener {
            name = nameEditText.text.toString()
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()
                        // Register user on App
                        // Predefined Method --> FirebaseAuth
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@MainActivity) { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this@MainActivity, "Account Created",
                                    Toast.LENGTH_SHORT).show()
                            // FirebaseUser --> Predefined class
                            //val user = firebaseAuth.currentUser

                            firebaseUser = firebaseAuth.currentUser!!

                            databaseReference.child("chats").child(firebaseUser.uid).child("name").setValue("Ram Kumar")

                            databaseReference.child(firebaseUser.uid).child("name").setValue(name)
                            //                                    databaseReference.child(user.getUid()).child("age").setValue(name);
                            //                                    databaseReference.child(user.getUid()).child("name").setValue(name);
                            Log.d("MainActivity", "createUserWithEmail:success")

                            startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
                        } else {
                            Log.w("MainActivity", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this@MainActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()

                        }
                    }
        }


        //Google Sign-In
        googleSignInButton.setOnClickListener { signIn() }

        loginButton.setOnClickListener {
            name = nameEditText.text.toString()
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@MainActivity) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MainActivity", "signInWithEmail:success")
                            startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
                        } else {
                            Log.w("MainActivity", "signInWithEmail:failure", task.exception)
                            Toast.makeText(this@MainActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    override fun onStart() {
        super.onStart()
            // Check for existing Google Sign In account, if the user is already signed in
            // the GoogleSignInAccount will be non-null.
            val account = GoogleSignIn.getLastSignedInAccount(this)
            //updateUI(account)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // facebook login
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)


        // Google sign-in
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("MainActivity", "Google sign in failed", e)
                // ...
            }


            //handleSignInResult(task)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("MainActivity", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MainActivity", "signInWithCredential:success")
                        //val user = mAuth.getCurrentUser()
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("MainActivity", "signInWithCredential:failure", task.exception)
                        //Snackbar.make(findViewById(R.id.), "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                       // updateUI(null)
                    }

                    // ...
                })
    }


    fun handleFacebookAccessToken(token: AccessToken) {
    Log.d("MainActivity", "handleFacebookAccessToken:" + token);

    var credential : AuthCredential = FacebookAuthProvider.getCredential(token.token);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MainActivity", "signInWithCredential:success");

                        //FirebaseUser user = mAuth.getCurrentUser();
                        //updateUI(user);
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Log.w("MainActivity", "signInWithCredential:failure", task.getException());
                        Toast.makeText(this@MainActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }

                })
//            .addOnCompleteListener(this, {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithCredential:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        updateUI(user);
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.getException());
//                        Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                        updateUI(null);
//                    }
//
//                    // ...
//                }
//            })
}





    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Log.w("MainActivity",""+ account)
            //updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("MainActivity", "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
        }
    }
}