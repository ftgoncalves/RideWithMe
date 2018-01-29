package com.ftgoncalves.ridewithme.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.ftgoncalves.ridewithme.R
import com.ftgoncalves.ridewithme.commons.Store
import com.jakewharton.rxbinding2.view.RxView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.google_sign_in
import timber.log.Timber
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity(), LoginContract.View {

  @Inject
  lateinit var hub: LoginContract.Hub

  @Inject
  lateinit var store: Store<LoginStore>

  override fun doLoginClicks() = RxView.clicks(google_sign_in).map { }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    store.stateChanges()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { bindNewState(it) }

    Timber.d(store.toString())

    hub.connect()
  }

  private fun bindNewState(state: LoginStore) {
    when (state.type) {
      LoginStore.Type.Success -> success()
    }
  }

  private fun success() {
    Toast.makeText(this, "It works", Toast.LENGTH_LONG).show()
  }

  override fun onDestroy() {
    super.onDestroy()
    hub.disconnect()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    hub.newResult(requestCode, data)
  }
}
