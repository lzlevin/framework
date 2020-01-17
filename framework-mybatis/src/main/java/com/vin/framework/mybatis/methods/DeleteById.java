package com.vin.framework.mybatis.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.vin.framework.core.constant.BaseEntityConstant;
import com.vin.framework.mybatis.constant.MethodConstant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Optional;

/**
 * 逻辑删除方法（注入更新时间和更新人）
 *
 * @author levin
 * @since 1.0.0
 */
public class DeleteById extends com.baomidou.mybatisplus.core.injector.methods.DeleteById {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        if (!tableInfo.isLogicDelete()) {
            return super.injectMappedStatement(mapperClass, modelClass, tableInfo);
        }
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BY_ID;
        String sql = null;
        Optional<TableFieldInfo> modifyByField = tableInfo.getFieldList().stream().filter(t -> t.isWithUpdateFill()
                && (BaseEntityConstant.KEY_MODIFY_BY.equals(t.getProperty()))).findFirst();
        Optional<TableFieldInfo> modifyTimeField = tableInfo.getFieldList().stream().filter(t -> t.isWithUpdateFill()
                && (BaseEntityConstant.KEY_MODIFY_TIME.equals(t.getProperty()))).findFirst();
        StringBuilder setSql = new StringBuilder(sqlLogicSet(tableInfo));
        if (modifyByField.isPresent()) {
            setSql.append(COMMA).append(modifyByField.get().getSqlSet(null));
        }
        if (modifyTimeField.isPresent()) {
            setSql.append(modifyTimeField.get().getSqlSet(null));
        }
        setSql.setCharAt(setSql.length() - 1, '\n');
        sql = String.format(MethodConstant.LOGIC_DELETE_BY_ID, tableInfo.getTableName(), setSql,
                tableInfo.getKeyColumn(), tableInfo.getKeyProperty(),
                tableInfo.getLogicDeleteSql(true, true));
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
        return addUpdateMappedStatement(mapperClass, modelClass, getMethod(sqlMethod), sqlSource);

    }
}
