package groupProjectNBU10.Controller;

import groupProjectNBU10.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
//@PreAuthorize("hasRole(T(com.example.digitalportfolio.role.Roles).ADMIN_ROLE)")
public class AdminController
{
  private final AdminService adminService;


  @Autowired
  public AdminController(AdminService adminService)
  {
    this.adminService = adminService;
  }


  /**
   * That's a method wherewith admin can be created using /api/v1/user/createAdmin
   *
   * @param username the username which user can log into database.
   * @param password
   * @return ResponseEntity
   */

  @PostMapping("/createadmin")
  public ResponseEntity<Void> createAdmin(@RequestParam String username, @RequestParam String password)
  {
    adminService.createAdmin(username, new BCryptPasswordEncoder().encode(password));
    return ResponseEntity.ok().build();
  }

  /**
   * That's a method wherewith admin can be deleted by another admin using /api/v1/user/deleteadmin
   *
   * @param username the username which user can log into database.
   * @return ResponseEntity
   */

  @DeleteMapping("/deleteadmin")
  public ResponseEntity<Void> deleteAdmin(@RequestParam String username)
  {
    adminService.deleteAdmin(username);
    return ResponseEntity.ok().build();
  }

}
