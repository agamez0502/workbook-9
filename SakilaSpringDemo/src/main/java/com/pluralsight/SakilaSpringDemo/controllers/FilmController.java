package com.pluralsight.SakilaSpringDemo.controllers;

import com.pluralsight.SakilaSpringDemo.dao.FilmDao;
import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    @Qualifier("jdbcFilmDao")
    private FilmDao filmDao;

    @GetMapping("/api/films")
    public List<Film> getFilms() {
        return filmDao.getAll();
    }

    @GetMapping("/")
    public String defaultRequest(@RequestParam(defaultValue = "Hola Amigis") String name, @RequestParam String favColor){
        return "Hello " + name + " I hear your favorite color is " + favColor;
    }
}