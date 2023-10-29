package com.example.demo.service.impl;

import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.MainTypeMapper;
import com.example.demo.mapper.SecondaryTypeMapper;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.MainType;
import com.example.demo.pojo.SecondaryType;
import com.example.demo.service.SecondaryTypeService;
import com.example.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SecondaryTypeServiceImpl
 * 二级商品类型服务实现类
 */
@Service
public class SecondaryTypeServiceImpl extends BaseServiceImpl<SecondaryType> implements SecondaryTypeService {

    @Autowired
    private MainTypeMapper mainTypeMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SecondaryTypeMapper secondaryTypeMapper;

    /**
     * 将数据库查询结果转换为指定格式的分页数据
     *
     * @param page 分页数据
     *
     * @return 转换后的分页数据
     */
    private PageUtil wrapPageQuery(PageUtil<Map<String, Object>> page) {
        List<Map<String, Object>> collect = page.getData().stream().map(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", value.get("id"));
            map.put("name", value.get("name"));
            map.put("remark", value.get("remark"));
            map.put("mainTypeId", value.get("main_type_id"));
            map.put("type", value.get("type"));
            map.put("isDeleted", value.get("is_deleted"));
            return map;
        }).collect(Collectors.toList());

        page.setData(collect);
        return page;
    }

    /**
     * 插入一条 SecondaryType 记录到数据库中。
     *
     * @param secondaryType 要插入的 SecondaryType 对象
     *
     * @return 插入操作影响的行数
     */
    public Integer save(SecondaryType secondaryType) {
        return secondaryTypeMapper.insert(secondaryType);
    }

    /**
     * 根据主键 ID 更新 SecondaryType 记录。
     *
     * @param id            要更新的记录的 ID
     * @param secondaryType 包含要更新的字段的 SecondaryType 对象
     *
     * @return 更新操作影响的行数
     */
    @Override
    public Integer update(Integer id, SecondaryType secondaryType) {
        return secondaryTypeMapper.update(id, secondaryType);
    }

    /**
     * 根据主键 ID 删除 SecondaryType 记录。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除操作影响的行数
     */
    public Integer updateDeleteByID(Integer id) {
        return secondaryTypeMapper.updateDeleteByID(id);
    }

    /**
     * 根据名称查询 MainType 记录。
     *
     * @param name 要查询的名称
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    @Override
    public MainType getOne(String name) {
        return secondaryTypeMapper.selectOneByName(name);
    }

    /**
     * 获得所有未删除类型及其关联的商品信息
     *
     * @return 包含主类型、二级类型和商品信息的列表
     */
    public List selectType() {
        List<Map<String, Object>> mainTypes = mainTypeMapper.selectListNoDeleted();
        List<Map<String, Object>> secondaryTypes = secondaryTypeMapper.selectListNoDeleted();
        List<Goods> goods = goodsMapper.selectListNoDeleted();
        List list = new ArrayList();
        mainTypes.forEach(mainType -> {
            List secondaryTypesList = new ArrayList();
            Map<String, Object> mainTypeMap = new HashMap<>();
            secondaryTypes.forEach(secondaryType -> {
                Map<String, Object> secondaryTypeMap = new HashMap<>();
                if (mainType.get("id") == secondaryType.get("main_type_id")) {
                    secondaryTypeMap.put("id", secondaryType.get("id"));
                    secondaryTypeMap.put("name", secondaryType.get("name"));
                    secondaryTypesList.add(secondaryTypeMap);
                    List goodsList = new ArrayList();
                    goods.forEach(good -> {
                        Map<String, Object> goodMap = new HashMap<>();
                        if (secondaryType.get("id") == good.getType()) {
                            goodMap.put("id", good.getId());
                            goodMap.put("name", good.getName());
                            goodMap.put("price", good.getPrice());
                            goodMap.put("image", good.getImage());
                            goodsList.add(goodMap);
                        }
                        secondaryTypeMap.put("goodsList", goodsList);
                        mainTypeMap.put("id", mainType.get("id"));
                        mainTypeMap.put("name", mainType.get("name"));
                        mainTypeMap.put("secondaryTypeList", secondaryTypesList);
                    });
                }
                mainTypeMap.put("id", mainType.get("id"));
                mainTypeMap.put("name", mainType.get("name"));
                mainTypeMap.put("secondaryTypeList", secondaryTypesList);
            });
            list.add(mainTypeMap);
        });
        return list;
    }

    /**
     * 查询指定 ID 的未删除 SecondaryType 记录。
     *
     * @param id SecondaryType 记录的 ID
     *
     * @return 查询到的 SecondaryType 对象，如果不存在则返回 null
     */
    public Map<String, Object> getNoDeleted(Integer id) {
        SecondaryType value = secondaryTypeMapper.getNoDeleted(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", value.getId());
        map.put("name", value.getName());
        map.put("mainTypeId", value.getMainTypeId());
        return map;
    }

    /**
     * 根据主类型 ID 查询未删除的 SecondaryType 记录列表。
     *
     * @param mainTypeId 主类型 ID
     *
     * @return 未删除的 SecondaryType 记录列表
     */
    public List<SecondaryType> getListNoDeleted(Integer mainTypeId) {
        return secondaryTypeMapper.selectListByMainTypeIdNoDeleted(mainTypeId);
    }

    /**
     * 查询所有未删除的 SecondaryType 记录列表。
     *
     * @return 所有未删除的 SecondaryType 记录列表
     */
    public PageUtil getPageList(Integer current, Integer size) {
        PageUtil<Map<String, Object>> page = readPageMapList(
                current,
                size,
                secondaryTypeMapper.count(),
                (offset, pageSize) -> secondaryTypeMapper.selectPageList(offset, pageSize)
        );
        return wrapPageQuery(page);
    }

    /**
     * 根据名称模糊搜索未删除的 SecondaryType 记录列表，分页显示。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    要搜索的名称
     *
     * @return 模糊搜索的 SecondaryType 记录列表
     */
    public PageUtil<Map<String, Object>> getPageListByName(Integer current, Integer size, String name) {
        PageUtil<Map<String, Object>> page = readPageMapList(
                current,
                size,
                secondaryTypeMapper.countByName(name),
                (offset, pageSize) -> secondaryTypeMapper.selectPageListByName(offset, pageSize, name)
        );
        return wrapPageQuery(page);
    }
}
