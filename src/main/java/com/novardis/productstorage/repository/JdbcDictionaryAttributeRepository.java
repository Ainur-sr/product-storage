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
        final String query = String.format("select * from %s", dicTableName);
        return jdbcTemplate.query(
                query,
                (rs, rowNum) -> new DictionaryAttributeDto()
                        .setId(rs.getLong("id"))
                        .setName(rs.getString("name"))
                        .setDescription(rs.getString("description"))
                        .setUnit(rs.getString("unit"))
        );
    }

    @Override
    public Optional<DictionaryAttributeDto> getByDicNameAndId(String dicTableName, Long attributeId) {
        final String query = String.format("select * from %s where id = %d", dicTableName, attributeId);
        return jdbcTemplate.queryForObject(
                query,
                (rs, rowNum) -> Optional.of(new DictionaryAttributeDto()
                        .setId(rs.getLong("id"))
                        .setName(rs.getString("name"))
                        .setDescription(rs.getString("description"))
                        .setUnit(rs.getString("unit")))
        );
    }
}
