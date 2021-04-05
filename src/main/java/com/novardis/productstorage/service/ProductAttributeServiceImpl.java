package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.criteria.ProductAttributeDeletePK;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.*;
import com.novardis.productstorage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public Product addProductAttribute(ProductAttributePK param) {
        Product resultProduct = null;
        //проверка наличия продукта
        findProductById(param.getProductId());
        //словарь словарей
        final AttributeDicDto attributeDicDto = findAttributeDicDto(param.getDicId());
        //атрибут из словаря
        final DictionaryAttributeDto dictionaryAttributeDto = findDictionaryAttributeDto(attributeDicDto, param.getAttributeId());

        //№1 создание индекса
        final Long index = attributeLinkRepository.createIndex();
        if (index != null) {
            //№2 запись значения в таблицы attribute_01_value или attribute_02_value
            Long attributeValueId = dictionaryAttributeValRepository.save(
                    attributeDicDto.getValueTableName(),
                    param.getAttributeValue(),
                    param.getAttributeId(),
                    index,
                    param.getProductId());

            if (attributeValueId != null && attributeValueId > 0) {
                ProductAttributeLinkDto productAttributeLinkDto = new ProductAttributeLinkDto();
                productAttributeLinkDto
                        .setAttributeLinkId(index)
                        .setProductId(param.getProductId())
                        .setDicId(param.getDicId());
                //№3 запись значения внешних ключей в таблицу product_attribute_link
                Long linkRecordId = productAttributeLinkRepository.save(productAttributeLinkDto);
                if (linkRecordId != null) {
                    resultProduct = productService.getById(param.getProductId());
                }
            }
        }

        return resultProduct;
    }

    @Transactional
    @Override
    public Product updateProductAttribute(ProductAttributePK param) {
        Product resultProduct = null;
        //проверка наличия продукта
        findProductById(param.getProductId());
        //словарь словарей
        final AttributeDicDto attributeDicDto = findAttributeDicDto(param.getDicId());
        //атрибут из словаря
        final DictionaryAttributeDto dictionaryAttributeDto = findDictionaryAttributeDto(attributeDicDto, param.getAttributeId());
        //запись с таблицы атрибутов
        final DictionaryAttributeValDto dictionaryAttributeValDto = findDictionaryAttributeValDto(
                attributeDicDto.getValueTableName(),
                param.getProductId(),
                param.getAttributeId());

        final boolean isUpdated = dictionaryAttributeValRepository.updateValue(
                attributeDicDto.getValueTableName(),
                param.getAttributeValue(),
                dictionaryAttributeValDto.getAttributeId());

        if (isUpdated) {
            resultProduct = productService.getById(param.getProductId());
        }

        return resultProduct;
    }

    @Transactional
    @Override
    public Product deleteProductAttribute(ProductAttributeDeletePK param) {
        Product resultProduct = null;
        //проверка наличия продукта
        findProductById(param.getProductId());
        //словарь словарей
        final AttributeDicDto attributeDicDto = findAttributeDicDto(param.getDicId());
        //запись с таблицы атрибутов
        final DictionaryAttributeValDto dictionaryAttributeValDto = findDictionaryAttributeValDto(attributeDicDto.getValueTableName(), param.getProductId(), param.getAttributeId());

        final boolean isDeleted = attributeLinkRepository.deleteById(dictionaryAttributeValDto.getAttributeLinkId());

        if (isDeleted) {
            resultProduct = productService.getById(param.getProductId());
        }

        return resultProduct;
    }

    private ProductDto findProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Can't find product with id = %d", productId)));
    }

    private AttributeDicDto findAttributeDicDto(Long dicId) {
        return attributeDicRepository.findById(dicId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Can't find dictionary with id = %d", dicId)));
    }

    private DictionaryAttributeDto findDictionaryAttributeDto(AttributeDicDto attributeDicDto, Long attributeId) {
        return dictionaryAttributeRepository.getByDicNameAndId(attributeDicDto.getTableName(), attributeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Can't find attribute in table %s with id = %d", attributeDicDto.getName(), attributeId)));
    }

    private DictionaryAttributeValDto findDictionaryAttributeValDto(String attributeValTableName, Long productId, Long attributeDicId) {
        return dictionaryAttributeValRepository.findByProductIdAndAttributeDicId(attributeValTableName, productId, attributeDicId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Can't find attribute in table %s with productId = %d", attributeValTableName, productId)));
    }


}
