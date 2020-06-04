package com.vf.mybatis.methods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.vf.mybatis.constant.MethodConstant;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * 批量插入方法
 *
 * @author levin
 * @since 1.0.0
 */
public class InsertInBatch extends AbstractMethod {
    /**
     * 注入自定义 MappedStatement
     *
     * @param mapperClass mapper 接口
     * @param modelClass  mapper 泛型
     * @param tableInfo   数据库表反射信息
     * @return MappedStatement
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
        String columnScript = SqlScriptUtils.convertTrim(this.getAllInsertSqlColumn(tableInfo),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(this.getAllInsertSqlProperty(tableInfo, MethodConstant.KEY_FOREACH_PREFIX),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String foreach = SqlScriptUtils.convertForeach(valuesScript, MethodConstant.KEY_FOREACH_COLLECTION, null, MethodConstant.KEY_FOREACH_ITEM, COMMA);
        String keyProperty = null;
        String keyColumn = null;
        if (StringUtils.isNotBlank(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(getMethod(sqlMethod), tableInfo, builderAssistant);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }

        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, foreach);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, MethodConstant.KEY_INSERT_IN_BATCH, sqlSource, keyGenerator, keyProperty, keyColumn);
    }

    /**
     * 获取所有插入字段
     *
     * @param tableInfo 表信息
     * @return 字段信息
     */
    private String getAllInsertSqlColumn(final TableInfo tableInfo) {
        return tableInfo.getKeyInsertSqlColumn(true) + tableInfo.getFieldList().stream().map(TableFieldInfo::getInsertSqlColumn)
                .filter(Objects::nonNull).collect(joining(NEWLINE));
    }

    /**
     * 获取所有 insert 时候插入值 sql 脚本片段
     *
     * @param prefix 前缀
     * @return sql 脚本片段
     */
    private String getAllInsertSqlProperty(final TableInfo tableInfo, final String prefix) {
        final String newPrefix = prefix == null ? EMPTY : prefix;
        return tableInfo.getKeyInsertSqlProperty(newPrefix, true) + tableInfo.getFieldList().stream()
                .map(i -> i.getInsertSqlProperty(newPrefix)).filter(Objects::nonNull).collect(joining(NEWLINE));
    }
}
