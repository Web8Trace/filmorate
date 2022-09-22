package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Validator {
    public static boolean validatedFilm(Film film) {
        if(film.getName()==null||film.getName().isEmpty()){
            return false;
        }
        if(film.getDescription().length()>200){
            return false;
        }
        if(film.getReleaseDate().isBefore(LocalDate.parse("1895-12-28"))){
            return false;
        }
        if (film.getDuration()<0){
            return false;
        }
        return true;
    }

    public static boolean validatedUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return false;
        }
        if (!user.getEmail().contains("@")){
            return false;
        }
        if (user.getLogin()==null|| user.getLogin().isEmpty()){
            return false;
        }
        if (user.getLogin().contains(" ")){
            return false;
        }
        if(user.getName()==null||user.getName().isEmpty()){
            user.setName(user.getLogin());
        }
        if(user.getBirthday().isBefore(LocalDate.now())){
            return false;
        }
        return true;
    }
}


