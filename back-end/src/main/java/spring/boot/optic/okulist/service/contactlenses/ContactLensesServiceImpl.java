package spring.boot.optic.okulist.service.contactlenses;

import static spring.boot.optic.okulist.service.liquid.LiquidServiceImpl.getStrings;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.contactlenses.ContactLensesMapper;
import spring.boot.optic.okulist.model.lenses.ContactLenses;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.repository.lenses.ContactLensesRepository;
import spring.boot.optic.okulist.repository.lenses.ManufacturerRepository;

@Service
@RequiredArgsConstructor
public class ContactLensesServiceImpl implements ContactLensesService {

    private final ContactLensesRepository contactLensesRepository;
    private final ContactLensesMapper contactLensesMapper;
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public ContactLensesResponseDto createContactLenses(
            ContactLensesRequestDto contactLensesRequestDto) {
        ContactLenses lenses = contactLensesMapper.toModel(contactLensesRequestDto);
        Manufacturer manufacturer = manufacturerRepository.findById(contactLensesRequestDto
                        .getLensConfigurationId())
                .orElseThrow(() -> new EntityNotFoundException("Configuration not found with ID: "
                        + contactLensesRequestDto.getLensConfigurationId()));
        lenses.setLensConfiguration(manufacturer);
        lenses = contactLensesRepository.save(lenses);
        return contactLensesMapper.toDto(lenses);
    }

    @Override
    public List<ContactLensesResponseDto> findAll(Pageable pageable) {
        return contactLensesRepository.findAll()
                .stream()
                .map(contactLensesMapper::toDto)
                .toList();
    }

    @Override
    public ContactLensesResponseDto getById(Long id) {
        ContactLenses lenses = contactLensesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found lenses with ID: " + id)
        );
        return contactLensesMapper.toDto(lenses);
    }

    @Override
    public ContactLensesResponseDto update(Long id,
                                           ContactLensesRequestDto contactLensesRequestDto) {
        ContactLenses existingLenses = contactLensesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find lenses with ID: " + id));
        BeanUtils.copyProperties(contactLensesRequestDto, existingLenses,
                getNullPropertyNames(contactLensesRequestDto));
        ContactLenses updatedLenses = contactLensesRepository.save(existingLenses);
        return contactLensesMapper.toDto(updatedLenses);
    }

    @Override
    public void deleteLensesById(Long lensesId) {
        ContactLenses lenses = contactLensesRepository.findById(lensesId).orElseThrow(
                () -> new EntityNotFoundException("Can't found lenses with ID :" + lensesId)
        );
        lenses.setDeleted(true);
        contactLensesRepository.save(lenses);
    }

    private String[] getNullPropertyNames(Object source) {
        return getStrings(source);
    }
}
