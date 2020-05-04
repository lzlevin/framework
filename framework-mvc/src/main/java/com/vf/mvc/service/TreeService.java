package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.common.entity.ChildrenAware;
import com.vf.common.entity.Parent;
import com.vf.common.util.TreeUtils;
import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author levin
 * @date 2020/4/30
 * @since 1.0.0
 */
public interface TreeService<E extends Parent<Long>, DTO, PO extends ChildrenAware<PO>>
        extends BaseService<E, DTO, PO> {

    /**
     * 根据查询条件查询所有的节点并组装成相应的树形集合
     *
     * @param dto 查询条件
     * @return 树形集合
     */
    @Log(action = "根据条件查询树")
    default List<PO> tree(DTO dto) {
        Assert.isNull(dto, "查询条件不能为空");
        E entity = createEntity(dto);
        List<E> list = getDao().list(Wrappers.query(entity));
        Map<Long, List<E>> collect = list.stream().collect(Collectors.groupingBy(Parent::getParentId));
        List<E> es = collect.get(TreeUtils.DEFAULT_LONG_ROOT);
        Assert.isEmpty(es, "根节点不存在");
        Map<Long, List<PO>> poMap = collect.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, t -> this.createPOList(t.getValue())));
        for (Map.Entry<Long, List<PO>> entry : poMap.entrySet()) {
            int size = entry.getValue().size();
            for (int i = 0; i < size; i++) {
                PO po = entry.getValue().get(i);
                Long id = collect.get(entry.getKey()).get(i).getId();
                po.setChildren(poMap.get(id));
            }
        }
        return poMap.get(TreeUtils.DEFAULT_LONG_ROOT);
    }

    /**
     * 根据查询条件查询第一层数据，并根据父子节点查询指定深度的数据<br/>
     * 循环层级进行数据查询，当{@code depth}小于0时，默认设置为{@link TreeUtils#DEFAULT_LEAF_DEPTH}，且不能大于10
     *
     * @param id    查询条件
     * @param depth 深度
     * @return
     */
    @Log(action = "根据条件和深度查询树")
    default List<PO> leaf(Long id, Integer depth) {
        Assert.isNull(id, "ID不能为空");
        if (null == depth || depth < 0) {
            depth = TreeUtils.DEFAULT_LEAF_DEPTH;
        }
        Assert.isTrue(depth > 10, "深度不能大于10");
        E byId = getDao().getById(id);
        if (null == byId) {
            return Collections.emptyList();
        }
        List<E> list = new ArrayList<>();
        list.add(byId);
        throw new UnsupportedOperationException("未实现");
    }


    /**
     * 根据父ID查询节点
     *
     * @param id 父节点ID
     * @return
     */
    @Log(action = "根据父节点ID查询")
    default List<PO> leaf(Long id) {
        E entity = createEntity();
        entity.setParentId(id);
        return createPOList(this.getDao().list(Wrappers.query(entity)));
    }
}
