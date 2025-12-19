package dam.Fullstack.ConflictTrackerAPI.mapper;

import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryCreateDto;
import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryResponseDto;
import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryUpdateDto;
import dam.Fullstack.ConflictTrackerAPI.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponseDto toResponseDto(Country country);

    List<CountryResponseDto> toResponseDtos(List<Country> countries);

    @Mapping(target = "id", ignore = true)
    Country toEntity(CountryCreateDto dto);

    @Mapping(target = "id", ignore = true)
    Country toEntity(CountryUpdateDto dto);
}
