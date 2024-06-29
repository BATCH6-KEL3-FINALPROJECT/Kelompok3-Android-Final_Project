## APK Bundle
APK bundle: [SkyPass.apk]()
## About Our App
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
## Repository Supporting This Application
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
## Instalation
1. Open Android Studio 
2. Clone this repository (tutorial on cloning repository with Android Studio: [Repository cloning](https://www.geeksforgeeks.org/how-to-clone-android-project-from-github-in-android-studio/) 
3. Connect the device to Android Studio:
   * Using a virtual device: Click [Run With ADV](https://developer.android.com/studio/run/managing-avds)
   * Using a real device: Click [Connect With USB Debugging](https://developer.android.com/codelabs/basic-android-kotlin-compose-connect-device#0) or [Connect With Wireless Debugging](https://developer.android.com/studio/run/device)
4. Run the app in Android Studio: Click [Documentation](https://developer.android.com/studio/run/managing-avds)
## Library
| Library                              | Usage                                                                                                                                                             | Dependencies                                                                |
|--------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Android KTX                          | To provide Kotlin extensions for core Android components such as collections, files, preferences, and more                                                        | implementation 'androidx.core:core-ktx:1.8.0'                               |
| Lifecycle                            | To enable observing and responding to life cycle changes of Android components such as activities and fragments.                                                  | implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'             |
| Activity Compose                     | To provide Activity-ktx, which is part of Jetpack Compose, for easy integration of Activity with Compose UI.                                                      | implementation 'androidx.activity:activity-compose:1.5.1'                   |
| Compose UI                           | To provide UI basics on Jetpack Compose and layout system to build a declarative and responsive user interface.                                                   | implementation 'androidx.compose.ui:ui'                                     |
| Compose UI Graphic                   | To provide functions and tools for drawing and manipulating graphics within Jetpack Compose.                                                                      | implementation 'androidx.compose.ui:ui-graphics'                            |
| Compose Tooling Preview              | To provide development tools and previews to help develop interfaces with Jetpack Compose.                                                                        | implementation 'androidx.compose.ui:ui-tooling-preview'                     |
| Jetpack Compose Material 3           | To provide Material Design version 3 components for Jetpack Compose, including components such as buttons, cards, text, and more.                                 | implementation 'androidx.compose.material3:material3'                       |
| JUnit                                | To provide Material Design version 3 components for Jetpack Compose, including components such as buttons, cards, text, and more.                                 | testImplementation 'junit:junit:4.13.2'                                     |
| JUnit Test                           | This library is part of AndroidX Test and provides extensions to the JUnit unit testing framework for Android.                                                    | androidTestImplementation 'androidx.test.ext:junit:1.1.5'                   |
| AndroidX Test                        | To provide a powerful and expressive functional testing (UI) framework for Android application testing.                                                           | androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'      |
| Compose JUnit Test                   | To provide testing tools and utilities for UI testing with Jetpack Compose using JUnit 4.                                                                         | androidTestImplementation 'androidx.compose.ui:ui-test-junit4'              |
| Compose Tooling                      | To provide development tools and utilities to assist development with Jetpack Compose.                                                                            | debugImplementation 'androidx.compose.ui:ui-tooling'                        |
| Compose Test Manifest                | To provide the manifest required to run tests with Jetpack Compose.                                                                                               | debugImplementation 'androidx.compose.ui:ui-test-manifest'                  |
| Jetpack Compose Material Design Icon | To provide extended Material Design icons for use in applications that use Jetpack Compose.                                                                       | implementation "androidx.compose.material:material-icons-extended:1.4.3"    |
| Jetpack Compose Navigation           | To provide integration between Jetpack Compose and Android Navigation Components, allowing for integrated and easy-to-manage user interface navigation.           | implementation "androidx.navigation:navigation-compose:2.5.3"               |
| Pager Component For Jetpack Compose  | To provide a Pager component for Jetpack Compose, which enables views with swipe navigation such as page views or carousel views.                                 | implementation "com.google.accompanist:accompanist-pager:0.30.1"            |
| Pager Indicator For Jetpack Compose  | To provide a pager indicator for Jetpack Compose, which can be used to display indicators such as dots or arrows to show the current position in the pager view.  | implementation "com.google.accompanist:accompanist-pager-indicators:0.30.1" |
| Retrofit                             | This library is a powerful and lightweight HTTP client for Android and Java, which allows communication with servers using the HTTP protocol and REST API.        | implementation 'com.squareup.retrofit2:retrofit:2.9.0'                      |
| GSON                                 | This library is a Retrofit converter that uses Gson to convert JSON responses from the server into Java/Kotlin objects.                                           | implementation "com.squareup.retrofit2:converter-gson:2.9.0"                |
| OKHTTP3                              | This library is an OkHttp interceptor used to view and log HTTP requests and responses for troubleshooting and debugging purposes.                                | implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"             |
| View Model For Jetpack Compose       | This library provides integration between Jetpack Compose and the ViewModel architecture, allowing the use of ViewModels in Compose UI components.                | implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"       |
| Preference                           | This library provides Android preferences as a component of the Jetpack architecture, which allows easy organisation of applications and use of user preferences. | implementation "androidx.preference:preference-ktx:1.2.0"                   |
