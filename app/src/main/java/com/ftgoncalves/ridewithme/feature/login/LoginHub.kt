package com.ftgoncalves.ridewithme.feature.login

import android.content.Intent
import com.ftgoncalves.ridewithme.feature.login.google.GoogleLogin
import io.reactivex.disposables.CompositeDisposable

class LoginHub constructor(
    private val view: LoginContract.View,
    private val doGoogleLoginJob: GoogleLoginJob
) : LoginContract.Hub {

  private val disposable = CompositeDisposable()

  override fun connect() {
    val subscribe = view.doLoginClicks()
        .flatMapCompletable { doGoogleLoginJob.bind(Unit) }
        .subscribe()

    disposable.add(subscribe)
  }

  override fun disconnect() {
    disposable.dispose()
  }

  override fun newResult(requestCode: Int, data: Intent?) {
    if (requestCode == GoogleLogin.GOOGLE_REQUEST) {
      doGoogleLoginJob.onActivityResult(data)
    }
  }
}
