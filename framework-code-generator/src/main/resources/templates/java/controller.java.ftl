package ${package.Controller};

import ${package.Service}.${table.serviceName};
import ${package.DTO}.${table.dtoName};
import ${package.Entity}.${entity};
import ${package.VO}.${table.voName};
import ${package.PO}.${table.poName};
import com.vf.mvc.response.ApiResponse;
<#if table.hasParent>
import com.vf.common.entity.Parent;
</#if>
<#if table.hasName>
import com.vf.core.vo.NameVO;
</#if>
import com.vf.log.annotation.Log;
import com.vf.mvc.controller.CurdController;
import com.vf.mvc.service.CurdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

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
@Log(function = "${table.comment!}")
public class ${table.controllerName} implements CurdController<${table.voName}, ${table.dtoName}, ${entity}, ${table.poName}> {

    @Autowired
    private ${table.serviceName} service;

    /**
    * curd服务
    *
    * @return curd服务
    */
    @Override
    public <S extends CurdService<${entity}, ${table.dtoName}, ${table.poName}>> S getService() {
        return (S) service;
    }
}
