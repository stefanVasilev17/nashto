package groupProjectNBU10.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleRepository
{
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  @Autowired
  public RoleRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
  {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  /**
   * Retrieves an information about user's roles.
   *
   * @param username
   * @return Set<String>
   */

  public Set<String> getAllRoles(String username)
  {
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);
    String sql = "SELECT ROLE FROM ROLES WHERE USERNAME = :username";
    return new HashSet<>(namedParameterJdbcTemplate.queryForList(sql, mapSqlParameterSource, String.class));
  }

  /**
   * A method wherewith admin can add a role to user
   *
   * @param role     the necessary role which admin want to add to user.
   * @param username
   */

  public void addRole(String role, String username)
  {
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);
    mapSqlParameterSource.addValue("role", role);
    String sql = "INSERT INTO ROLES(ROLE , USERNAME) VALUES (:role,:username)";
    namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
  }

  /**
   * A method wherewith admin can delete a role from user.
   *
   * @param role     the necessary role which admin want to delete from user.
   * @param username
   */

  public void deleteRole(String role, String username)
  {
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("username", username);
    mapSqlParameterSource.addValue("role", role);
    String sql = "DELETE FROM  ROLES WHERE ROLE = :role  AND USERNAME = :username";
    namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
  }
}
