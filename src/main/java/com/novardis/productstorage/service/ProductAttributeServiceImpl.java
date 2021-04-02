package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.domain.DictionaryAttribute;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.AttributeDicDto;
import com.novardis.productstorage.dto.DictionaryAttributeDto;
import com.novardis.productstorage.dto.ProductAttributeLinkDto;
import com.novardis.productstorage.dto.ProductDto;
import com.novardis.productstorage.repository.AttributeDicRepository;
import com.novardis.productstorage.repository.DictionaryAttributeRepository;
import com.novardis.productstorage.repository.DictionaryAttributeValRepository;
import com.novardis.productstorage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductRepository productRepository;
    private final AttributeDicRepository attributeDicRepository;
    private final DictionaryAttributeRepository dictionaryAttributeRepository;
    private final DictionaryAttributeValRepository dictionaryAttributeValRepository;

    @Override
    public Product addAttributeToProduct(ProductAttributePK param) {
        ProductDto productDto = productRepository.findById(param.getProductId()).orElse(null);

        DictionaryAttributeDto dictionaryAttributeDto = null;
        AttributeDicDto attributeDicDto = attributeDicRepository.findById(param.getAttributeDicId()).orElse(null);
        if (attributeDicDto != null){
            //атрибут из словаря
            dictionaryAttributeDto = dictionaryAttributeRepository.getByDicNameAndId(attributeDicDto.getTableName(), param.getAttributeId()).orElse(null);
        }
        if (productDto != null && dictionaryAttributeDto != null) {
            //запись значения в таблицы attribute_01_value или attribute_02_value
            Long attributeValueId = dictionaryAttributeValRepository.save(attributeDicDto.getTableName(), param.getAttributeValue(), dictionaryAttributeDto.getId());
            if (attributeValueId != null){
                ProductAttributeLinkDto productAttributeLinkDto = new ProductAttributeLinkDto();
                productAttributeLinkDto
                        .setProductId(attributeValueId)
                        .setProductId(param.getProductId())
                        .setAttributeDicId(param.getAttributeDicId());
            }
        }


        return null;
    }
}
