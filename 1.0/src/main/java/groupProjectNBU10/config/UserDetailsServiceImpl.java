package groupProjectNBU10.config;


import groupProjectNBU10.Model.User;
import groupProjectNBU10.Repository.UserRepository;
import groupProjectNBU10.Service.AdminService;
import groupProjectNBU10.Service.RoleService;
import groupProjectNBU10.Service.UserService;
import groupProjectNBU10.exceptions.AccessForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
  private UserService  userService;
  private RoleService    roleService;
  private AdminService   adminService;
  private UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserService userService, RoleService roleService,
                                AdminService adminService, UserRepository userRepository)
  {
    this.userService = userService;
    this.roleService = roleService;
    this.adminService = adminService;
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String username) throws AccessForbiddenException
  {
    User user = userService.findByUserName(username);

//    if (user.isLocked() || !user.isEnabled()) {
//      throw new AccessForbiddenException("Access forbidden!");
//    }

    Set<String> roles = roleService.getAllRoles(username);
    String[] rolez = roles.toArray(new String[roles.size()]);

    org.springframework.security.core.userdetails.User.UserBuilder builder =
        org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword())
            .roles(rolez);

    return builder.build();
  }

}
