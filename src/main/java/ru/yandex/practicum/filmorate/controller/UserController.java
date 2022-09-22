package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.filmorate.validator.Validator.validatedUser;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users=new ArrayList<>();
    public static Long staticId=1L;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    List<User>getUsers(){
        log.info("Текущее число пользователей: {}", users.size());
        return users;
    }

    @PostMapping
    User postUser(@RequestBody User user){
        if(validatedUser(user)){
            user.setId(staticId++);
            users.add(user);
        }else {
            throw new ValidationException();
        }
        log.info("Польователь создан, общее количество пользователей: {}", users.size());
        return user;
    }

    @PutMapping
    User putFilm(@PathVariable Long id,
                 @RequestBody User user){
        if(validatedUser(user)){
            for (User u:users){
                if (u.getId().equals(user.getId())){
                    users.remove(u);
                    users.add(user);
                }else {
                    users.add(user);
                }
            }
        }
        log.info("Польователь c идентификатором {} изменен", user.getId());
        return user;
    }




}
