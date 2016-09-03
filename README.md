# ThumbnailApp

#About
A simple code challenge that requires getting a user list from a webservice and showing it in a grid. Also if we click in some of the thumbnails a request is sent to another webservice, the response is plain text that is shown in a Snackbar.

#Overall architecture
I used a simplified version of [Fernando Cejas Clean Architecture](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/). I got rid of a couple of layers and the project isn't split in data, domain and presentation. Mainly I just kept the UseCases, the Repository pattern (although isn't 100% a repository pattern, lazy guilt there) and the MVP architecture.

#Technologies that I used
- Picasso: To load the images fron the web through a simple API.
- Dagger: To make the dependency injection.
- Android SDK components: Just a RecyclerView with a GridLayoutManager.
- Retrofit + OkHttpClient: To perform the HTTP requests.
- Soundcloud's Lightcycle: To avoid hooking up the Presenter in each of the Fragment lifecycle methods. The Lightcycle library does this
for us.
- Butterknife: To avoid all the `findViewById` boilerplate code.
- RxJava: To perform asynchronously the request and using the Subscribers as callbacks.

#Package organization
- datasource: In this package I put the Repository concrete implementation.
- di: All Dagger Modules are here.
- model: The model classes are here, I didn't use entity mapper classes, so basically the model classes and the entity mapper classes are the same.
- net: All the network layer related classes are here.
  - rest: The RESTful API interface is in this package and the concrete implementation that uses the Retrofit service class.
  - services: The Retrofit classes. The Retrofit service interface and the Retrofit factory class.
  - subscribers: Default subscribers implementations.
  - usecases: The UseCases implementations are in this package. Notice that I didn't inject the threads to avoid creating extra Modules, since the project is really small I think that doing so would be a waste.
- repository: The repository interfaces. Since there's only one Repository implementation, and there's no memory layer Repositories or database Repositories I didn't inject this dependencies neither.
- ui: All User Interface related classes are here, Activities, Fragments, Custom Views, etc.
  - main: Main screen classes. It only contains the [MainActivity](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/main/MainActivity.java) class.
  - challenge: Challenge screen classes. The challenge screen is the screen that shows the grid with the Player's thumbnails.
    - view: Challenge's screen view classes. It only contains the [ChallengeFragment](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/challenge/view/ChallengeFragment.java) class.
    - presenter: Challenge's screen presenter classes. It only contains the [ChallengePresenter](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/challenge/presenter/ChallengePresenter.java) class.
    - adapter: Challenge's screen RecyclerView related classes. Contains the [ChallengeAdapter](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/challenge/adapter/ChallengeAdapter.java) class and the [ViewHolder](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/challenge/adapter/PlayerViewHolder.java)
  - widgets: Custom view classes. It contains a custom TextView, the [ThumbnailAppTextView](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/widgets/ThumbnailAppTextView.java) and the [TriangleShapeView](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/ui/widgets/TriangleShapeView.java) which is a custom view that draws a right triangle fill with color, used as separator in the thumbnails.
  
#Notes
- As you can see I got rid of a couple of layers from the Clean Architecture, because this app isn't complex enough to require those layers I avoided some dependency injections, for example: I didn't inject the Executor in the [UseCase](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/net/usecases/UseCase.java) class. I didn't inject the OkHttpClient or the Retrofit instance in the [ServiceFactory](https://github.com/4gus71n/ThumbnailApp/blob/fee4c357334183d01be2081a5e0b15abec1f4c0f/app/src/main/java/com/si/thumbnailapp/net/services/ServiceFactory.java). I didn't inject the Repositories in the use cases.
- Checked against Lollipop and Marshmellow (there's no special single permissons that can be taken off, so there's no need to perform any type of single permission validation).
- I'll add some Robolectric Fragment unit testing using a mocked Presenter if I've time left this weekend :B
- I'm cancelling the image requests that're outside the Grid, this way if you scroll really fast through the grid, we'll avoid stacking up several request. Check the ViewHolder class, the [cancelLoading](https://github.com/4gus71n/ThumbnailApp/blob/master/app/src/main/java/com/si/thumbnailapp/ui/challenge/adapter/PlayerViewHolder.java#L67) method.
- The MainActivity is being use only as a shell container for the Fragment. I like keeping all the logic in the Fragment/Presenters so Its easier to manage.

#Some screencaps
![alt tag](https://github.com/4gus71n/ThumbnailApp/blob/master/Screenshot_2016-09-02-14-41-48.png?raw=true)

#APK
[Click here](https://github.com/4gus71n/ThumbnailApp/raw/master/app-debug.apk) to download the apk debug deploy to your device.

#References
- [Codepath Wiki](http://guides.codepath.com/android)
- [Fernando Cejas Clean Architecture](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

