package com.riku1227.vrchatlin

import android.content.SharedPreferences
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class VRChatlinCookieJar(val preferences: SharedPreferences): CookieJar {

    companion object {
        const val COOKIE_AUTH = "auth"
        const val COOKIE_API_KEY = "apiKey"
        const val COOKIE_CFDUID = "__cfduid"
    }

    private var cookieMap = mutableMapOf<String, Cookie>()
    private val cookieNameList = listOf(COOKIE_API_KEY, COOKIE_AUTH, COOKIE_CFDUID)

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookieList = arrayListOf<Cookie>()
        for(cookieName in cookieNameList) {
            if(cookieMap[cookieName] != null) {
                cookieList.add(cookieMap[cookieName]!!)
            } else {
                val preferencesValue = preferences.getString(cookieName, "")
                if(!preferencesValue.isNullOrEmpty()) {
                    Cookie.parse(url, preferencesValue)?.let {
                        if(it.expiresAt > System.currentTimeMillis()) {
                            cookieList.add(it)
                        } else {
                            cookieMap.remove(cookieName)
                            preferences.edit().remove(cookieName).apply()
                        }
                    }
                }
            }
        }
        return cookieList
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val editor = preferences.edit()
        for(i in cookies) {
            cookieMap[i.name] = i
            editor.putString(i.name, i.toString()).apply()
        }
        editor.apply()
    }

    fun setCookie(key: String, cookieStr: String) {
        cookieMap.remove(key)
        preferences.edit().putString(key, cookieStr).apply()
    }

}