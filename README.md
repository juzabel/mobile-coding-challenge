# Yoga Solo Android coding challenge

This application shows a list of yoga positions, and once you click in one of this position, the detail will be shown.

## Architecture
This app is using Clean Architecture, split in modules by layer, plus a internal web server module. Inside the presentation layer, we're using MVVM architecture, using the official ViewModel class of Android Jetpack, with LiveData objects (And LiveEvent for events, to avoid re-launch problem after configuration changes).

For screen navigation, this app is using Android navigation components

The language used is Kotlin, including Coroutines.

## Libraries
This app is using this libraries:

* **Koin** : Dependency injection framework kotlin-friendly
* **Core-ktx** : Android kotlin extensions
* **Appcompat** : Android compatibility library
* **Constraintlayout** : Android layout constraint rules based
* **Retrofit** : Networking library
* **Moshi** : Kotlin-friendly Json parser
* **Navigation components** : Android navigation tool to make easier and stronger the fragments navigation
* **Lifecycle extensions** : Set of extensions to manage lifecycle. We're using LiveData observables and ViewModel class
* **Logging interceptor** : OkHttp interceptor to log calls
* **LiveEvent** Livedata object for events.
* **Room** Google ORM tool for sqlite database
* **Coil** Kotlin-friendly image loading tool (Only for try purposes, still not stable)
* **JUnit** Java Unit Test framework
* **Mockk** Mock tool kotlin-friendly
* **Ktor** Asynchronous networking tool for Kotlin, used to create the web services
* **Ktor-gson** Json parser for Ktor (Used Gson instead of Moshi adapter because ktor-moshi library seems not updated)