package halcyon.clemncare.app.service;

import halcyon.clemncare.app.dto.HomeAddressDTO;
import halcyon.clemncare.app.model.HomeAddress;

public interface HomeAddressService {

    public HomeAddress createAddress(HomeAddressDTO addressDTO);

    public HomeAddress updateAddress(Long id, HomeAddressDTO addressDTO);

    public HomeAddress partialUpdateAddress(Long id, HomeAddressDTO addressDTO);

    public void deleteAddress(Long addressId);

    public HomeAddress getAddress(Long id);

}
