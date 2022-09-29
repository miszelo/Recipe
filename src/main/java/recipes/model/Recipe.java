package recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "RECIPES")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "ID", nullable = false)
    private long id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "DESCRIPTION")
    private String description;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "INGREDIENTS")
    private List<String> ingredients;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "DIRECTIONS")
    private List<String> directions;
}
