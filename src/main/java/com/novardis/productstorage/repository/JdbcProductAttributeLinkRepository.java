package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductAttributeLinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcProductAttributeLinkRepository implements ProductAttributeLinkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(ProductAttributeLinkDto productAttributeLinkDto) {
        final String sqlQuery = "insert into product_attribute_link (attribute_link_id, product_id, attribute_dic_id) values(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setLong(1, productAttributeLinkDto.getAttributeLinkId());
            stmt.setLong(2, productAttributeLinkDto.getProductId());
            stmt.setLong(3, productAttributeLinkDto.getDicId());
            return stmt;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

}
