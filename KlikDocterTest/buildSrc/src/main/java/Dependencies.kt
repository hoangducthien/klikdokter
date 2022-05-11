object Versions {
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val compileSdk = 32
}


object Deps {
    const val kotlinKtx = "androidx.core:core-ktx:1.7.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"

    private const val okhttpVersion = "4.9.3"
    const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val okhttpLog = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

    private const val gsonVersion = "2.9.0"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$gsonVersion"
    const val jUnit = "junit:junit:4.13.2"
    const val jUnitExt = "androidx.test.ext:junit:1.1.3"
    const val expresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.1"
    const val material = "com.google.android.material:material:1.6.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val coroutine =  "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"

    private const val roomVersion = "2.4.2"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomAnnotation = "androidx.room:room-compiler:$roomVersion"

    private const val hiltVersion = "2.38.1"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val javaInject = "javax.inject:javax.inject:1"

    private const val ktxVersion = "2.4.1"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$ktxVersion"
    const val lifeCycleVm = "androidx.lifecycle:lifecycle-viewmodel-ktx:$ktxVersion"

    const val activityKtx = "androidx.activity:activity-ktx:1.4.0"

}