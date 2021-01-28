package groupProjectNBU10.Service;

import groupProjectNBU10.Model.User;
import groupProjectNBU10.Repository.RoleRepository;
import groupProjectNBU10.Repository.UserRepository;
import groupProjectNBU10.Validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService
{

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private Validator      validator;
  private UserService    userService;

  @Autowired
  public AdminService(UserRepository userRepository, RoleRepository roleRepository, Validator validator
      , UserService userService)
  {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.validator = validator;
    this.userService = userService;
  }

  /**
   * That's a method wherewith admin is created.
   * There is a check if user is null, if it is, an exception is thrown.
   *
   * @param username the username which is necessary for logging.
   * @param password the password which is necessary for logging.
   */

  public void createAdmin(String username,
      String password, String email)
  {
    validator.validateIsNotNull(userRepository.findUserPerRegistration(username));
    userRepository.createAdmin(username, password, email);
    userService.enableUser(username);
  }

  /**
   * That's a method wherewith admin is deleted.
   * There is a check if user is null, if it is, an exception is thrown.
   *
   * @param username the username which is necessary for logging.
   */

  public void deleteAdmin(String username)
  {
    validator.validateIsNull(userRepository.findByUserName(username));
    roleRepository.deleteRole("ADMIN", username);
    userRepository.delete(username);
  }

  /**
   * That's a method wherewith admin can lock an user.
   *
   * @param lock     it is a boolean type
   * @param username the username which is necessary for logging.
   */

  public void lockUser(boolean lock, String username)
  {
    User u = userRepository.findByUserName(username);
    userRepository.setLock(username, lock);
  }
}
