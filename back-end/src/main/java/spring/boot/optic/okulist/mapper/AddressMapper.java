package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.order.AddressDto;
import spring.boot.optic.okulist.model.Address;

@Mapper(config = MapperConfig.class)
public interface AddressMapper {
    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
