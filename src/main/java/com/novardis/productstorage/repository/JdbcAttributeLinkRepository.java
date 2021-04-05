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
        String sqlQuery = "insert into attribute_link (id) values (default)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> connection.prepareStatement(sqlQuery, new String[]{"id"}),
                keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("delete from attribute_link where id = ?", id) > 0;
    }
}
