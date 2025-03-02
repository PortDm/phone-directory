package demon.person.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

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
    private String post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Department department;

    @Transient
    private String fio;
    public String getFio() {
        return lastName + " " + firstName + " " + secondName;
    }

    @Transient
    private String fioShort;
    public String getFioShort() {
        return lastName + " " + firstName.charAt(0) + "." + secondName.charAt(0) + ".";
    }

    @Transient
    private String nameDepartment;
    public String getNameDepartment() {
        return !Objects.isNull(department) ? department.getNameDepartment() : "";
    }

}
