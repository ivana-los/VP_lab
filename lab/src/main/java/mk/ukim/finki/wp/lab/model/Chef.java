package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Chef {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes;
}
