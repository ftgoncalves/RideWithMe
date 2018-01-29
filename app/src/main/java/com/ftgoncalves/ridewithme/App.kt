package com.ftgoncalves.ridewithme

import com.ftgoncalves.ridewithme.infra.di.component.ApplicationComponent
import com.ftgoncalves.ridewithme.infra.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    applicationComponent = DaggerApplicationComponent.builder()
        .application(this)
        .build()

    applicationComponent.inject(this)

    return applicationComponent
  }

  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
  }

  companion object {
    lateinit var applicationComponent: ApplicationComponent
  }
}
