package com.example.task2films

public class Film {
     var title: String? = null
     var year: String? = null
     var  runtime: String? = null;
     var  genre: String? = null;
     var director: String? = null;
     var writer: String? = null;
     var actors: String? = null;
     var plot: String? = null;
     var language: String? = null;
     var country: String? = null;
     var Awards: String? = null;

     constructor(
          title: String?,
          year: String?,
          runtime: String?,
          genre: String?,
          director: String?,
          writer: String?,
          actors: String?,
          plot: String?,
          language: String?,
          country: String?,
          Awards: String?
     ) {
          this.title = title
          this.year = year
          this.runtime = runtime
          this.genre = genre
          this.director = director
          this.writer = writer
          this.actors = actors
          this.plot = plot
          this.language = language
          this.country = country
          this.Awards = Awards
     }
}