package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.DictionaryAttributeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcDictionaryAttributeRepository implements DictionaryAttributeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<DictionaryAttributeDto> getAllByDicName(String dicTableName) {
        String query = String.format("select * from %s", dicTableName);
        return jdbcTemplate.query(
                query,
                (rs, rowNum) -> {
                    DictionaryAttributeDto dictionaryAttributeDto = new DictionaryAttributeDto();
                    dictionaryAttributeDto
                            .setId(rs.getLong("id"))
                            .setName(rs.getString("name"))
                            .setDescription(rs.getString("description"))
                            .setUnit(rs.getString("unit"));

                    return dictionaryAttributeDto;
                }
        );
    }

    @Override
    public Optional<DictionaryAttributeDto> getByDicNameAndId(String dicTableName, Long attributeId) {
        String query = String.format("select * from %s where id = %d", dicTableName, attributeId);
        return jdbcTemplate.queryForObject(
                query,
                (rs, rowNum) -> Optional.of(new DictionaryAttributeDto(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("unit")
                        )
                )
        );

    }
}
