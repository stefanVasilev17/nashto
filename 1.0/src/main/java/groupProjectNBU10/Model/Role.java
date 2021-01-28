package groupProjectNBU10.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Date: 1/28/2021 Time: 2:39 PM
 * <p>
 *
 *
 * @author Vladislav_Zlatanov
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private RoleType role;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("role")
    private List<Person> persons;
}
