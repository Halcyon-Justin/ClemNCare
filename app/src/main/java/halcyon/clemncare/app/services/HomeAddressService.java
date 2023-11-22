package halcyon.clemncare.app.services;

import java.util.List;

import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;

public interface HomeAddressService {

    public String createAddress(HomeAddress address);

    public String updateAddress(HomeAddress address);

    public String deleteAddress(Long addressId);

    public HomeAddress getAddress(Long id);

    public List<Guardian> getGuardiansById(Long addressId);

}
