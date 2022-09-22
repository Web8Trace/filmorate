package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.filmorate.validator.Validator.validatedFilm;

@RestController
@RequestMapping("/films")
public class FilmController {
    private List<Film> films = new ArrayList<>();
    public static Long staticId=1L;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @GetMapping
    List<Film>getFilms(){
        log.info("Текущее число пользователей: {}", films.size());
        return films;
    }

    @PostMapping
    Film postFilm(@RequestBody Film film){
        if(validatedFilm(film)){
            film.setId(staticId++);
            films.add(film);
        }else {
            throw new ValidationException();
        }
        return film;
    }

    @PutMapping
    Film putFilm(@PathVariable Long id,
                 @RequestBody Film film){
        if(validatedFilm(film)){
            for (Film f:films){
                if (f.getId().equals(film.getId())){
                 films.remove(f);
                 films.add(film);
                }else {
                    films.add(film);
                }
            }
        }
        return film;
    }






}
