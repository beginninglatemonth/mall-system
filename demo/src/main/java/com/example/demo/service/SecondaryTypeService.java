package com.example.demo.service;

import com.example.demo.pojo.MainType;
import com.example.demo.pojo.SecondaryType;
import com.example.demo.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * SecondaryTypeService
 */
public interface SecondaryTypeService {

    Integer save(SecondaryType secondaryType);

    Integer update(Integer id, SecondaryType secondaryType);

    Integer updateDeleteByID(Integer id);

    MainType getOne(String name);

    List selectType();

    Map<String, Object> getNoDeleted(Integer id);

    List<SecondaryType> getListNoDeleted(Integer mainTypeId);

    PageUtil getPageList(Integer current, Integer size);

    PageUtil getPageListByName(Integer current, Integer size, String name);


}
