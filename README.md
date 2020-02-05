# VRChatlin
## これなに
VRChatAPIをいい感じに扱えるようになるAndroid向けライブラリ  
Retrofit2, RxJava2, Moshiを使用しています    
OkHttpを使用しているためAndroid 5.0(API level 21)以上対応です  
[@minSdkVersion](https://twitter.com/minSdkVersion/status/1204145130673975311)
## サポートされているAPI  
現時点では [https://vrchatapi.github.io/](https://vrchatapi.github.io/) の情報を使用しています  
  
| API名 | サポートされているか | 備考 |
| :-------: | :----: | :---: |
| User API | フルサポート | |
| Favorites API | フルサポート | |
| World API | 一部サポート | deleteWorldのデバッグができていません /  GetWorldMetadataはサポートされていません|
| Avatar API | 一部サポート | Save Avatarはサポートされていません |
| Notification API | フルサポート | Send Notificationは削除されています |  
  
他のAPIは現時点でサポートされていません  
## インストール  
jcenterで利用可能です    
``` Gradle
repositories {
    jcenter()
}
```  
dependenciesに下記の依存関係とともに書いてください
``` Gradle
implementation "com.squareup.okhttp3:okhttp:4.2.2"
implementation "com.squareup.retrofit2:retrofit:2.6.2"
implementation "com.squareup.retrofit2:adapter-rxjava2:2.6.2"
implementation "com.squareup.retrofit2:converter-moshi:2.6.2"
implementation "io.reactivex.rxjava2:rxjava:2.2.14"
implementation "com.squareup.moshi:moshi:1.9.1"
implementation "com.squareup.moshi:moshi-kotlin:1.9.1"

implementation "com.riku1227:vrchatlin:1.0.0"
```
## 依存関係
``` Gradle
dependencies {
    implementation "com.squareup.okhttp3:okhttp:4.2.2"

    implementation "com.squareup.retrofit2:retrofit:2.6.2"

    implementation "com.squareup.retrofit2:adapter-rxjava2:2.6.2"

    implementation "com.squareup.retrofit2:converter-moshi:2.6.2"

    implementation "io.reactivex.rxjava2:rxjava:2.2.14"

    implementation "com.squareup.moshi:moshi:1.9.1"

    implementation "com.squareup.moshi:moshi-kotlin:1.9.1"
}
```
## 使い方
``` kotlin
VRChatlin.get(context)
```
でクライアントインスタンスが取れます
シングルトンになっているので一番最初以降は使い回されます
### APIサービスの初期化
``` kotlin
/* 非推奨 */
VRChatlin.get(context).APIService()
```
これで勝手にサービスの初期化をしてくれますが
このままだとCookie(Authトークンなど)を平文でSharedPreferencesに保存してしまうので  
[Android Jetpack Security](https://developer.android.com/jetpack/androidx/releases/security) の [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences) を使用することを推奨します  
[EncryptedSharedPreferences使ってみる](https://qiita.com/rmorimot/items/ba9e79825bccaa9c0abe)  
``` kotlin
/* 推奨 */
VRChatlin.get(context).APIService(SharedPreferences)
```
一番最初呼び出すときに引数に用意したSharedPreferencesを入れることでそのSharedPreferencesを使用することができます
``` kotlin
VRChatlin.get(context).APIService(SharedPreferences, OkHttpClient)
/* もしくは */
VRChatlin.get(context).APIService(initOkHttpClient = OkHttpClient) //非推奨
```  
これで用意したOkHttpClientを使用することができます  
ただこれだとCookieを保存しないので、処理を作るか、CookieJarに **VRChatlinCookieJar** を使用してください  
これでサービスの初期化ができました  
初期化した次からはSharedPreferencesなどを引数に入れなくても大丈夫です
