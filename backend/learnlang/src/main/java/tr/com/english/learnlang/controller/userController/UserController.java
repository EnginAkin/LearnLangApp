package tr.com.english.learnlang.controller.userController;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.entity.role.Role;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.words.Word;
import tr.com.english.learnlang.service.role.RoleService;
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

    @Autowired
    private RoleService roleService;

    @GetMapping("/user")
    public User getUser(@RequestParam("id") Long id){
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("authanticate")
    GeneralResponse authenticateUser() {
        return new GeneralResponse("başarılı",true);
    }

    @GetMapping("/user/wordList/{userId}")
    public List<Word> getWordsUser(@PathVariable("userId") Long id){
        return wordService.getListWordsByUserId(id);
    }// Kullanıcının kelimelelerini getir.

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/word/addLearnedList")
    public ResponseEntity<?> addLearnedList(@RequestParam("wordId") Long wordId , @RequestParam("userId") Long userId){
        wordService.addWordToUserLearnedList(userId,wordId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/word/addUserWordList")
    public ResponseEntity<?> addUserWordList(@RequestParam("wordId") Long wordId , @RequestParam("userId") Long userId){
        wordService.addUserWordList(userId,wordId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/learnedWords")
    public List<Word> getLearnedUser(@RequestParam("userId") Long id){
        System.out.println("Girildi getLearnedWords list User Id "+id);
        return wordService.getLearnedWordsByUserId(id);
    }



}
@Data
class RoleToUserForm{
    private String roleName;
    private String email;
}