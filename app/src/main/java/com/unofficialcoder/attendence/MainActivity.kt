package com.unofficialcoder.attendence

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.unofficialcoder.attendence.ui.DashboardActivity


class MainActivity : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    private var pDialog: ProgressDialog? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signOut()
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        val signOutButton =
            findViewById<Button>(R.id.sign_out_button)
        pDialog = ProgressDialog(this@MainActivity)
        // Configure Google Sign In
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()
        signInButton.setOnClickListener { signIn() }
        signOutButton.setOnClickListener { signOut() }
    }

    /**
     * Display Progress bar while Logging in
     */
    private fun displayProgressDialog() {
        pDialog!!.setMessage("Logging In.. Please wait...")
        pDialog!!.isIndeterminate = false
        pDialog!!.setCancelable(false)
        pDialog!!.show()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient?.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try { // Google Sign In was successful, authenticate with Firebase
                val account =
                    task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) { // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        displayProgressDialog()
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct!!.id)
        val credential: AuthCredential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this, object : OnCompleteListener<AuthResult?> {
                override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful()) { // Sign in success, update UI with the signed-in user's information
                        Log.d(
                            TAG,
                            "signInWithCredential:success"
                        )
                        val user = mAuth?.currentUser
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        //updateUI(user)
                    } else { // If sign in fails, display a message to the user.
                        Log.w(
                            TAG,
                            "signInWithCredential:failure",
                            task.getException()
                        )
                        Toast.makeText(
                            applicationContext,
                            "Login Failed: ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    hideProgressDialog()
                }
            })
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
        val displayName = findViewById<TextView>(R.id.displayName)
        val profileImage =
            findViewById<ImageView>(R.id.profilePic)
        if (user != null) {
            displayName.setText(user.getDisplayName())
            displayName.visibility = View.VISIBLE
            // Loading profile image
            val profilePicUrl = user?.photoUrl
            if (profilePicUrl != null) {
                Glide.with(this).load(profilePicUrl)
                    .into(profileImage)
            }
            profileImage.visibility = View.VISIBLE
            findViewById<View>(R.id.sign_in_button).visibility = View.GONE
            findViewById<View>(R.id.sign_out_button).visibility = View.VISIBLE
        } else {
            displayName.visibility = View.GONE
            profileImage.visibility = View.GONE
            findViewById<View>(R.id.sign_in_button).visibility = View.VISIBLE
            findViewById<View>(R.id.sign_out_button).visibility = View.GONE
        }
    }

    private fun hideProgressDialog() {
        pDialog!!.dismiss()
    }

    public fun signOut() { // Firebase sign out
        mAuth?.signOut()
        // Google sign out
        mGoogleSignInClient?.signOut()?.addOnCompleteListener(this,
            OnCompleteListener<Void?> {
                //updateUI(null)
            })
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val RC_SIGN_IN = 9001

    }
}
