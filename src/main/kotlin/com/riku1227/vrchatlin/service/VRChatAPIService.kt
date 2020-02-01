package com.riku1227.vrchatlin.service

import com.riku1227.vrchatlin.model.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface VRChatAPIService {

    /*
    * ----------
    * Remote Config API
    * ----------
    *  */
    @GET("1/config")
    fun getRemoteConfig(): Single<VRChatRemoteConfig>

    /*
    * ----------
    * User API
    *   | Current User
    * ----------
    *  */
    @GET("1/auth/user")
    fun login(@Header("Authorization") auth: String, @Query("apiKey") apiKey: String): Single<VRChatUser>

    @PUT("1/logout")
    fun logout(): Completable

    @GET("1/auth/user")
    fun getCurrentUserInfo(): Single<VRChatUser>

    @PUT("1/users/{user_id}")
    fun updateUserInfo(@Path("user_id") user_id: String, @Body updateInfo: VRChatUpdateInfo): Single<VRChatUser>

    /*
    * ----------
    * User API
    *   | Friends
    * ----------
    *  */

    @GET("1/auth/user/friends")
    fun getFriends(@Query("offline") offline: Boolean? = null, @Query("n") n: Int? = null, @Query("offset") offset: Int? = null): Single<List<VRChatUser>>

    @GET("1/user/{user_id}/friendStatus")
    fun getFriendStatus(@Path("user_id") user_id: String): Single<VRChatFriendStatus>

    @POST("1/user/{user_id}/friendRequest")
    fun sendFriendRequest(@Path("user_id") user_id: String): Single<VRChatNotification>

    @DELETE("1/auth/user/friends/{user_id}")
    fun deleteFriend(@Path("user_id") user_id: String): Completable

    @PUT("1/auth/user/notifications/{notification_id}/accept")
    fun acceptFriend(@Path("notification_id") notification_id: String): Completable

    /*
    * ----------
    * User API
    *   | Any User
    * ----------
    *  */

    @GET("1/users/{user_id}")
    fun getUserByID(@Path("user_id") user_id: String): Single<VRChatUser>

    @GET("1/users/{user_name}/name")
    fun getUserByName(@Path("user_name") user_name: String): Single<VRChatUser>

    @GET("1/users")
    fun getUserList(@Query("search") search: String? = null, @Query("n") n: Int? = null, @Query("offset") offset: Int? = null): Single<List<VRChatUser>>

    /*
    * ----------
    * Favorites API
    * ----------
    * */

    @POST("1/favorites")
    fun addFavorite(@Body vrChatFavorite: VRChatFavorite): Single<VRChatFavorite>

    @GET("1/favorites/{favorite_id}")
    fun getFavorite(@Path("favorite_id") favorite_id: String): Single<VRChatFavorite>

    @GET("1/favorites")
    fun getFavoriteList(@Query("type") type: String? = null): Single<List<VRChatFavorite>>

    @GET("1/auth/user/friends/favorite")
    fun getFavoriteFriendList(@Query("tag") tag: String? = null): Single<List<VRChatUser>>

    @GET("1/worlds/favorites")
    fun getFavoriteWorldList(@Query("tag") tag: String? = null): Single<List<VRChatWorld>>

    @GET("1/avatars/favorites")
    fun getFavoriteAvatarList(/* @Query("tag") tag: String? = null */): Single<List<VRChatAvatar>>

    @DELETE("1/favorites/{favorite_id}")
    fun deleteFavorite(@Path("favorite_id") favorite_id: String): Completable
}