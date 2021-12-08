package tr.com.english.learnlang.controller.userController;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/user/{id}/words")
    public List<Word> getWordsUser(@PathVariable("id") Long id){
        return wordService.getWordsByUserId(id);
    }// Kullanıcının kelimelelerini getir.

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        System.out.println("geldim");
        userService.addRoleToUser(form.getEmail(),form.getRoleName());
        return ResponseEntity.ok().build();
    }



}
@Data
class RoleToUserForm{
    private String roleName;
    private String email;
}