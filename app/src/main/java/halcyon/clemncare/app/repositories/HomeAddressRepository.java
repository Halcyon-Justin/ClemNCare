package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import halcyon.clemncare.app.model.HomeAddress;

public interface HomeAddressRepository extends JpaRepository<HomeAddress, Long>{
    
}
