package groupProjectNBU10.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Date: 1/28/2021 Time: 3:04 PM
 * <p>
 *
 *
 * @author Vladislav_Zlatanov
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    Set<Student> students;

    @OneToMany(mappedBy = "classes")
    Set<Grade> grades;

    @ManyToOne
    @JoinColumn(name = "teachers_id")
    private Teacher teacher;
}
