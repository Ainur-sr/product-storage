package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeOneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcAttributeOneRepository implements AttributeOneRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(AttributeOneDto attributeOneDto) {
        String sqlQuery = "insert into attribute_01_value (value, attribute_01_dic_id) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, attributeOneDto.getValue());
            stmt.setLong(2, attributeOneDto.getAttributeId());
            return stmt;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean update(AttributeOneDto attributeOneDto) {
        return jdbcTemplate.update("update attribute_01_value set value = ? where id = ?",
                attributeOneDto.getValue(),
                attributeOneDto.getAttributeId()) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("delete from attribute_01_value where id = ?", id) > 0;
    }

    @Override
    public List<AttributeOneDto> findAll() {
        return jdbcTemplate.query(
                "select * from attribute_one_dic_view",
                (rs, rowNum) -> {
                    AttributeOneDto attributeOneDto = new AttributeOneDto();
                    attributeOneDto
                            .setAttributeId(rs.getLong("attribute_id"))
                            .setAttributeDicId(rs.getLong("attribute_dic_id"))
                            .setName(rs.getString("attribute_name"))
                            .setDescription(rs.getString("attribute_description"))
                            .setUnit(rs.getString("attribute_unit"))
                            .setValue(rs.getString("attribute_value"));
                    return attributeOneDto;
                }
        );
    }

    @Override
    public Optional<AttributeOneDto> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from attribute_one_dic_view where attribute_id = ?",
                new Object[]{id},
                (rs, rowNum) -> Optional.of(new AttributeOneDto(
                        rs.getLong("attribute_id"),
                        rs.getLong("attribute_dic_id"),
                        rs.getString("attribute_name"),
                        rs.getString("attribute_description"),
                        rs.getString("attribute_unit"),
                        rs.getString("attribute_value")))
        );
    }

}
