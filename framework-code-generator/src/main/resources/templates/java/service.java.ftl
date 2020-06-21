package ${package.Service};

import ${package.Entity}.${entity};
import ${package.DTO}.${table.dtoName};
import ${package.PO}.${table.poName};
import com.vf.mvc.service.CurdService;
/**
* <p>
 * ${table.comment!} 服务类
 * </p>
*
* @author ${author}
* @date ${date}
* @since ${cfg.version}
*/
public interface ${table.serviceName} extends CurdService<${entity}, ${table.dtoName}, ${table.poName}> {

}
