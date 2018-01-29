package com.ftgoncalves.ridewithme.feature.login

import android.content.Intent
import com.ftgoncalves.ridewithme.commons.Job
import com.ftgoncalves.ridewithme.commons.Store
import com.ftgoncalves.ridewithme.feature.login.google.GoogleFirebaseAuth
import com.ftgoncalves.ridewithme.feature.login.google.GoogleLogin
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import io.reactivex.Completable
import timber.log.Timber

class DoGoogleLoginJob(
    private val store: Store<LoginStore>,
    private val googleLogin: GoogleLogin,
    private val firebase: GoogleFirebaseAuth
) : GoogleLoginJob {
  override fun bind(input: Unit): Completable {
    googleLogin.startActivityForResult()

    return Completable.complete()
  }

  override fun onActivityResult(data: Intent?) {
    val result = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)

    Timber.d(store.toString())

    firebase.firebaseAuthWithGoogle(result)
        .doOnSuccess { /*Save on database*/ }
        .doOnSuccess { Timber.d(it.email) }
        .doOnSuccess { store.update(store.state().setType(LoginStore.Type.Success)) }
        .subscribe()
  }
}

interface GoogleLoginJob : Job<Unit> {
  fun onActivityResult(data: Intent?)
}
