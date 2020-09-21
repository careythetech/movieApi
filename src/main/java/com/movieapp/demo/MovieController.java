package com.movieapp.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/movies/")
@CrossOrigin(origins = "http://localhost:4200" )
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/getMovies")
    public Object getMovies(){
        try{
            return this.movieRepository.findAll();
        }
        catch(Exception exception){
            return "Expection occured: " + exception;
        }
    }

    @GetMapping("/getMovie/{id}")
    public Object getMovie(@PathVariable Long id){
        try{
            return this.movieRepository.findById(id);
        }
        catch(Exception exception){
            return "Expection occured: " + exception;
        }

    }

    // This will save the movie entity in the body of the request into database
    @PostMapping("/postMovie")
    public Object insertMovie(@RequestBody MovieEntity movieEntity){
        try{
            return movieRepository.save(movieEntity);
        }
        catch (Exception exception){
            return "Expection occured: " + exception;
        }

    }
    // @RequestBody contains the object to update
    //@PathVariable contains the id of that object
    @PutMapping("/update/{id}")
    public Object updateMovie(@RequestBody MovieEntity  movieEntity, @PathVariable Long id){
        try{
            if(movieRepository.findById(id) == null){
                return "Movie Not Found";
            }
            movieEntity.setId(id);
            movieRepository.save(movieEntity);
            return movieRepository.findById(id);
        }
        catch (Exception exception){
            return "Expection occured: " + exception;
        }
    }

    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieRepository.deleteById(id);
        return "Movie By Id : " + id + " Deleted";
    }

}
