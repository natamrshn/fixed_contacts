package spring.boot.optic.okulist.service.order;

import spring.boot.optic.okulist.dto.order.AddressDto;

public interface AddressService {
    AddressDto getById(Long addressId);
    AddressDto getAvailablePickupAddress();

    AddressDto createAddress(AddressDto addressDto);

    void deleteAddress(Long addressId);

    AddressDto updateAddressAvailability(Long addressId, boolean isAvailableForPickup);
    AddressDto getDefaultAddress();
}
