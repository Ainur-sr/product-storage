package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.AttributeDicDto;
import com.novardis.productstorage.dto.DictionaryAttributeDto;
import com.novardis.productstorage.dto.ProductAttributeLinkDto;
import com.novardis.productstorage.dto.ProductDto;
import com.novardis.productstorage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductRepository productRepository;
    private final AttributeDicRepository attributeDicRepository;
    private final DictionaryAttributeRepository dictionaryAttributeRepository;
    private final DictionaryAttributeValRepository dictionaryAttributeValRepository;
    private final ProductAttributeLinkRepository productAttributeLinkRepository;
    private final AttributeLinkRepository attributeLinkRepository;

    private final ProductService productService;

    @Transactional
    @Override
    public Product addAttributeToProduct(ProductAttributePK param) {
        Product resultProduct = null;

        ProductDto productDto = productRepository.findById(param.getProductId()).orElse(null);
        DictionaryAttributeDto dictionaryAttributeDto = null;
        //словарь
        AttributeDicDto attributeDicDto = attributeDicRepository.findById(param.getDicId()).orElse(null);
        if (attributeDicDto != null){
            //атрибут из словаря
            dictionaryAttributeDto = dictionaryAttributeRepository.getByDicNameAndId(attributeDicDto.getTableName(), param.getAttributeId()).orElse(null);
        }
        if (productDto != null && dictionaryAttributeDto != null) {
           //1
            final Long index = attributeLinkRepository.createIndex();
            if (index != null){
                //2 запись значения в таблицы attribute_01_value или attribute_02_value
                Long attributeValueId = dictionaryAttributeValRepository.save(
                        attributeDicDto.getValueTableName(),
                        param.getAttributeValue(),
                        dictionaryAttributeDto.getId(),
                        index);

                if (attributeValueId != null && attributeValueId > 0){
                    ProductAttributeLinkDto productAttributeLinkDto = new ProductAttributeLinkDto();
                    productAttributeLinkDto
                            .setAttributeLinkId(index)
                            .setProductId(param.getProductId())
                            .setDicId(param.getDicId());
                    //3
                    Long linkRecordId = productAttributeLinkRepository.save(productAttributeLinkDto);
                    if (linkRecordId != null){
                        resultProduct = productService.getById(param.getProductId());
                    }
                }
            }

        }

        return resultProduct;
    }
}
