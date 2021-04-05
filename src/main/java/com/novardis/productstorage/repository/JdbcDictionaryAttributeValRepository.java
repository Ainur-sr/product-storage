package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.DictionaryAttributeValDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcDictionaryAttributeValRepository implements DictionaryAttributeValRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(String attributeValTableName, String value, Long attributeDicId, Long attributeLinkId, Long productId) {
        final String sqlQuery = String.format("insert into %s (value, attribute_dic_id, attribute_link_id, product_id) values(?, ?, ?, ?)",
                attributeValTableName);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, value);
            stmt.setLong(2, attributeDicId);
            stmt.setLong(3, attributeLinkId);
            stmt.setLong(4, productId);
            return stmt;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<DictionaryAttributeValDto> findByProductId(String attributeValTableName, Long productId) {
        final String sqlQuery = String.format("select id, value, attribute_dic_id, attribute_link_id, product_id from %s where product_id = ?",
                attributeValTableName);
        return jdbcTemplate.queryForObject(
                sqlQuery,
                (rs, rowNum) -> {
                    DictionaryAttributeValDto dto = new DictionaryAttributeValDto();
                    dto
                            .setAttributeId(rs.getLong("id"))
                            .setAttributeValue(rs.getString("value"))
                            .setAttributeDicId(rs.getLong("attribute_dic_id"))
                            .setAttributeLinkId(rs.getLong("attribute_link_id"))
                            .setProductId(rs.getLong("product_id"));
                    return Optional.of(dto);
                },
                productId
        );
    }

/*    @Override
    public boolean update(DictionaryAttributeValDto dictionaryAttributeValDto) {
        return jdbcTemplate.update("update attribute_01_value set value = ? where id = ?",
                dictionaryAttributeValDto.getAttributeValue(),
                dictionaryAttributeValDto.getAttributeId()) > 0;
    }*/

//    @Override
//    public boolean deleteById(Long id) {
//        return jdbcTemplate.update("delete from attribute_01_value where id = ?", id) > 0;
//    }

}
