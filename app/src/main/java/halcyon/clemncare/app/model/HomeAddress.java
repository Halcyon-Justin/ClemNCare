package halcyon.clemncare.app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import halcyon.clemncare.app.enums.StateCode;

@Data
@Entity
public class HomeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;
    private String city;
    private StateCode state;
    private String zipCode;

    // Constructors, getters, and setters
}
