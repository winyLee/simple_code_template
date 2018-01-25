package ${nameSpace};

import com.ecovacs.core.dao.SimpleExpression;
import com.ecovacs.core.exception.EcovacsValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${domain}.dao.${moduleName}.${className}Dao;
import ${domain}.service.${moduleName}.${className}Service;
import ${domain}.dto.${moduleName}.${className}DTO;
import ${domain}.model.${moduleName}.${className};

/**
* generate by winylee CodeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
@Service
public class ${className}ServiceImpl  implements ${className}Service{

    @Autowired
    private ${className}Dao dao;

    @Override
    public Page<${className}DTO> findPage(Pageable pageable, ${className}DTO dto) {
        List<SimpleExpression> expressionList = new ArrayList<>();
        return convert(dao.findByPage(expressionList, pageable),pageable);
    }

    @Override
    public ${className}DTO get(Long id) {
        if(id == null){
            throw new EcovacsValidateException("id 不能为空");
        }
        ${className}  entity = dao.findOne(id);
        return entity==null?null:entity.toDTO();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(${className}DTO dto) {
        ${className}  entity = new ${className}();
        entity.fromDTO(dto);
        dao.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(${className}DTO dto) {
        ${className} entity = dao.findOne(dto.getId());
        if(entity == null){
            throw new EcovacsValidateException("修改失败，该信息已被删除");
        }
        entity.fromDTO(dto);
        dao.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if(id == null){
            throw new EcovacsValidateException("id 不能为空");
        }
        dao.delete(id);
    }


    private Page<${className}DTO> convert(Page<${className}> page, Pageable pageable) {
        List<${className}DTO> dtoList = convert(page.getContent());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    private List<${className}DTO> convert(List<${className}> list) {
        List<${className}DTO> dtoList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (${className} entity : list) {
                ${className}DTO dto = entity.toDTO();
                dtoList.add(dto);
            }
        }
         return dtoList;
    }
}
