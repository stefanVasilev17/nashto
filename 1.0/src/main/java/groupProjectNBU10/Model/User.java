package groupProjectNBU10.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class  User
{

  private String      username;
  //@ValidPassword
  private String      password;
  private Set<String> role;

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public Set<String> getRole()
  {
    return role;
  }

  public void setRole(Set<String> role)
  {
    this.role = role;
  }


}
