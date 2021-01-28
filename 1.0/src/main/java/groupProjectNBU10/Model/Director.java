package groupProjectNBU10.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Date: 1/28/2021 Time: 2:54 PM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author Vladislav_Zlatanov
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "directors")
public class Director extends Person {

    @OneToOne
    private School school;
}
