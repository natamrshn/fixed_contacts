package spring.boot.optic.okulist.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.order.AddressDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.AddressMapper;
import spring.boot.optic.okulist.model.Address;
import spring.boot.optic.okulist.repository.AddressRepository;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDto getById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDto getAvailablePickupAddress() {
        Address address = addressRepository.getAvailablePickupAddress();
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressLine1(addressDto.getAddressLine1());
        address.setAddressLine2(addressDto.getAddressLine2());
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());
        Address createdAddress = addressRepository.save(address);
        return addressMapper.toDto(createdAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public AddressDto updateAddressAvailability(Long addressId, boolean isAvailableForPickup) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));

        address.setAvailableForPickup(isAvailableForPickup);
        Address updatedAddress = addressRepository.save(address);

        return addressMapper.toDto(updatedAddress);
    }

    @Override
    public AddressDto getDefaultAddress() {
        Long defaultStoreId = 1L;
        Address defaultStoreAddress = addressRepository.findById(defaultStoreId)
                .orElseThrow(() -> new EntityNotFoundException("Default store address not found with id: " + defaultStoreId));
        return addressMapper.toDto(defaultStoreAddress);
    }

}