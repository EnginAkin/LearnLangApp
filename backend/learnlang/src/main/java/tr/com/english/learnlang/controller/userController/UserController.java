package tr.com.english.learnlang.controller.userController;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.words.Word;
import tr.com.english.learnlang.service.user.UserService;
import tr.com.english.learnlang.service.word.WordService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordService wordService;


    @GetMapping("/user")
    public User getUser(@RequestParam("id") Long id){
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }


    @PostMapping("/signup")
    public GeneralResponse addUser(@RequestBody User user){
        return userService.addUser(user);

    }

    @GetMapping("/user/{id}/words")
    public List<Word> getWordsUser(@PathVariable("id") Long id){
        return wordService.getWordsByUserId(id);
    }// Kullanıcının kelimelelerini getir.

}
