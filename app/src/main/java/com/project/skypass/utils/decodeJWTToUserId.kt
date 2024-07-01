package com.project.skypass.utils

import android.util.Base64
import org.json.JSONObject
import java.nio.charset.StandardCharsets

fun decodeJWT(token: String): String {
    try {
        val parts = token.split(".")
        if (parts.size == 3) {
            val payload = String(Base64.decode(parts[1], Base64.URL_SAFE), StandardCharsets.UTF_8)
            val jsonObject = JSONObject(payload)
            return jsonObject.getString("id")
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}
