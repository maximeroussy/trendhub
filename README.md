![Logo](logo.png)

## Assumptions & Decisions

+ For this simple demo project I'm using the Github API v3 as an unauthenticated user. This saves a lot of work with authentication which is I believe not important or relevant to the task. The main downside is that github limits their api usage when a user isn't authenticated. During heavy use and testing, it's possible to go over those limits and have to wait until they reset (I rarely had this happen for this project). I implemented caching for the calls with OkHttp which made a huge improvement.

+ I quickly realized that the Github API v3 doesn't have an endpoint to fetch or search trending repositories. I'm not sure exactly how they do it internally but most other third party libraries or APIs I've found simply perform web scraping on the trending page. I could have designed a scrapper myself, or simply used one of the existing projects I found. However, based on the requirement to list **Android** specific trending repositories, I decided to adapt the "trending" definition in this case to be the top starred android repositories, listed in descending order. This is easily achieved with the **repository search** endpoint by specifying "android" as a topic and requesting the appropriate sort.

+ I decided to use RxJava instead of Kotlin coroutines as I'm already very familiar with RxJava and its operators. I've been really interested in, and have been testing out Kotlin coroutines lately but I haven't figured out an ideal workflow yet for all situations. I do think that RxJava is often a bit heavy for most usecases.

+ A Github repository's data is massive. Building a fully featured detail view for a repository (mimicking the website) would be a significant undertaking with so many API calls (totaling the number of contributors, commits, branches, pull requests, issues, etc.) that the rate limits might not handle viewing many repositories one after another. I decided to interpret the "simple" part of the requirement in my own way and built a layout that showcases some of the main information for any github repository. I've kept it to 3 api calls, one for the repository details, one for the source directory contents and one for the readme in html form. I was able to design and build a nice looking screen with these api responses. The readme is actually displayed using a webview to load the html from the api call. I felt like it would be too long to set things up to handle the markdown.

## Design & Structure

I've went with a typical app structure for the project:
+ **presentation:** Contains all the view related code and logic including activities, adapters and viewmodels.
+ **dependencyinjection:** Contains all the Dagger2 components and modules to generate our dependency graph and wire everything up.
+ **domain:** Contains the "business logic" of sorts including the usecases, data repository interfaces and main data models.
+ **data:** Contains our data repository interface implementations and behaves as our abstraction layer to our actual data sources. Also contains mapper classes to map between data source models and business domain models.
+ **data/api:** Contains our classes and infrastructure to query and return remote data from the Github API. Usually merits its own package instead of being within the data package, but since there isn't any other data source, such as a local database, for this project, I thought it would be simpler to keep it in this package for clarity.

I often split these packages into their own separate modules, especially for larger scale projects that get quite large. Multiple modules can help reduce build time (parallel module building) and create a more concrete separation and abstraction between our layers. Google has also been pushing heavily the multi-module setup as of late with instant apps, app bundles and dynamic delivery.

I used the MVVM pattern for the app using architecture components (ViewModel and LiveData) and databinding to keep the views as passive as possible and only handling setting up the widgets and visual formatting. The View (activity, fragment, layout) is designed to react to the ViewModel changing it's data. 

## Testing



## Future Considerations

+ **Implementing more specific and detailed error handling and messaging for users:** I've simply implemented a catchall that shows generic error dialogs without any context on the cause of any issues for the sake of this project.

+ **Keep designing and implementing more of the repository detail page:** Much more data could be added to the detail page to bring it closer to or on par with what you see on the website, including navigating to other sections as well. I had intended to implement more of but had to stop here for the time being.
