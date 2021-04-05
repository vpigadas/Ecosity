package com.vpigadas.ecosity.data.login

data class UserCredentialsModel(
    val username: String,
    val pass: String
) {
    companion object {
        val MOCK = UserCredentialsModel("moderator", "moderator")
    }

    fun validate(username: String, pass: String): Boolean =
        (username == "moderator" && pass == "moderator")

}