package service

import org.scalatest.funsuite.AnyFunSuite

class MovieServiceTest extends AnyFunSuite {

  test("test getAllMovies"){
    val movies = MovieService.getAllMovies()
    assert(movies.length > 0)
  }

  test("test getMoviesByDirectorAndStartEndYear"){
    val movies = MovieService.getMoviesByDirectorAndStartEndYear(
      director = "Sidney Franklin", startYear = 1900, endYear = 2000)

    //Positive Cases
    assert(movies.length > 0)

    //Negative Cases
    assertThrows[Exception]{
      MovieService.getMoviesByDirectorAndStartEndYear(
        director = "Sidney Franklin", startYear = 1950, endYear = 1925)
    }
    assertThrows[Exception]{
      MovieService.getMoviesByDirectorAndStartEndYear(
        director = "Sidney Franklin", startYear = -1, endYear = 1925)
    }
    assertThrows[Exception]{
      MovieService.getMoviesByDirectorAndStartEndYear(
        director = "Sidney Franklin", startYear = 1950, endYear = -4)
    }
  }

  test("test getMoviesByUserReviewsAndLanguage"){
    val movies = MovieService.getMoviesByUserReviewsAndLanguage(userReviews = 100, language = "German")

    // Positive Cases
    assert(movies.length > 0)
    if(movies.length > 1){
      val movieA = movies(0)
      val movieB = movies(movies.length - 1)
      assert(movieA.reviewsFromUser.toString.toInt > movieB.reviewsFromUser.toString.toInt)
    }

    // Negative Cases
    assertThrows[Exception]{
      MovieService.getMoviesByUserReviewsAndLanguage(userReviews = -2, language = "German")
    }
  }

  test("test getMoviesByCountryAndYear"){
    val movies = MovieService.getMoviesByCountryAndYear("USA", 1920)

    // Positive Cases
    assert(movies.length > 0)
    if(movies.length > 1){
      val movieA = movies(0)
      val movieB = movies(movies.length - 1)
      assert(MovieService.compareMoviesByBudget(movieA.budget.toString, movieB.budget.toString))
    }

    // Negative Cases
    assertThrows[Exception]{
      MovieService.getMoviesByCountryAndYear("USA", -1)
    }
  }

  test("test getMoviesByCountryAndVoters"){
    val movies = MovieService.getMoviesByCountryAndVoters(country = "USA", votes = 1500)

    // Positive Cases
    assert(movies.length > 0)
    if(movies.length > 1){
      val movieA = movies(0)
      val movieB = movies(movies.length - 1)
      assert(movieA.votes.toString.toInt > movieB.votes.toString.toInt)
    }

    // Negative Cases
    assertThrows[Exception]{
      MovieService.getMoviesByCountryAndVoters(country = "USA", votes = -1)
    }
  }

  test("test getMoviesByBudgetRangeAndCounty"){
    val movies = MovieService.getMoviesByBudgetRangeAndCounty(
      country = "USA", minBudget = 5000, maxBudget = 20000)

    //Positive Cases
    assert(movies.length > 0)

    //Negative Cases
    assertThrows[Exception]{
      MovieService.getMoviesByBudgetRangeAndCounty(
        country = "USA", minBudget = 5000, maxBudget = 200)
    }
    assertThrows[Exception]{
      MovieService.getMoviesByBudgetRangeAndCounty(
        country = "USA", minBudget = -3, maxBudget = 200)
    }
    assertThrows[Exception]{
      MovieService.getMoviesByBudgetRangeAndCounty(
        country = "USA", minBudget = 5000, maxBudget = -5)
    }
  }
}
