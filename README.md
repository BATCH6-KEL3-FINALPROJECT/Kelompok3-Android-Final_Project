<h1 align="center">
  <img src="./app/src/main/res/drawable/logo_skypass.png" alt="SkypassLogo" width="300"/>
  <br/>
  Final Project Binar Academy x Kampus Merdeka Batch 6
</h1>

<img src="https://drive.google.com/uc?export=view&id=1-prgQg-_HGLY0_ds9HaNiQKXkR_Ydn3E" alt="test">

# APK Bundle
APK bundle: [SkyPass.apk](https://drive.google.com/drive/folders/1ohACHPNZUUM5QxtC-3GRyRNPCWEgVLO4?usp=sharing)

# About Our App
This application is a flight ticket booking application where this application has many features such as:
1. Authentication & verification feature by email
2. 1x trip or round trip ticket booking
3. Ticket payment using Midtrans
4. Ticket printing feature
5. Favourite destination selection menu
6. There is a ticket booking history menu
7. There is a notification feature
8. There is a profile feature where the user can change their personal data including profile picture.
9. Users can also delete their account if not needed
10. There is a dark mode feature for user convenience
    
# Repository Supporting This Application
| Path               | Link Repository |
|--------------------|-----------------|
| Android Engineering| SkyPass-App (this repository)               |
| Fullstack Web (Front End)    | [SkyPass-Web](https://github.com/BATCH6-KEL3-FINALPROJECT/Kelompok3-Frontend-Final_Project.git)                |
| Fullstack Web (Back End)  | [SkyPass-API](https://github.com/BATCH6-KEL3-FINALPROJECT/Kelompok3-Backend-Final_Project.git)                |

# Usage

## Requirement
1. Android Studio Jellyfish or higher (supports Jetpack Compose Material 3)
2. Minimum SDK 24 (Android 7.0)
3. PC/Laptop
   * Processor: Intel Core i3 or higher (recommended i5 or higher)
   * RAM 8 GB or higher
   * Minimum 8 GB disc (SSD recommended)
   * Microsoft Windows 64 bit
   * 
## Instalation
1. Open Android Studio 
2. Clone this repository (tutorial on cloning repository with Android Studio: [Repository cloning](https://www.geeksforgeeks.org/how-to-clone-android-project-from-github-in-android-studio/) 
3. Connect the device to Android Studio:
   * Using a virtual device: Click [Run With ADV](https://developer.android.com/studio/run/managing-avds)
   * Using a real device: Click [Connect With USB Debugging](https://developer.android.com/codelabs/basic-android-kotlin-compose-connect-device#0) or [Connect With Wireless Debugging](https://developer.android.com/studio/run/device)
4. Run the app in Android Studio: Click [Documentation](https://developer.android.com/studio/run/managing-avds)
   
# Library
| Library                              | Usage                                                                                                                                                             | Dependencies                                                                |
|--------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Android KTX                          | To provide Kotlin extensions for core Android components such as collections, files, preferences, and more                                                        | implementation("androidx.core:core-ktx:1.13.1")                             |
| Lifecycle KTX                        | To enable observing and responding to life cycle changes of Android components such as activities and fragments.                                                  | implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")             |
| Constraint Layout | Provides a flexible layout system for creating complex user interfaces | implementation("androidx.constraintlayout:constraintlayout:2.1.4") |
| Coil | An image loading library for Android backed by Kotlin Coroutines | implementation("io.coil-kt:coil:2.6.0") |
| Lifecycle Runtime | Kotlin extensions for Android Lifecycle library | implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") |
| Lifecycle View Model | Kotlin extensions for ViewModel library | implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") |
| Lifecycle Live Data | Kotlin extensions for LiveData library | implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0") |
| Fragment KTX | Kotlin extensions for Android Fragment library | implementation("androidx.fragment:fragment-ktx:1.6.2") |
| Navigation Fragment | Kotlin extensions for Android Navigation Component | implementation("androidx.navigation:navigation-fragment-ktx:2.7.7") |
| Navigation UI Fragment | Kotlin extensions for Android Navigation UI | implementation("androidx.navigation:navigation-ui-ktx:2.7.7") |
| Room Database |  Kotlin extensions for Room, a persistence library providing an abstraction layer over SQLite | implementation("androidx.room:room-ktx:2.6.1") |
| Room Complier |Annotation processor for Room | implementation("androidx.room:room-ktx:2.6.1") |
| Coroutines Core | Core libraries for Kotlin coroutines | implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") |
| Coroutines Android | Android-specific libraries for Kotlin coroutines | implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") |
| Retrofit | A type-safe HTTP client for Android and Java | implementation("com.squareup.retrofit2:retrofit:2.9.0") |
| Retrofit Converter GSON to JSON | A converter for Retrofit that uses Gson for JSON serialization | implementation("com.squareup.retrofit2:converter-gson:2.9.0") |
| OkHttp3 | An HTTP & HTTP/2 client for Android and Java applications | implementation("com.squareup.okhttp3:okhttp:4.11.0") |
| OkHttp3 Logging Interceptor | An OkHttp interceptor that logs HTTP request and response data | implementation("com.squareup.okhttp3:logging-interceptor:4.11.0") |
| Paging | Kotlin extensions for the Paging library, which helps load and display large sets of data | implementation("androidx.paging:paging-runtime-ktx:3.3.0") |
| Shimmer Loader | A library for adding shimmer effect on views | implementation("com.facebook.shimmer:shimmer:0.5.0") |
| OTP View | A library for creating PIN input views | implementation("io.github.chaosleung:pinview:1.4.4") |
| Koin | A pragmatic dependency injection library for Kotlin developers | implementation("io.insert-koin:koin-android:3.5.6") |
| App Intro For On Boarding | A library for creating introduction slides for your app | implementation("com.github.AppIntro:AppIntro:6.3.1") |
| Dots Indicator | A library for creating dots indicators for view pagers | implementation("com.tbuonomo:dotsindicator:4.3") |
| Styleable Toast | A library for customizable Toast messages | implementation("io.github.muddz:styleabletoast:2.4.0") |
| Lottie | A library for rendering Adobe After Effects animations in real-time | implementation("com.airbnb.android:lottie:6.0.0") |
| Calendar View |  A customizable calendar view for Android | implementation("com.kizitonwose.calendar:view:2.5.1") |
| Seat Book View | A library for creating a seat booking view | implementation("com.github.JahidHasanCO:SeatBookView:1.0.4") | 
| Firebase Crashlytics | Firebase Crashlytics for tracking and reporting app crashes | implementation("com.google.firebase:firebase-crashlytics:19.0.1") |
| Google OAuth | Google Play services library for authentication and authorization | implementation("com.google.android.gms:play-services-auth:20.0.0") |
| Groupie | A library for managing complex RecyclerView layouts | implementation("com.github.lisawray.groupie:groupie:2.10.1") |
| Groupie View Binding | ViewBinding support for Groupie | implementation("com.github.lisawray.groupie:groupie-viewbinding:2.10.1") |
| Ticket View | A custom view for creating a ticket-style layout | implementation("com.vipulasri:ticketview:1.1.2") |
