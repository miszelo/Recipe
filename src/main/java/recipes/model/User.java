package recipes.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Pattern(regexp = ".+@.+\\..+")
    @NotBlank
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes;
}
