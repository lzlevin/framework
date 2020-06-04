package com.vf.task.file.db;

import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.vf.task.file.AbstractFileTask;
import com.vf.utils.lang.Assert;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.vfs2.FileObject;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件到数据库
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
@Getter
@Setter
public abstract class File2DbTask extends AbstractFileTask {

    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * 要插入数据的表
     */
    private String table;
    /**
     * 默认分页插入的大小
     */
    private int pageSize = 1000;
    /**
     * 文件处理器
     */
    private FileReadLineHandler fileReadLineHandler;
    /**
     * 表元数据
     */
    @Setter(AccessLevel.NONE)
    protected Table tableMeta;
    /**
     * 表的列
     */
    @Setter(AccessLevel.NONE)
    private List<Column> columns;
    /**
     * 插入的数据
     */
    @Setter(AccessLevel.NONE)
    protected String insertSql;

    @Override
    public void init() {
        super.init();
        Assert.isBlank(table, "the table argument must be not null");
        Assert.isNull(dataSource, "the datasource argument must be not null");
        Assert.isNull(fileReadLineHandler, "the fileReadLineHandler argument must be not null");
        this.tableMeta = MetaUtil.getTableMeta(dataSource, table);
        this.columns = new ArrayList<>(this.tableMeta.getColumns());
    }

    @Override
    public void handleFiles(List<FileObject> fileObjects) throws IOException {
        for (FileObject fileObject : fileObjects) {
            //1.将文件转化成数据集合
            //2.将数据进行插入
            int size = 0;
            List<Map<String, Object>> lines = new ArrayList<>();
            while (fileReadLineHandler.hasNext()) {
                lines.add(fileReadLineHandler.next());
                size++;
                if (size == pageSize) {
                    batchInsert(lines);
                    lines.clear();
                }
            }
            batchInsert(lines);
        }
    }

    /**
     * 批量插入
     *
     * @param lines 插入的数据
     */
    private void batchInsert(List<Map<String, Object>> lines) {
        //get sql
        //get param
        String sql = getSql();
        Object[][] params = null;
        try {
            new QueryRunner(dataSource).batch(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将list参数转换为列名相对应的顺序
     *
     * @param lines 数据行
     * @return object[][]
     */
    private Object[][] convert(List<Map<String, Object>> lines) {
        int line = lines.size();
        int column = this.columns.size();
        Object[][] objects = new Object[line][column];
        for (int i = 0; i < line; i++) {
            Map<String, Object> row = lines.get(i);
            for (int j = 0; j < column; j++) {
                Object value = row.get(columns.get(j).getName());
                objects[i][j] = value;
            }
        }
        return objects;
    }

    /**
     * 获取sql
     *
     * @return
     */
    private String getSql() {
        if (this.insertSql == null) {
            String columns = this.columns.stream().map(Column::getName).collect(Collectors.joining(","));
            String values = this.columns.stream().map(t -> "?").collect(Collectors.joining(","));
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(this.tableMeta.getTableName())
                    .append("(").append(columns).append(")")
                    .append("VALUES")
                    .append("(").append(values).append(")");
        }
        return insertSql;
    }
}
