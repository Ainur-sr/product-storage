package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductAttributeViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcProductAttributeViewRepository implements AttributeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public List<ProductAttributeViewDto> findAllByProductId(Long id) {
        final SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        return namedJdbcTemplate.query(
                "select * from product_attribute_view where product_id = :id",
                parameters,
                (rs, rowNum) -> new ProductAttributeViewDto()
                        .setProductId(rs.getLong("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .setDicId(rs.getLong("dic_id"))
                        .setAttributeId(rs.getLong("attribute_id"))
                        .setAttributeName(rs.getString("attribute_name"))
                        .setAttributeDescription(rs.getString("attribute_description"))
                        .setAttributeUnit(rs.getString("attribute_unit"))
                        .setAttributeValue(rs.getString("attribute_value"))
        );
    }

    @Override
    public List<ProductAttributeViewDto> findAllByProductIdIn(Collection<Long> ids) {
        final SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
        return namedJdbcTemplate.query(
                "select * from product_attribute_view where product_id in (:ids)",
                parameters,
                (rs, rowNum) -> new ProductAttributeViewDto()
                        .setProductId(rs.getLong("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .setDicId(rs.getLong("dic_id"))
                        .setAttributeId(rs.getLong("attribute_id"))
                        .setAttributeName(rs.getString("attribute_name"))
                        .setAttributeDescription(rs.getString("attribute_description"))
                        .setAttributeUnit(rs.getString("attribute_unit"))
                        .setAttributeValue(rs.getString("attribute_value"))
        );
    }

    @Override
    public List<ProductAttributeViewDto> findAllByAttributeName(String attributeName) {
        final String query = "select * from product_attribute_view where lower(attribute_name) like ?";
        return jdbcTemplate.query(
                query,
                (rs, rowNum) -> new ProductAttributeViewDto()
                        .setProductId(rs.getLong("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .setDicId(rs.getLong("dic_id"))
                        .setAttributeId(rs.getLong("attribute_id"))
                        .setAttributeName(rs.getString("attribute_name"))
                        .setAttributeDescription(rs.getString("attribute_description"))
                        .setAttributeUnit(rs.getString("attribute_unit"))
                        .setAttributeValue(rs.getString("attribute_value")),
                "%" + attributeName.toLowerCase() + "%"
        );
    }

    @Override
    public List<ProductAttributeViewDto> findAll() {
        return jdbcTemplate.query(
                "select * from product_attribute_view",
                (rs, rowNum) -> new ProductAttributeViewDto()
                        .setProductId(rs.getLong("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .setDicId(rs.getLong("dic_id"))
                        .setAttributeId(rs.getLong("attribute_id"))
                        .setAttributeName(rs.getString("attribute_name"))
                        .setAttributeDescription(rs.getString("attribute_description"))
                        .setAttributeUnit(rs.getString("attribute_unit"))
                        .setAttributeValue(rs.getString("attribute_value"))
        );
    }

}
