package com.novardis.productstorage.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcAttributeLinkRepository implements AttributeLinkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long createIndex() {
        final String sqlQuery = "insert into attribute_link (id) values (default)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> connection.prepareStatement(sqlQuery, new String[]{"id"}),
                keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean deleteByAttributeId(Long attributeId) {
        return jdbcTemplate.update("delete from attribute_link where id = ?", attributeId) > 0;
    }

    @Override
    public boolean deleteByProductId(Long productId) {
        final String sqlQuery = "delete from attribute_link al where al.id in " +
                "(select pal.attribute_link_id from product_attribute_link pal where product_id = ?)";

        return jdbcTemplate.update(sqlQuery, productId) > 0;
    }
}
