package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductDto;
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
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(ProductDto productDto) {
        final String sqlQuery = "insert into product (name) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, productDto.getName());
            return stmt;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean update(ProductDto productDto) {
        return jdbcTemplate.update("update product set name = ? where id = ?", productDto.getName(), productDto.getId()) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("delete from product where id = ?", id) > 0;
    }

    @Override
    public List<ProductDto> findAll() {
        return jdbcTemplate.query(
                "select * from product",
                (rs, rowNum) -> new ProductDto()
                        .setId(rs.getLong("id"))
                        .setName(rs.getString("name"))
        );
    }

    @Override
    public List<ProductDto> findAllByName(String productName) {
        final String query = "select * from product where name like ?";
        return jdbcTemplate.query(
                query,
                (rs, rowNum) -> new ProductDto()
                        .setId(rs.getLong("id"))
                        .setName(rs.getString("name")),
                "%" + productName + "%"
        );
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from product where id = ?",
                (rs, rowNum) -> Optional.of(new ProductDto()
                        .setId(rs.getLong("id"))
                        .setName(rs.getString("name"))
                ),
                id
        );
    }
}
