package com.ftgoncalves.ridewithme.infra.di.module

import com.ftgoncalves.ridewithme.commons.Store
import com.ftgoncalves.ridewithme.feature.login.DoGoogleLoginJob
import com.ftgoncalves.ridewithme.feature.login.LoginActivity
import com.ftgoncalves.ridewithme.feature.login.LoginContract
import com.ftgoncalves.ridewithme.feature.login.LoginHub
import com.ftgoncalves.ridewithme.feature.login.LoginStore
import com.ftgoncalves.ridewithme.feature.login.google.GoogleFirebaseAuth
import com.ftgoncalves.ridewithme.feature.login.google.GoogleLogin
import com.ftgoncalves.ridewithme.infra.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
@ActivityScoped
class LoginModule {

  @Provides
  fun providesLoginActivity(activity: LoginActivity): LoginContract.View = activity

  @Provides
  @ActivityScoped
  fun providesLoginStore(): Store<LoginStore> {
    return Store(LoginStore())
  }

  @Provides
  fun providesLoginHub(activity: LoginActivity, view: LoginContract.View, store: Store<LoginStore>): LoginContract.Hub {
    return LoginHub(view, DoGoogleLoginJob(store, GoogleLogin(activity), GoogleFirebaseAuth(activity)))
  }
}
