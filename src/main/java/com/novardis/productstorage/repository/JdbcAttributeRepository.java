package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcAttributeRepository implements AttributeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AttributeDto> findAllByProductId(Long id) {
        return jdbcTemplate.query(
                "select * from product_attribute_view where product_id = ?",
                (rs, rowNum) -> {
                    AttributeDto attributeDto = new AttributeDto();
                    attributeDto
                            .setProductId(rs.getLong("productId"))
                            .setProductName(rs.getString("productName"))
                            .setDicId(rs.getLong("dic_id"))
                            .setAttributeId(rs.getLong("attributeId"))
                            .setAttributeName(rs.getString("attributeName"))
                            .setAttributeDescription(rs.getString("attributeDescription"))
                            .setAttributeUnit(rs.getString("attributeUnit"))
                            .setAttributeValue(rs.getString("attributeValue"));

                    return attributeDto;
                }
        );
    }
}
