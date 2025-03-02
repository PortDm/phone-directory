package demon.person.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String phoneIpNumber;
    private String department;
    private String post;

    @Transient
    private String fio;
    public String getFio() {
        return lastName + " " + firstName + " " + secondName;
    }

}
