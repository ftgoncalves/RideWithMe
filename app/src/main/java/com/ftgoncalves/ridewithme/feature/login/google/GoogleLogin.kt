package com.ftgoncalves.ridewithme.feature.login.google

import com.ftgoncalves.ridewithme.R
import com.ftgoncalves.ridewithme.feature.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleLogin constructor(private val activity: LoginActivity) {

  private val gso by lazy {
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(activity.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
  }

  private val googleSignInClient by lazy {
    GoogleSignIn.getClient(activity, gso)
  }

  fun startActivityForResult() {
    activity.startActivityForResult(googleSignInClient.signInIntent, GOOGLE_REQUEST)
  }

  companion object {
    const val GOOGLE_REQUEST = 1234
  }
}
