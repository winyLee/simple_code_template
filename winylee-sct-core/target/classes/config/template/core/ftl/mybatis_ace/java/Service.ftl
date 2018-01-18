package ${nameSpace};

import ${domain}.dto.${moduleName}.${className}DTO;
import ${domain}.model.${moduleName}.${className};
import com.winylee.framework.core.page.Page;
import java.util.List;
/**
* generate by  WinyleeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
public interface ${className}Service {

    /**
    * 查询
    * @return
    */
    public List<${className}DTO> findPage(Page<${className}DTO> page, ${className}DTO condition);

    /**
    * 按id获取�?
    * @param id
    * @return
    */
    ${className}DTO get(Long id);

    /**
    * 新增
    * @param dto
    * @return
    */
    Long save(${className}DTO dto);

    /**
    * 修改
    * @param dto
    * @return
    */
    void edit(${className}DTO dto);

    /**
    * 删除
    * @param id
    * @return
    */
    void delete(Long id);

}