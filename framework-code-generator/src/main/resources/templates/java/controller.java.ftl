package ${package.Controller};

import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Service}.${table.serviceName};
import ${package.DTO}.${table.dtoName};
import ${package.Entity}.${entity};
import ${package.VO}.${table.voName};
import com.vf.core.api.ApiResponse;
import com.vf.core.validate.CreateGroup;
import com.vf.core.validate.DeleteGroup;
import com.vf.core.validate.UpdateGroup;
<#if table.hasParent>
import com.vf.core.common.Parent;
</#if>
<#if table.hasUseFlag>
import com.vf.core.dto.MultiIdDTO;
</#if>
<#if table.hasName>
import com.vf.core.vo.NameVO;
</#if>
import com.vf.log.annotation.Log;
import com.vf.utils.bean.BeanUtil;
import com.vf.validate.validator.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

import java.util.List;
import java.util.stream.Collectors;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @date ${date}
 * @since ${cfg.version}
 */
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "vin.admin.controller", name = "enabled", havingValue = "true")
@Log(function = "${table.comment!}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} business;

    /**
     * 根据条件查询
     *
     * @param dto 条件实体
     * @return api响应
     */
    @RequestMapping("list")
    @Log(action = "集合查询")
    public ApiResponse list(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        List<${entity}> list = business.list(getQuery(entity));
        List<${table.voName}> collect = BeanUtil.copyPropertiesList(list, ${table.voName}.class);
        return ApiResponse.success(collect);
    }

    /**
    * 分页查询
    *
    * @param dto 分页查询DTO
    * @return
    */
    @RequestMapping("page")
    @Log(action = "分页查询")
    public ApiResponse page(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        Page<${entity}> page = new Page<>();
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getSize());
        ${entity} condition = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        Page<${entity}> result = business.page(page, Wrappers.query(condition));
        Page<${table.voName}> response = new Page<>();
        BeanUtil.copyProperties(result, response, CopyOptions.create().setIgnoreProperties("records", "optimizeCountSql"));
        List<${table.voName}> collect = BeanUtil.copyPropertiesList(result.getRecords(), ${table.voName}.class);
        response.setRecords(collect);
        return ApiResponse.success(response);
    }

    /**
    * 根据主键获取
    *
    * @param dto 主键实体类
    * @return
    */
    @RequestMapping("get")
    @Log(action = "主键查询")
    public ApiResponse get(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = business.getById(dto.getId());
        ${table.voName} vo = BeanUtil.copyPropertiesClazz(entity, ${table.voName}.class);
        return ApiResponse.success(vo);
    }

    /**
    * 创建接口
    *
    * @param dto ${table.comment!}信息
    * @return
    */
    @RequestMapping("create")
    @Log(action = "创建")
    public ApiResponse create(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        BeanValidator.validate(dto, CreateGroup.class);
        boolean save = business.save(entity);
        return ApiResponse.success(save);
    }

    /**
    * 根据主键更新
    *
    * @param dto ${table.comment!}数据
    * @return
    */
    @RequestMapping("update")
    @Log(action = "根据主键更新")
    public ApiResponse update(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        BeanValidator.validate(entity, UpdateGroup.class);
        boolean save = business.updateById(entity);
        return ApiResponse.success(save);
    }

    /**
    * 根据主键删除
    *
    * @param dto 主键
    * @return
    */
    @RequestMapping("delete")
    @Log(action = "根据主键删除")
    public ApiResponse delete(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        BeanValidator.validate(entity, DeleteGroup.class);
        boolean save = business.removeById(entity.getId());
        return ApiResponse.success(save);
    }

    /**
     * 根据主键批量删除
     *
     * @param dto 主键
     * @return
     */
    @RequestMapping("remove")
    @Log(action = "批量删除")
    public ApiResponse remove(MultiIdDTO dto) {
        long start = System.currentTimeMillis();
        List<${table.idPropertyType}> split = dto.split(${table.idPropertyType}.class);
        business.removeByIds(split);
        return ApiResponse.success(true);
    }
    <#if table.hasName>

    /**
    * 获取简单列表，可以用于下拉框选择等
    *
    * @param dto 查询条件
    * @return
    */
    @RequestMapping("simple")
    @Log(action = "简单列表查询")
    public ApiResponse simple(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        List<${entity}> list = business.list(getQuery(entity));
        List<NameVO> collect = list.stream().map(t -> {
            NameVO<${table.idPropertyType}> vo = new NameVO();
            BeanUtil.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        return ApiResponse.success(collect);
    }
    </#if>

    <#if table.hasParent>

    /**
     * 获取树形结构数据
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping("tree")
    @Log(action = "树形结构查询")
    public ApiResponse tree(${table.dtoName} dto) {
        long start = System.currentTimeMillis();
        ${entity} entity = BeanUtil.copyPropertiesClazz(dto, ${entity}.class);
        List<${entity}> list = business.list(getQuery(entity));
        List<${table.voName}> collect = BeanUtil.copyPropertiesList(list, ${table.voName}.class);
        List<${table.voName}> tree = Parent.tree(collect,${table.idPropertyType}.class);
        return ApiResponse.success(tree);
    }
    </#if>
    <#if table.hasUseFlag>

    /**
    * 批量启用
    *
    * @param dto 启用id
    * @return
    */
    @RequestMapping("enable")
    @Log(action = "批量启用")
    public ApiResponse enable(MultiIdDTO dto) {
        long start = System.currentTimeMillis();
        List<${table.idPropertyType}> split = dto.split(${table.idPropertyType}.class);
        List<${entity}> collect = split.stream().map(t -> {
            ${entity} entity = new ${entity}();
            entity.setUseFlag(true);
            entity.setId(t);
            return entity;
            }
        ).collect(Collectors.toList());
        business.updateBatchById(collect);
        return ApiResponse.success(true);
    }

    /**
    * 批量禁用
    *
    * @param dto 禁用id
    * @return
    */
    @RequestMapping("disable")
    @Log(action = "批量禁用")
    public ApiResponse disable(MultiIdDTO dto) {
        long start = System.currentTimeMillis();
        List<${table.idPropertyType}> split = dto.split(${table.idPropertyType}.class);
        List<${entity}> collect = split.stream().map(t -> {
            ${entity} entity = new ${entity}();
            entity.setUseFlag(false);
            entity.setId(t);
            return entity;
            }
        ).collect(Collectors.toList());
        business.updateBatchById(collect);
        return ApiResponse.success(true);
    }
    </#if>

    /**
    * 获取默认的查询条件
    *
    * @param entity 查询条件
    * @return
    */
    private Wrapper<${entity}> getQuery(${entity} entity) {
        return Wrappers.lambdaQuery(entity)<#if table.hasSeq>.orderByAsc(${entity}::getSeq)</#if>;
    }
}
</#if>
