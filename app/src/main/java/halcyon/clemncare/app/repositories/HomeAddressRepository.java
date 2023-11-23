package halcyon.clemncare.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.enums.StateCode;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;

@Repository
public interface HomeAddressRepository extends JpaRepository<HomeAddress, Long>{

    List<Guardian> findGuardiansById(Long id);

    HomeAddress findByStreetAddressAndCityAndStateAndZipCode(String streetAddress, String city, StateCode state,
            String zipCode);
    
}
