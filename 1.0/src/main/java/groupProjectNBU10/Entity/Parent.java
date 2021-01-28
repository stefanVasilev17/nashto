package groupProjectNBU10.Entity;

import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Date: 1/28/2021 Time: 3:17 PM
 * <p>
 *
 *
 * @author Vladislav_Zlatanov
 */
public class Parent extends Person{
    @ManyToMany
    Set<Student> kids;
}
