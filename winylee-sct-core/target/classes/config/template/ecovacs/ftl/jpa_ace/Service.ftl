package ${nameSpace};

import com.ecovacs.common.pojo.Result;
import ${domain}.dto.${moduleName}.${className}DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* generate by winylee CodeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
public interface ${className}Service {

    /**
    * 查询
    * @param pageable
    * @param dto
    * @return
    */
    Page<${className}DTO> findPage(Pageable pageable, ${className}DTO dto);


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
    void save(${className}DTO dto);


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
