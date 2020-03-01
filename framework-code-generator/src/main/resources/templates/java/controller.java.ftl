package ${package.Controller};

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Service}.${table.serviceName};
import ${package.DTO}.${table.dtoName};
import ${package.Entity}.${entity};
import ${package.VO}.${table.voName};
import com.vin.framework.core.api.ApiResponse;
import com.vin.framework.core.validate.CreateGroup;
import com.vin.framework.core.validate.DeleteGroup;
import com.vin.framework.core.validate.UpdateGroup;
<#if table.hasParent>
import com.vin.framework.core.common.Parent;
</#if>
<#if table.hasName>
import com.vin.framework.core.vo.NameVO;
</#if>
import com.vin.framework.validate.validator.BeanValidator;
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
    public ApiResponse list(${table.dtoName} dto) {
        log.info("[${table.comment!}查询开始]，接口名[list]");
        long start = System.currentTimeMillis();
        ${entity} entity = new ${entity}();
        BeanUtil.copyProperties(dto, entity);
        List<${entity}> list = business.list(Wrappers.query(entity));
        List<${table.voName}> collect = list.stream().map(t -> {
        ${table.voName} vo = new ${table.voName}();
            BeanUtil.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        log.info("[${table.comment!}查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(collect);
    }

    /**
    * 分页查询
    *
    * @param dto 分页查询DTO
    * @return
    */
    @RequestMapping("page")
    public ApiResponse page(${table.dtoName} dto) {
        log.info("[${table.comment!}分页查询开始]，接口名[page]");
        long start = System.currentTimeMillis();
        Page<${entity}> page = new Page<>();
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getSize());
        ${entity} condition = new ${entity}();
        BeanUtil.copyProperties(dto, condition);
        Page<${entity}> result = business.page(page, Wrappers.query(condition));
        Page<${table.voName}> response = new Page<>();
        BeanUtil.copyProperties(result, response, CopyOptions.create().setIgnoreProperties("records", "optimizeCountSql"));
        List<${table.voName}> collect = result.getRecords().stream().map(t -> {
            ${table.voName} vo = new ${table.voName}();
            BeanUtil.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        response.setRecords(collect);
        log.info("[${table.comment!}分页查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(response);
    }

    /**
    * 根据主键获取
    *
    * @param dto 主键实体类
    * @return
    */
    @RequestMapping("get")
    public ApiResponse get(${table.dtoName} dto) {
        log.info("[${table.comment!}根据ID查询开始]，接口名[get]");
        long start = System.currentTimeMillis();
        ${table.voName} vo = new ${table.voName}();
        ${entity} entity = business.getById(dto.getId());
        BeanUtil.copyProperties(entity, vo);
        log.info("[${table.comment!}根据ID查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(vo);
    }

    /**
    * 创建接口
    *
    * @param dto ${table.comment!}信息
    * @return
    */
    @RequestMapping("create")
    public ApiResponse create(${table.dtoName} dto) {
        log.info("[${table.comment!}创建开始]，接口名[create]");
        long start = System.currentTimeMillis();
        ${entity} entity = new ${entity}();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(dto, CreateGroup.class);
        boolean save = business.save(entity);
        log.info("[${table.comment!}创建结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(save);
    }

    /**
    * 根据主键更新
    *
    * @param dto ${table.comment!}数据
    * @return
    */
    @RequestMapping("update")
    public ApiResponse update(${table.dtoName} dto) {
        log.info("[${table.comment!}更新开始]，接口名[update]");
        long start = System.currentTimeMillis();
        ${entity} entity = new ${entity}();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(entity, UpdateGroup.class);
        boolean save = business.updateById(entity);
        log.info("[${table.comment!}更新结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(save);
    }

    /**
    * 根据主键删除
    *
    * @param dto 主键
    * @return
    */
    @RequestMapping("delete")
    public ApiResponse delete(${table.dtoName} dto) {
        log.info("[${table.comment!}根据ID删除开始]，接口名[delete]");
        long start = System.currentTimeMillis();
        ${entity} entity = new ${entity}();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(entity, DeleteGroup.class);
        boolean save = business.removeById(entity.getId());
        log.info("[${table.comment!}根据ID删除结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(save);
    }
    <#if table.hasName>
    /**
    * 获取简单列表，可以用于下拉框选择等
    *
    * @param dto 查询条件
    * @return
    */
    @RequestMapping("simple")
    public ApiResponse simple(${table.dtoName} dto) {
        log.info("[${table.comment!}简单查询开始]，接口名[simple]");
        long start = System.currentTimeMillis();
        ${entity} entity = new ${entity}();
        BeanUtil.copyProperties(dto, entity);
        List<${entity}> list = business.list(Wrappers.query(entity));
        List<NameVO> collect = list.stream().map(t -> {
            NameVO<${table.idPropertyType}> vo = new NameVO();
            BeanUtil.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        log.info("[${table.comment!}简单查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
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
    public ApiResponse tree(OrgDTO dto) {
        log.info("[${table.comment!}树形查询开始]，接口名[tree]");
        long start = System.currentTimeMillis();
        ${entity} entity = new ${entity}();
        BeanUtil.copyProperties(dto, entity);
        List<${entity}> list = business.list(Wrappers.query(entity));
        List<${table.voName}> collect = list.stream().map(t -> {
            ${table.voName} vo = new ${table.voName}();
            BeanUtil.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        List<${table.voName}> tree = Parent.treeString(collect);
        log.info("[${table.comment!}树形查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(tree);
    }
    </#if>
}
</#if>
