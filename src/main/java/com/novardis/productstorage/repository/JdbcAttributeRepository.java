package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeDicDto;
import com.novardis.productstorage.dto.AttributeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcAttributeRepository implements AttributeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public List<AttributeDto> findAllByProductId(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        List<AttributeDto> resultList = namedJdbcTemplate.query(
                "select * from product_attribute_view where product_id = :id",
                parameters,
                (rs, rowNum) -> {
                    AttributeDto attributeDto = new AttributeDto();
                    attributeDto
                            .setProductId(rs.getLong("product_id"))
                            .setProductName(rs.getString("product_name"))
                            .setDicId(rs.getLong("dic_id"))
                            .setAttributeId(rs.getLong("attribute_id"))
                            .setAttributeName(rs.getString("attribute_name"))
                            .setAttributeDescription(rs.getString("attribute_description"))
                            .setAttributeUnit(rs.getString("attribute_unit"))
                            .setAttributeValue(rs.getString("attribute_value"));

                    return attributeDto;
                }
        );

        return resultList;
    }

    @Override
    public List<AttributeDto> findAll() {
        return jdbcTemplate.query(
                "select * from product_attribute_view",
                (rs, rowNum) ->{
                    AttributeDto attributeDto = new AttributeDto();
                    attributeDto
                            .setProductId(rs.getLong("product_id"))
                            .setProductName(rs.getString("product_name"))
                            .setDicId(rs.getLong("dic_id"))
                            .setAttributeId(rs.getLong("attribute_id"))
                            .setAttributeName(rs.getString("attribute_name"))
                            .setAttributeDescription(rs.getString("attribute_description"))
                            .setAttributeUnit(rs.getString("attribute_unit"))
                            .setAttributeValue(rs.getString("attribute_value"));

                    return attributeDto;
                }
        );
    }
}
