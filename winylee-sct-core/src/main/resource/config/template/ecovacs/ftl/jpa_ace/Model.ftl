package ${nameSpace};

import com.ecovacs.common.model.BaseEntity;
import ${dtoNameSpace}.${className}DTO;
import javax.persistence.*;
/**
 * 实体类${classDes}
* generate by winylee CodeTemplate
*  @author   ${authorName} ${createTimeStr}
 */
@Entity
@Table(name="${tblId}")
public class ${className} extends BaseEntity {
<#if fields?exists>
    <#list  fields as field >
    /**
    * ${field.fieldName}
    * ${field.fieldComment?if_exists }
    */
    private ${field.fieldType} ${field.fieldId};
    </#list>

    <#list  fields as field >
    /**
    *   ${field.fieldName}
    *   ${field.fieldComment?if_exists }
    */
    @Column(name = "${field.columnName}")
    public ${field.fieldType} get${field.fieldUId}() {
         return this.${field.fieldId};
    }

    /**
    *   ${field.fieldName}
    *   ${field.fieldComment?if_exists }
    */
    public void set${field.fieldUId}(${field.fieldType} ${field.fieldId}) {
        this.${field.fieldId} = ${field.fieldId};
    }
    </#list>

    @Override
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
