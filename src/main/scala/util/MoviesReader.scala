package util

import models.Movie

import java.io.FileNotFoundException

object MoviesReader {

  def getAllMovies: List[Movie] = {
    var moviesList: List[Movie] = List()
    val bufferedSource = try{
      io.Source.fromFile("src/main/resources/movies_dataset.csv")
    } catch {
      case ex: FileNotFoundException=>{
        throw FileNotFoundException("File not found at specified location")
      }
      case ex: Exception => {
        throw Exception("Something went wrong in reading CSV Data")
      }
    }
    var counter = 0
    for (line <- bufferedSource.getLines) {
      if(counter <= 10000){
        val cols = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1).map(_.trim)
        val movie: Movie = new Movie()
        movie.imdbTitleId = cols(0)
        movie.title = cols(1)
        movie.originalTitle = cols(2)
        movie.year = cols(3)
        movie.datePublished = cols(4)
        movie.genre = cols(5)
        movie.duration = cols(6)
        movie.country = cols(7)
        movie.language = cols(8)
        movie.director = cols(9)
        movie.writer = cols(10)
        movie.productionCompany = cols(11)
        movie.actors = cols(12)
        movie.description = cols(13)
        movie.averageVote = cols(14)
        movie.votes = cols(15)
        movie.budget = cols(16)
        movie.usaGrossIncome = cols(17)
        movie.worldwideGrossIncome = cols(18)
        movie.metascore = cols(19)
        movie.reviewsFromUser = cols(20)
        movie.reviewsFromCritics = cols(21)
        moviesList = moviesList :+ movie
      }
      counter += 1
    }
    bufferedSource.close
    moviesList.drop(1)
  }
}
