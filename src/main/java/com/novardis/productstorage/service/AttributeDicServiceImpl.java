package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.AttributeDic;
import com.novardis.productstorage.dto.AttributeDicDto;
import com.novardis.productstorage.mapper.AttributeDicMapper;
import com.novardis.productstorage.repository.AttributeDicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttributeDicServiceImpl implements AttributeDicService {

    private final AttributeDicRepository attributeDicRepository;
    private final AttributeDicMapper attributeDicMapper;

    @Override
    public List<AttributeDic> getAll() {
        List<AttributeDic> attributeDicList = null;
        List<AttributeDicDto> attributeDicDtoList = attributeDicRepository.findAll();
        if (!CollectionUtils.isEmpty(attributeDicDtoList)) {
            attributeDicList = attributeDicDtoList.stream()
                    .map(attributeDicMapper::toDomain)
                    .collect(Collectors.toList());
        } else {
            attributeDicList = Collections.emptyList();
        }

        return attributeDicList;
    }
}
