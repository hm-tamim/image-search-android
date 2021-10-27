# ImageSearch Android

UI is available in Figma:
https://www.figma.com/file/efpcWnBveDor4qwAsP8Ch9/ImageSearch

Demonstration video:
https://youtu.be/sGfahG-LcvE


# Project Overview 

- The application was written in Kotlin. 
- MVVM architectural pattern was followed.
- Android Jetpack libraries were utilized
- Dagger Hilt was used for dependency injection.
- Navigation graph was used to maintain single activity architecture 
- Room persistence ORM was used to save search history and image data for offline capability
- Coroutine for threading and database actions.
- Epoxy library was used to populate RecycleView with multi-types views such as image grid, empty page, loading bar etc
- Retrofit okhttp client was used to call remote API(Pixabay)
- Glide was used for loading images
- Subsampling library was used to implemented zoomable image previewer
- Base classes were written for auto data binding and reducing boilerplate.
- Junit4, Robolectric, Mockito and Espresso were used for unit and instrumental testing
- Shared transition was used (Bonus #1)
- ViewPager2 was implemented slide left/right feature(Bonus #2)


More tests could have been added it there was enough time.

# Screenshots


![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/1.%20home.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/2.%20gallery%202x2.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/3.%20gallery%203x3.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/4.%20gallery%204x4.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/5.%20gallery%20empty%20page.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/6.%20shared%20element%20transition.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/7.%20zoomable%20image%20viewer.png?raw=true)
![homepage](https://github.com/hm-tamim/image-search-android/blob/master/screenshots/8.%20image%20slider.png)
