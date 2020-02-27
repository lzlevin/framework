package com.vin.framework.generator.java;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.vin.framework.core.dto.BaseDTO;
import com.vin.framework.mybatis.entity.LongKeyBaseEntity;
import com.vin.framework.mybatis.mapper.BaseMapper;
import com.vin.framework.mybatis.service.IService;
import com.vin.framework.mybatis.service.impl.ServiceImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 代码生成器
 *
 * @author levin
 * @since 1.0.0
 */
public class Generator {

    public static void main(String[] args) {

        AutoGenerator generator = new AutoGenerator();

        generator.setGlobalConfig(getGlobalConfig());

        generator.setDataSource(getDataSourceConfig());

        generator.setPackageInfo(getPackageConfig());

        generator.setStrategy(getStrategyConfig());

        generator.setCfg(getInjectionConfig());

        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        generator.setTemplate(getTemplateConfig());

        generator.execute();
    }

    /**
     * 模板路径配置
     *
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig config = new TemplateConfig();
        config.setController("templates/java/controller.java");
        config.setEntity("templates/java/entity.java");
        config.setMapper("templates/java/mapper.java");
        config.setService("templates/java/service.java");
        config.setServiceImpl("templates/java/serviceImpl.java");
        config.setXml("templates/java/mapper.xml");
        config.setDto("templates/java/dto.java");
        config.setVo("templates/java/vo.java");
        return config;
    }

    /**
     * 自定义注入属性
     *
     * @return
     */
    private static InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("version", PROPERTIES.getProperty("version"));
                this.setMap(map);
            }
        };
    }

    /**
     * 获取数据库配置
     *
     * @return
     */
    private static StrategyConfig getStrategyConfig() {
        StrategyConfig config = new StrategyConfig();
        config.setSkipView(true);
        config.setNaming(NamingStrategy.underline_to_camel);
        config.setSuperEntityClass(LongKeyBaseEntity.class);
        config.setSuperMapperClass(BaseMapper.class.getName());
        config.setSuperServiceClass(IService.class.getName());
        config.setSuperServiceImplClass(ServiceImpl.class.getName());
        config.setSuperDTOClass(BaseDTO.class.getName());
        config.setEntityBuilderModel(true);
        config.setEntityLombokModel(true);
        config.setEntitySerialVersionUID(false);
        String tables = PROPERTIES.getProperty("tables");
        if (StringUtils.isNotBlank(tables)) {
            String[] split = tables.split(",");
            config.setInclude(split);
        }
        return config;
    }

    /**
     * 全局配置
     *
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSwagger2(true);
        globalConfig.setAuthor(PROPERTIES.getProperty("author"));
        globalConfig.setOpen(true);
        globalConfig.setEntityName("%sEntity");
        globalConfig.setServiceName("%sBusiness");
        globalConfig.setServiceImplName("%sBusinessImpl");
        globalConfig.setOutputDir(PROPERTIES.getProperty("dir"));
        return globalConfig;
    }

    /**
     * 包配置
     *
     * @return
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PROPERTIES.getProperty("package"));
        return packageConfig;
    }

    /**
     * 获取数据源配置
     *
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setDriverName(PROPERTIES.getProperty("jdbc.driverClassName"));
        config.setUrl(PROPERTIES.getProperty("jdbc.url"));
        config.setUsername(PROPERTIES.getProperty("jdbc.username"));
        config.setPassword(PROPERTIES.getProperty("jdbc.password"));
        return config;
    }

    public static Properties PROPERTIES = null;

    static {
        ClassPathResource resource = new ClassPathResource("config.properties");
        try {
            PROPERTIES = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件失败");
        }
    }


}
