package ${nameSpace};

/**
 * 实体类${classDes}
 * generate by WinyleeTemplate
 *  @author   ${authorName} ${createTimeStr}
 */
public class ${className}DTO  {

    private Long id;
<#if fields?exists>

    <#list  fields as field >
    /**
     * ${field.fieldName?if_exists}
     * ${field.fieldComment?if_exists }
     */
    private ${field.fieldType} ${field.fieldId};
    </#list>
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    <#list  fields as field >
    /**
     *   ${field.fieldName?if_exists}
     *   ${field.fieldComment?if_exists }
     */
    public ${field.fieldType} get${field.fieldUId}() {
        return this.${field.fieldId};
    }

    /**
     *   ${field.fieldName?if_exists}
     *   ${field.fieldComment?if_exists }
     */
    public void set${field.fieldUId}(${field.fieldType} ${field.fieldId}) {
        this.${field.fieldId} = ${field.fieldId};
    }
    </#list>
</#if>
}