package com.ftgoncalves.ridewithme.feature.login

import android.content.Intent
import io.reactivex.Observable

object LoginContract {
  interface View {
    fun doLoginClicks(): Observable<Unit>
  }

  interface Hub {
    fun connect()

    fun disconnect()

    fun newResult(requestCode: Int, data: Intent?)
  }
}
