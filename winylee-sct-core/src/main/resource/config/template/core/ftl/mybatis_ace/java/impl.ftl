package ${nameSpace};

import com.winylee.framework.core.exception.WinyleeValidationException;
import com.winylee.framework.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import ${domain}.dao.${moduleName}.${className}Dao;
import ${domain}.service.${moduleName}.${className}Service;
import ${domain}.dto.${moduleName}.${className}DTO;
import ${domain}.model.${moduleName}.${className};

/**
* generate by  WinyleeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
@Service
public class ${className}ServiceImpl  implements ${className}Service{

    @Autowired
    private ${className}Dao dao;
    //用来支持更细粒度操作
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<${className}DTO> findPage(Page<${className}DTO> page, ${className}DTO condition) {
        Map<String, Object> map = new HashMap<>();
        //分页必须的参数
        map.put("page", page);
        map.put("condition", condition);
        List<${className}> list =  dao.findPage(map);
        if(CollectionUtils.isNotEmpty(list)){
            List<${className}DTO> dtoList= new ArrayList<>();
            for(${className} entity :  list){
                dtoList.add(entity.toDTO());
            }
            return dtoList;
        }
        return null;
    }

    @Override
    public ${className}DTO get(Long id) {
        if (id == null) {
            throw new WinyleeValidationException("id不能为空");
        }
        ${className} entity = dao.getById(id);
        if (entity == null) {
            return null;
        }
        return entity.toDTO();
    }

    @Override
    public Long save(${className}DTO dto) {
        if (dto == null) {
            throw new WinyleeValidationException("信息不存在");
        }
        ${className} entity = new ${className}();
        return dao.save(entity.fromDTO(dto));
    }

    @Override
    public void edit(${className}DTO dto) {
        if (dto == null) {
            throw new WinyleeValidationException("信息不存在");
        }
        ${className} entity = new ${className}();
        dao.updateById(entity.fromDTO(dto));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new WinyleeValidationException("id不能为空");
        }
        dao.delById(id);
    }
}