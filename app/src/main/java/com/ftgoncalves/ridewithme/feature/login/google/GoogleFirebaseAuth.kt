package com.ftgoncalves.ridewithme.feature.login.google

import com.ftgoncalves.ridewithme.feature.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Single
import timber.log.Timber

class GoogleFirebaseAuth constructor(
    private val activity: LoginActivity
) {

  fun firebaseAuthWithGoogle(acct: GoogleSignInAccount): Single<FirebaseUser> {
    return Single.create<FirebaseUser>({ emitter ->
      val auth = FirebaseAuth.getInstance()
      val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

      auth.signInWithCredential(credential).addOnCompleteListener(activity, { task ->
        if (task.isSuccessful) {

          auth.currentUser?.let {
            emitter.onSuccess(it)
          }
        } else {
          task.exception?.let {
            Timber.e(it)
            emitter.onError(it)
          }
        }
      })
    })
  }
}
