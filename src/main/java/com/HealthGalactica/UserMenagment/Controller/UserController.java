package com.HealthGalactica.UserMenagment.Controller;
import com.HealthGalactica.UserMenagment.Model.UserModel;
import com.HealthGalactica.UserMenagment.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@EnableWebMvc
@ComponentScan(basePackages = "com.example.UserManagment")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getUser")
    public Optional<UserModel> showUser(@RequestParam Long id){
        return userService.findById(id);
    }
    @GetMapping("/getAllUsers")
    public String showAllUser(Model model){
        List<UserModel> users = userService.getAll();
        model.addAttribute("users",users);
        return "getAllUsers";
    }
    @GetMapping("/index")
    public String homeWebpage(){
        return "index";
    }

    @GetMapping("/add")
    public String showAddUserForm (Model model){
        model.addAttribute("newUser",new UserModel());
        return "create_user";
    }

    @PostMapping("/add")
    public String addUser (@ModelAttribute ("newUser") @Valid UserModel user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "create_user";
        }
        userService.addUser(user);
        model.addAttribute("user",userService.getAll());
        return "index";
    }

    @GetMapping("/login")
    public String showLoginpage(Model model){
        model.addAttribute("user",new UserModel());
        return "logIn";
    }
    @PostMapping("/login")
    public String userLogin(Model model,@ModelAttribute("user") UserModel user,
                            @RequestParam (value="email")String email,
                            @RequestParam (value="password")String password){

        Optional<UserModel> temp = userService.findByMailAndPass(email,password);
        if(temp.isPresent()){
            user= temp.get();
            model.addAttribute("user",user);
            if(user.getUserType().equals("user")){
                return "user/loginUserHomepage";
            }else if(user.getUserType().equals("admin")){
                return "admin/loginAdminHomepage";
            }else{
                return "healer/loginHealerHomepage";
            }
        }else{

            model.addAttribute("error","Niepoprawne dane logowania");
            return "logIn";
        }

    }

//    @GetMapping("/update/{id}")
//    public String updateUserById(@PathVariable("id") Long id,Model model){
//        Optional<UserModel> optionalUser = userService.findById(id);
//        if(optionalUser.isPresent()){
//            UserModel user= optionalUser.get();
//            model.addAttribute("user",user);
//            return "update";
//        }else{
//            return "error";
//        }
//    }
//    @PostMapping("/update/{id}")
//    public String updateUserById(@ModelAttribute("user") UserModel user){
//        userService.updateUser(user);
//        return "index";
//    }


}
