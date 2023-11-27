package halcyon.clemncare.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import halcyon.clemncare.app.dto.RegistrationRequest;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.services.implementation.RegistrationServiceImpl;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    private ChildRepository childRepository;

    @Mock
    private GuardianRepository guardianRepository;

    @Mock
    private HomeAddressRepository homeAddressRepository;

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    void testRegisterNewFamily() {
        // Create a RegistrationRequest with sample data
        RegistrationRequest registrationRequest = createSampleRegistrationRequest();

        // Mock the repository save methods
        when(childRepository.save(any(Child.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(guardianRepository.save(any(Guardian.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(homeAddressRepository.save(any(HomeAddress.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(familyRepository.save(any(Family.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method to be tested
        Family result = registrationService.registerNewFamily(registrationRequest);

        // Verify that the save methods were called with the correct arguments
        verify(childRepository, times(registrationRequest.getChildren().size())).save(any(Child.class));
        verify(guardianRepository, times(registrationRequest.getGuardians().size() + 1)).save(any(Guardian.class));
        verify(homeAddressRepository, times(1)).save(any(HomeAddress.class));
        verify(familyRepository, times(1)).save(any(Family.class));

        // Add more assertions as needed based on your specific requirements
        assertNotNull(result);
        assertEquals(registrationRequest.getAddress(), result.getAddress());
        assertEquals(registrationRequest.getChildren(), result.getChildren());
        assertEquals(registrationRequest.getGuardians(), result.getGuardians());
        assertEquals(registrationRequest.getEmergencyContact(), result.getEmergencyContact());
    }

    private RegistrationRequest createSampleRegistrationRequest() {
        // Create and return a sample RegistrationRequest for testing
        // You can customize this based on your test scenario
        RegistrationRequest request = new RegistrationRequest();
        request.setChildren(Arrays.asList(new Child(), new Child()));
        request.setGuardians(Arrays.asList(new Guardian(), new Guardian()));
        request.setAddress(new HomeAddress());
        request.setEmergencyContact(new Guardian());
        return request;
    }
}


