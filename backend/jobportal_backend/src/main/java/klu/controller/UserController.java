package klu.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import klu.model.UserManager;
import klu.model.Users;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    @Autowired
    private UserManager UM;

    @PostMapping("/signup")
    public String signUp(@RequestBody Users u) {
        return UM.adduser(u);
    }

    @GetMapping("/forgotpassword/{email}")
    public String forgotPassword(@PathVariable("email") String emailid) {
        return UM.recoverPassword(emailid);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody Users u) {
        return UM.validateCredentials(u.getEmail(), u.getPassword());
    }

    @PostMapping("/getfullname")
    public String getFullname(@RequestBody Map<String, String> data) {
        return UM.getFullname(data.get("csrid"));
    }
}
