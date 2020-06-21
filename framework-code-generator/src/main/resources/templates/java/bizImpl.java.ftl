package ${package.BizImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Biz}.${table.bizName};
import com.vf.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} Biz实现类
 * </p>
 *
 * @author ${author}
 * @date ${date}
 * @since ${cfg.version}
 */
@Service
public class ${table.bizImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.bizName} {

}
