package com.ftgoncalves.ridewithme.infra.di.component

import android.app.Application
import com.ftgoncalves.ridewithme.App
import com.ftgoncalves.ridewithme.infra.di.module.ActivityBuilderModule
import com.ftgoncalves.ridewithme.infra.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBuilderModule::class))
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}
