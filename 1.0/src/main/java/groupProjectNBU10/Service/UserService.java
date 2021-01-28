package groupProjectNBU10.Service;

import groupProjectNBU10.Model.User;
import groupProjectNBU10.Repository.UserRepository;
import groupProjectNBU10.Validator.Validator;
import groupProjectNBU10.exceptions.IllegalDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserService
{
  private UserRepository      userRepository;
  private Validator           validator;

  public UserService()
  {
  }

  @Autowired
  public UserService(UserRepository userRepository,Validator validator)
  {
    this.userRepository = userRepository;
    this.validator = validator;

  }

  /**
   * That's a method wherewith user is created.
   * There is a check if user is null, if it is, an exception is thrown.
   * There is a check if the role is admin, if it is , an exception is thrown.
   * There is a check if the username is 'admin', if it is, an exception is thrown.
   *
   * @param userName the username which is necessary for logging.
   * @param password the password which is necessary for logging.
   * @param role     the role which the user has.

   */

  public void createUser(String userName, @Valid String password, String role)
  {
    User u = userRepository.findUserPerRegistration(userName);

    validator.validateIsNotNull(u);

    validator.validateIsNull(userName);
    validator.validateIsNull(role);

    validator.validateBuyerIsNotAdmin(role);
    validateAdminName(userName);
    userRepository.createUser(userName, password);
  }


  /**
   * That's a method wherewith user get it's balance in portfolio.
   *
   * @param username the username which is necessary for logging.
   */



  public User findByUserName(String username)
  {
    return userRepository.findByUserName(username);
  }

  public User findAdmin(String username)
  {
    return userRepository.findByUserName(username);
  }

  public void delete(String username)
  {
    userRepository.delete(username);
  }

  public void enableUser(String username)//for testing
  {
    userRepository.enableUser(username);
  }

  private void validateAdminName(String name1)
  {
    if (name1.equalsIgnoreCase("admin")) {
      throw new IllegalDataException("The username cannot be 'admin'!");
    }
  }
}
