package com.ftgoncalves.ridewithme.feature.login

data class LoginStore(
    val type: Type? = null
) {

  fun setType(newType: Type) = copy(type = newType)

  sealed class Type {
    object Success : Type()
  }
}
