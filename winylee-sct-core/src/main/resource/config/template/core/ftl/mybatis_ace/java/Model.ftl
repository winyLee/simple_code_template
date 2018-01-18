package ${nameSpace};

import com.winylee.framework.core.entity.MybatisBaseEntity;
import ${dtoNameSpace}.${className}DTO;
import javax.persistence.*;
/**
 * 实体类${classDes}
 * generate by WinyleeTemplate
 *  @author   ${authorName} ${createTimeStr}
 */
public class ${className} extends MybatisBaseEntity {
<#if fields?exists>
    <#list  fields as field >
    /**
    * ${field.fieldName?if_exists}
    * ${field.fieldComment?if_exists }
    */
    private ${field.fieldType} ${field.fieldId};
    </#list>

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

    public ${className}DTO toDTO() {
        ${className}DTO  dto = new ${className}DTO ();
        <#list  fields as field >
        dto.set${field.fieldUId}(this.${field.fieldId});
        </#list>
        dto.setId(this.getId());
        return dto;
    }

    public ${className} fromDTO(${className}DTO  dto) {
        <#list  fields as field >
        this.set${field.fieldUId}(dto.get${field.fieldUId}());
        </#list>
        this.setId(dto.getId());
        return this;
    }
</#if>

}