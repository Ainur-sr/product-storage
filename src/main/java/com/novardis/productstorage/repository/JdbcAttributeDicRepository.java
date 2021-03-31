package com.novardis.productstorage.repository;

import com.novardis.productstorage.domain.AttributeDic;
import com.novardis.productstorage.dto.AttributeDicDto;
import com.novardis.productstorage.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcAttributeDicRepository implements AttributeDicRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AttributeDicDto> findAll() {
        return jdbcTemplate.query(
                "select * from attribute_dic",
                (rs, rowNum) ->
                        new AttributeDicDto(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description")
                        )
        );
    }
}
