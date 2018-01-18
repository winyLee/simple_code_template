package ${nameSpace};

import ${domain}.model.${moduleName}.${className};
import java.util.List;
import java.util.Map;
/**
* generate by WinyleeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
public interface ${className}Dao  {

    public Long save(${className} entity);

    public void delById(Long id);

    public List<${className}> findPage(Map<String, Object> map);

    public ${className} getById(Long id);

    public void updateById(${className} entity);

}