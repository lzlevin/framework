package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.common.entity.Sequence;
import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;
import com.vf.validate.group.CreateGroup;
import com.vf.validate.validator.BeanValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础新增服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface CreateService<E, DTO, PO> extends BaseService<E, DTO, PO> {

    /**
     * 新增一条<br>
     * 暂未采用{@link org.springframework.validation.annotation.Validated}注解
     *
     * @param dto 新增的数据
     * @return 是否新增成功
     */
    @Log(action = "新增")
    default boolean save(DTO dto) {
        Assert.isNull(dto, "新增数据不能为空");
        E entity = createEntity(dto);
        setSeq(entity);
        BeanValidator.validate(entity, CreateGroup.class);
        return getDao().save(entity);
    }

    /**
     * 批量新增
     *
     * @param list 新增数据list
     * @return 新增数量
     */
    @Log(action = "批量新增")
    @Transactional(rollbackFor = Throwable.class)
    default int saveInBatch(Collection<DTO> list) {
        Assert.isEmpty(list, "批量新增数据不能为空");
        List<E> collect = list.stream().map(t -> createEntity(t)).collect(Collectors.toList());
        collect.forEach(t -> BeanValidator.validate(t, CreateGroup.class));
        collect.forEach(this::setSeq);
        return getDao().saveInBatch(collect);
    }

    /**
     * 设置序列号
     *
     * @param entity
     */
    default void setSeq(E entity) {
        if (entity instanceof Sequence) {
            Long seq = ((Sequence) entity).getSeq();
            if (null == seq) {
                QueryWrapper<E> queryWrapper = Wrappers.query(createEntity()).select("seq");
                seq = (Long) max(queryWrapper);
            }
            if (null == seq) {
                seq = 0L;
            }
        }
    }
}
