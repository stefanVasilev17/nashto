package Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

/**
 * Date: 1/28/2021 Time: 2:49 PM
 * <p>
 *
 *
 * @author Vladislav_Zlatanov
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private Set<Class> classes;
}
