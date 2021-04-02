package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeDicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcAttributeDicRepository implements AttributeDicRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AttributeDicDto> findAll() {
        return jdbcTemplate.query(
                "select * from attribute_dic",
                (rs, rowNum) -> {
                    AttributeDicDto attributeDicDto = new AttributeDicDto();
                    attributeDicDto
                            .setId(rs.getLong("id"))
                            .setName(rs.getString("name"))
                            .setDescription(rs.getString("description"))
                            .setTableName(rs.getString("table_name"))
                            .setValueTableName(rs.getString("value_table_name"));

                    return attributeDicDto;
                }
        );
    }

    @Override
    public Optional<AttributeDicDto> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from attribute_dic where id = ?",
                new Object[]{id},
                (rs, rowNum) -> Optional.of(new AttributeDicDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("table_name"),
                        rs.getString("value_table_name")
                ))
        );
    }
}
