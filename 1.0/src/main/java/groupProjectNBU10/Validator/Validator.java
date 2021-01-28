package groupProjectNBU10.Validator;

import groupProjectNBU10.Model.User;
import groupProjectNBU10.Roles;
import groupProjectNBU10.exceptions.AccessForbiddenException;
import groupProjectNBU10.exceptions.IllegalDataException;
import org.springframework.stereotype.Component;

@Component
public class Validator
{


  public void validateBuyerIsNotAdmin(String userRole)
  {
    if (userRole.equalsIgnoreCase(Roles.ADMIN_ROLE)) {
      throw new IllegalDataException("This user is Admin!");
    }
  }


  public void validateEqualStrings(String string1, String string2)
  {
    if (!string1.equals(string2)) {
      throw new IllegalDataException("These strings are not equal!");
    }
  }

  public void validateIsNotNull(Object o)
  {
    if (o != null) {
      throw new IllegalDataException("This object exists!");
    }
  }

  public void validateIsNull(Object o)
  {
    if (o == null) {
      throw new AccessForbiddenException("Empty data !");
    }
  }
}
