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
  private final UserRepository userRepository;
  private       Validator      validator;

  @Autowired
  public UserService(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  /**
   * That's a method wherewith user is created.
   *
   * @param userName the username which is necessary for register.
   * @param password the password which is necessary for register.
   * @param role     the role which the user has.
   */

  public void createUser(String userName, @Valid String password, String role)//TODO posle da napravq anotaciqta za parolata @ValidPassword
  {
    validator.validateIsNull(userName);
    validator.validateIsNull(role);

    userRepository.createUser(userName, password);
  }

  public void delete(String username)
  {
    userRepository.delete(username);
  }

  public User findByUserName(String username)
  {
    return userRepository.findByUserName(username);
  }
}
