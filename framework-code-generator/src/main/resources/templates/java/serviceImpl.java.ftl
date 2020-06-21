package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${package.Biz}.${table.bizName};
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @date ${date}
 * @since ${cfg.version}
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    private ${table.bizName} biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<${entity}>> D getDao() {
        return (D) biz;
    }
}
