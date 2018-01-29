package com.ftgoncalves.ridewithme.infra.di.module

import com.ftgoncalves.ridewithme.feature.login.LoginActivity
import com.ftgoncalves.ridewithme.infra.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    internal abstract fun loginActivity(): LoginActivity
//
//    @ActivityScoped
//    @ContributesAndroidInjector(modules = arrayOf(SignUpModule::class))
//    internal abstract fun signUpActivity(): SignUpActivity
}
