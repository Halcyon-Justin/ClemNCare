package halcyon.clemncare.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import halcyon.clemncare.app.enums.StateCode;

@Data
@Entity
@Table(name = "home_address")
public class HomeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String streetAddress;
    private String city;
    private StateCode state;
    private String zipCode;

}
