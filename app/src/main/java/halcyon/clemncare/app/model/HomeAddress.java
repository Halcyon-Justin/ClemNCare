package halcyon.clemncare.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import halcyon.clemncare.app.enums.StateCode;
import lombok.Data;

@Data
@Entity
@Table(name = "home_address")
public class HomeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;
    private String city;

    @Enumerated(EnumType.STRING)
    private StateCode state;

    private String zipCode;

}
