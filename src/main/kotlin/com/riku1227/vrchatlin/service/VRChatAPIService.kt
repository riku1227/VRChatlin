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

    /*
    * ----------
    * World API
    *   | World
    * ----------
    *  */

    @GET("1/worlds/{world_id}")
    fun getWorldByID(@Path("world_id") world_id: String): Single<VRChatWorld>

    @GET("1/worlds")
    fun getWorldList(@Query("featured") featured: Boolean? = null, @Query("sort") sort: String? = null,
                     @Query("user") user: String? = null, @Query("userId") userId: String? = null,
                     @Query("n") n: Int? = null, @Query("order") order: String? = null, @Query("offset") offset: Int? = null,
                     @Query("search") search: String? = null, @Query("tag") tag: String? = null, @Query("notag") notag: String? = null,
                     @Query("releaseStatus") releaseStatus: String? = null,
                     @Query("maxUnityVersion") maxUnityVersion: String? = null, @Query("minUnityVersion") minUnityVersion: String? = null,
                     @Query("maxAssetVersion") maxAssetVersion: String? = null, @Query("minAssetVersion") minAssetVersion: String? = null,
                     @Query("platform") platform: String? = null): Single<List<VRChatWorld>>

    /* No debugging */
    @DELETE("1/worlds/{world_id}")
    fun deleteWorld(@Path("world_id") world_id: String): Completable

    /*
    @GET("1/worlds/{world_id}/metadata")
    fun getWorldMetadata()
    */

    /*
    * ----------
    * World API
    *   | World Instance
    * ----------
    *  */
    @GET("1/worlds/{world_id}/{instance_id}")
    fun getWorldInstanceByID(@Path("world_id") world_id: String, @Path("instance_id") instance_id: String): Single<VRChatWorldInstance>

    @POST("1/instances/{location}/invite")
    fun postInviteMe(@Path("location") location: String): Completable

    @POST("1/instances/{world_id}:{instance_id}/invite")
    fun postInviteMe(@Path("world_id") world_id: String, @Path("instance_id") instance_id: String): Completable

    /*
    * ----------
    * Avatar API
    * ----------
    * */

    @GET("1/avatars/{avatar_id}")
    fun getAvatarByID(@Path("avatar_id") avatar_id: String): Single<VRChatAvatar>

    @GET("1/avatars")
    fun getAvatarList(@Query("user") user: String? = null, @Query("featured") featured: Boolean? = null, @Query("tag") tag: String? = null,
                      @Query("search") search: String? = null, @Query("n") n: Int? = null, @Query("offset") offset: Int? = null,
                      @Query("order") order: String? = null, @Query("releaseStatus") releaseStatus: String? = null, @Query("sort") sort: String? = null,
                      @Query("maxUnityVersion") maxUnityVersion: String? = null, @Query("minUnityVersion") minUnityVersion: String? = null,
                      @Query("maxAssetVersion") maxAssetVersion: String? = null, @Query("minAssetVersion") minAssetVersion: String? = null,
                      @Query("platform") platform: String? = null): Single<List<VRChatAvatar>>

    @GET("1/avatars?user=me&releaseStatus=all")
    fun getAvatarMyList(@Query("featured") featured: Boolean? = null, @Query("tag") tag: String? = null,
                      @Query("search") search: String? = null, @Query("n") n: Int? = null, @Query("offset") offset: Int? = null,
                      @Query("order") order: String? = null, @Query("sort") sort: String? = null,
                      @Query("maxUnityVersion") maxUnityVersion: String? = null, @Query("minUnityVersion") minUnityVersion: String? = null,
                      @Query("maxAssetVersion") maxAssetVersion: String? = null, @Query("minAssetVersion") minAssetVersion: String? = null,
                      @Query("platform") platform: String? = null): Single<List<VRChatAvatar>>

    @PUT("1/avatars/{avatar_id}/select")
    fun chooseAvatar(@Path("avatar_id") avatar_id: String): Single<VRChatUser>

    @DELETE("1/avatars/{avatar_id}")
    fun deleteAvatar(@Path("avatar_id") avatar_id: String): Single<VRChatAvatar>

    /*
    * ----------
    * Notification API
    * ----------
    * */

    @GET("1/auth/user/notifications")
    fun getNotificationList(@Query("type") type: String? = null, @Query("sent") sent: Boolean? = null, @Query("after") after: String? = null): Single<List<VRChatNotification>>

    @PUT("1/auth/user/notifications/{notification_id}/see")
    fun seeNotification(@Path("notification_id") notification_id: String): Single<VRChatNotification>

    @PUT("1/auth/user/notifications/{notification_id}/hide")
    fun hideNotification(@Path("notification_id") notification_id: String): Single<VRChatNotification>
}