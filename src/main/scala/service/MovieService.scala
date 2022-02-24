package service

import util.MoviesReader
import models.Movie

object MovieService {

  val movieList = try{
    println("\nloading data...")
    MoviesReader.getAllMovies
  } catch {
    case ex: Exception => throw ex
  }

  def getAllMovies(): List[Movie] = movieList

  def getMoviesByDirectorAndStartEndYear(director: String, startYear: Int, endYear: Int): List[Movie] = {

    if(startYear <= 0) throw Exception("Invalid Start year, must be numeric value and greater than 0")
    if(endYear <= 0) throw Exception("Invalid End year, must be numeric value and greater than 0")
    if(startYear >= endYear) throw Exception("Start year must be before End year")
    val resultList = movieList.filter(movie => movie.director.toString.contains(director) && movie.year !="" &&
      (startYear <= movie.year.toString.toInt && movie.year.toString.toInt <= endYear))
    resultList
  }

  def getMoviesByUserReviewsAndLanguage(userReviews: Int, language: String = "English"): List[Movie] = {
    if(userReviews <= 0) throw Exception("Invalid User review, must be numeric value and greater than 0")
    val resultList = movieList.filter(movie => {
      movie.language == language && movie.reviewsFromUser.toString != ""
        && movie.reviewsFromUser.toString.toInt >= userReviews
    }).sortWith(_.reviewsFromUser.toString.toInt > _.reviewsFromUser.toString.toInt)
    resultList
  }

  def getMoviesByCountryAndYear(country: String, year: Int): List[Movie] = {
    if(year <= 0) throw Exception("Invalid year, must be numeric value and greater than 0")
    val resultList = movieList.filter(movie => movie.year.toString.toInt == year
      && movie.country == country && movie.budget != "").sortWith((movieA: Movie, movieB: Movie)=>{
      compareMoviesByBudget(movieA.budget.toString, movieB.budget.toString)
    })
    resultList
  }

  def getMoviesByCountryAndVoters(votes: Int, country: String): List[Movie] = {
    if(votes <= 0) throw Exception("Invalid votes, must be numeric value and greater than 0")
    val resultList = movieList.filter(movie => {
      movie.country == country && movie.votes.toString != ""
        && movie.votes.toString.toInt >= votes
    }).sortWith(_.votes.toString.toInt > _.votes.toString.toInt)
    resultList
  }

  def compareMoviesByBudget(budgetA: String, budgetB: String) = {
    val numerics = "0123456789"
    val budgetAInt = budgetA.toCharArray.filter(c => numerics.contains(c)).mkString.toInt
    val budgetBInt = budgetB.toCharArray.filter(c => numerics.contains(c)).mkString.toInt
    budgetAInt > budgetBInt
  }

}
