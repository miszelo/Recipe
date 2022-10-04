package recipes.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Email(regexp = "regexp = \"^(?=.{1,64}@)[A-Za-z0-9\\\\+_-]+(\\\\.[A-Za-z0-9\\\\+_-]+)*@\"\n"
            + "+ \"[^-][A-Za-z0-9\\\\+-]+(\\\\.[A-Za-z0-9\\\\+-]+)*(\\\\.[A-Za-z]{2,})$\"")
    @NotBlank
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Min(value = 8)
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes;
}
