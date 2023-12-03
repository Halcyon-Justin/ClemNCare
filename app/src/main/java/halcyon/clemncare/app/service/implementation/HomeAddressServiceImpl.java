package halcyon.clemncare.app.service.implementation;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.HomeAddressDTO;
import halcyon.clemncare.app.exception.AddressNotFoundException;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.service.HomeAddressService;

@Service
public class HomeAddressServiceImpl implements HomeAddressService {

    @Autowired
    HomeAddressRepository homeAddressRepository;

    @Override
    public HomeAddress createAddress(HomeAddressDTO addressDTO) {
        HomeAddress address = new HomeAddress();
        BeanUtils.copyProperties(addressDTO, address);
        return homeAddressRepository.save(address);
    }

    @Override
    public HomeAddress updateAddress(Long id, HomeAddressDTO addressDTO) {
        Optional<HomeAddress> optionalAddress = homeAddressRepository.findById(id);
        if(optionalAddress.isPresent()) {
            HomeAddress existingAddress = optionalAddress.get();
            BeanUtils.copyProperties(addressDTO, existingAddress);
            return homeAddressRepository.save(existingAddress);
        } else {
            throw new AddressNotFoundException("Address with ID " + id + " not found");
        }
    }

    @Override
    public HomeAddress partialUpdateAddress(Long id, HomeAddressDTO addressDTO) {
        Optional<HomeAddress> optionalAddress = homeAddressRepository.findById(id);
        if(optionalAddress.isPresent()) {
            HomeAddress existingAddress = optionalAddress.get();
            BeanUtils.copyProperties(addressDTO, existingAddress);
            return homeAddressRepository.save(existingAddress);
        } else {
            throw new AddressNotFoundException("Address with ID " + id + " not found");
        }
    }

    @Override
    public void deleteAddress(Long addressId) {
        homeAddressRepository.deleteById(addressId);
    }

    @Override
    public HomeAddress getAddress(Long addressId) {
        return homeAddressRepository.findById(addressId).orElse(null);
    }

}
