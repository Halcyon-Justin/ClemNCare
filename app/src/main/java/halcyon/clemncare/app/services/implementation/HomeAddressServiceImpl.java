package halcyon.clemncare.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.services.HomeAddressService;

@Service
public class HomeAddressServiceImpl implements HomeAddressService {

    @Autowired
    HomeAddressRepository homeAddressRepository;

    @Override
    public String createAddress(HomeAddress address) {
        homeAddressRepository.save(address);
        return "Address Saved Successfully";
    }

    @Override
    public String updateAddress(HomeAddress address) {
        homeAddressRepository.save(address);
        return "Address Updated Successfully";
    }

    @Override
    public String deleteAddress(Long addressId) {
        homeAddressRepository.deleteById(addressId);
        return "Address Deleted Successfully";
    }

    @Override
    public HomeAddress getAddress(Long addressId) {
        return homeAddressRepository.getById(addressId);
    }

    @Override
    public List<Guardian> getGuardiansById(Long addressId) {
        return homeAddressRepository.findGuardiansById(addressId);
    }


}
