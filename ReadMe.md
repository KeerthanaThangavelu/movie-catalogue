# Movie Catalogue System (Spring Boot + TMDb API)

This is a Java Spring Boot web application that integrates with [The Movie Database (TMDb)](https://www.themoviedb.org/) API to display trending movies, view detailed movie info, and manage a list of favorite movies using an in-memory H2 database.


## Features

- Fetch and display trending/popular movies using the TMDb API
- View detailed information about each movie
- Add or remove movies from favorites
- Favorites are stored in an H2 in-memory database
- Thymeleaf used for elegant UI rendering

## Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Thymeleaf
- RestTemplate
- TMDb API

## Getting Started

### 1. Clone the Repo

```
git clone https://github.com/your-username/movie-catalogue-system.git
cd movie-catalogue-system.
```
### 2. Configure the API Key

Set your TMDb API key in src/main/resources/application.yml:

```
tmdb:
  api-key: YOUR_TMDB_API_KEY
  base-url: https://api.themoviedb.org/3/movie/popular?api_key=
```

Or alternatively set it as an environment variable:
```
export TMDB_API_KEY=your_key_here
```

### 3. Build and Run the Application
```
mvn clean package
java -jar ./target/movie-catalogue-0.0.1-SNAPSHOT.jar
```

Access the app at:  http://localhost:9017/

## Pages Overview
|                   URL Path                    |             #Description             |
|:---------------------------------------------:|:------------------------------------:| 
|                       /                       |         View trending movies         |
|               /movie/{movieId}                |        View movie detail page        |
|                  /favorites                   | View and manage favorite movies list |
|                   /favorite                   |    Add movie to favorites (POST)     |
|               /remove-favorite                |  Remove movie from favorites (POST)  |




  
