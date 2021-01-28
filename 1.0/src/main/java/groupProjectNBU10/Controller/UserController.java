package groupProjectNBU10.Controller;

import groupProjectNBU10.Service.RoleService;
import groupProjectNBU10.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/user")
public class UserController
{

  private UserService userService;
  private RoleService roleService;

  @Autowired
  public UserController(UserService userService, RoleService roleService)
  {
    this.userService = userService;
    this.roleService = roleService;
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestParam(name = "username") String username,
                                         @RequestParam(name = "password")  @Valid String password,
                                         @RequestParam(name = "role") String role)
  {
    userService.createUser(username, new BCryptPasswordEncoder().encode(password), role);
    roleService.addRole(role, username);
    return ResponseEntity.ok().build();

  }
}

