package groupProjectNBU10.Repository;

import groupProjectNBU10.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class UserRepository
{
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final RoleRepository             roleRepository;

  @Autowired
  private UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RoleRepository roleRepository)
  {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.roleRepository = roleRepository;
  }

  /**
   * That's a method wherewith an user is created
   * using namedParameterJdbcTemplate.
   *
   * @param username the username which user can log into database.
   * @param password the necessary password for logging.
   */

  public void createUser(String username, String password)
  {
    // by default isLocked is false , and balance in portFolio is 0
    String sql = "INSERT INTO USERS(username,password,emai) VALUES (:username ,:password);";
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);
    mapSqlParameterSource.addValue("password", password);

    namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
  }

  /**
   * That's a method wherewith an admin is created
   * using namedParameterJdbcTemplate.
   *
   * @param username the username which admin can log into database.
   * @param password the necessary password for logging.
   */

  public void createAdmin(String username, String password)
  {
    String sql = "INSERT INTO USERS(username,password) VALUES (:username ,:password);";
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);
    mapSqlParameterSource.addValue("password", password);

    namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
  }

  /**
   * Retrieves an information about user.
   *
   * @param username the username which user can log into database.
   * @return an object of User.
   */

  public User findByUserName(String username)
  {
    String sql = "SELECT * FROM USERS WHERE USERNAME = :username";
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);

    return namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource,
        (ResultSet rs, int rowNum) -> {
          User user = new User();
          user.setUsername(rs.getString("username"));
          user.setPassword(rs.getString("password"));

          //  Set<String> roles = roleRepository.getAllRoles(user.getUsername());
          //  user.setRole(roles);
          return user;
        }
    );
  }

  /**
   * Deletes user.
   *
   * @param username the username which user can log into database.
   */

  public void delete(String username)
  {
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);
    String sql = "DELETE FROM USERS WHERE USERNAME= :username";
    try {
      namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
    catch (EmptyResultDataAccessException e) {
//TODO to be finished, when exceptions are made
    }
  }
}
