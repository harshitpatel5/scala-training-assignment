package application

import models.Movie
import service.MovieService

object MyScalaApplication extends App {

  start

  def start: Unit = {
    println("Enter a number from 1 to 5, depending on the case you want to test: ")
    val caseInput = try {
      scala.io.StdIn.readInt()
    } catch {
      case e: NumberFormatException => println("You did not entered a numberic value, try again\n")
      case _ => println("Something unexpected went wrong, terminating the application")
    }
    caseInput match {
      case 1 => {
        println("You are about to fetch movies by Director, Start year and End year")
        print("\nEnter Director's name: ")
        val director = scala.io.StdIn.readLine()
        print("Enter Start year: ")
        val startYear: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
              case e: NumberFormatException => println("You did not entered a numberic value\n")
              case _ => println("Something unexpected went wrong")
            }
          0
        }
        print("Enter End year: ")
        val endYear: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("You did not entered a numberic value\n")
            case _ => println("Something unexpected went wrong")
          }
          0
        }
        val movies = try{
          MovieService.getMoviesByDirectorAndStartEndYear(director, startYear, endYear)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something unexpected went wrong, terminating the application")
          }
          List()
        }
        if(movies.length != 0) printMovies(movies) else println("No records found!")
      }
      case 2 => {
        println("You are about to fetch movies by user reviews and Language in descending order by user reviews")
        print("\nEnter language: ")
        val language = scala.io.StdIn.readLine()
        print("Enter minimum user review: ")
        val userReview: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("You did not entered a numberic value\n")
            case _ => println("Something unexpected went wrong")
          }
          0
        }
        val movies = try{
          MovieService.getMoviesByUserReviewsAndLanguage(userReview, language)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something unexpected went wrong, terminating the application")
          }
          List()
        }
        if(movies.length != 0) printMovies(movies) else println("No records found!")
      }
      case 3 => {
        println("You are about to fetch movies by country and year of release in descending order by budget")
        print("\nEnter country: ")
        val country = scala.io.StdIn.readLine()
        print("Enter minimum user review: ")
        val year: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("You did not entered a numberic value\n")
            case _ => println("Something unexpected went wrong")
          }
            0
        }
        val movies = try{
          MovieService.getMoviesByCountryAndYear(country, year)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something unexpected went wrong, terminating the application")
          }
            List()
        }
        if(movies.length != 0) printMovies(movies) else println("No records found!")
      }
      case 4 => {
        println("You are about to fetch movies by country and minimum votes in descending order by votes")
        print("\nEnter country: ")
        val country = scala.io.StdIn.readLine()
        print("Enter minimum user review: ")
        val votes: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("You did not entered a numberic value\n")
            case _ => println("Something unexpected went wrong")
          }
            0
        }
        val movies = try{
          MovieService.getMoviesByCountryAndVoters(votes, country)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something unexpected went wrong, terminating the application")
          }
            List()
        }
        if(movies.length != 0) printMovies(movies) else println("No records found!")
      }
      case 5 => {
        println("You are about to fetch movies by country, minimum budget and maximum budget")
        print("\nEnter County's name: ")
        val country = scala.io.StdIn.readLine()
        print("Enter minimum budget(numeric value): ")
        val minBudget: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("You did not entered a numberic value\n")
            case _ => println("Something unexpected went wrong")
          }
            0
        }
        print("Enter maximum budget(numeric value): ")
        val maxBudget: Int = try {
          scala.io.StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("You did not entered a numberic value\n")
            case _ => println("Something unexpected went wrong")
          }
            0
        }
        val movies = try{
          MovieService.getMoviesByBudgetRangeAndCounty(country = country, minBudget = minBudget, maxBudget = maxBudget)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something unexpected went wrong, terminating the application")
          }
            List()
        }
        if(movies.length != 0) printMovies(movies) else println("No records found!")
      }
      case _ => println("Wrong Enter again\n")
    }
  }

  // Added vote and Language too for better comparison of data
  def printMovies(moviesList: List[Movie]): Unit = {
    moviesList.map(movie=>println(s"Title: ${movie.title} | Published Year: ${movie.year} " +
      s"| Budget: ${movie.budget} | User Review: ${movie.reviewsFromUser} | Country: ${movie.country}" +
      s" | Genre: ${movie.genre} | Duration: ${movie.duration} | Votes: ${movie.votes} | Language: ${movie.language}"))
  }
}
