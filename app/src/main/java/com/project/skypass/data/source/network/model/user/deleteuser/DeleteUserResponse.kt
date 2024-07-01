package com.project.skypass.data.source.network.model.user.deleteuser


import com.google.gson.annotations.SerializedName

data class DeleteUserResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: Data?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?
)