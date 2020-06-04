package ${package.VO};

<#if superVOClassPackage??>
import ${superVOClassPackage};
</#if>
<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if table.hasParent>
import com.vf.core.common.Parent;
</#if>
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>
import java.io.Serializable;
<#if table.hasParent>
import java.util.List;
</#if>

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @date ${date}
 * @since ${cfg.version}
 */
<#if entityLombokModel>
@Data
    <#if superVOClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
@Accessors(chain = true)
</#if>
<#if swagger2>
@ApiModel(value = "${entity}VO", description = "${table.comment!}")
</#if>
<#if superVOClass??>
public class ${table.voName} extends ${superVOClass}<${table.idPropertyType}> implements Serializable<#if table.hasParent>, Parent<${table.idPropertyType}></#if>{
<#else>
public class ${table.voName} implements Serializable {
</#if>

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
        <#if swagger2>
    @ApiModelProperty(value = "${field.comment}")
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

    <#if entityBuilderModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
        return this;
        </#if>
    }
    </#list>
</#if>
<#if table.hasParent>
    /**
     * 孩子节点
     */
    @ApiModelProperty(value = "孩子节点")
    private List<${table.voName}> children;

    @Override
    public void setChildren(List<? extends Parent<${table.idPropertyType}>> list) {
        this.children = (List<${table.voName}>) list;
    }
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
