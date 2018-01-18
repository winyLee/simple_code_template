package ${nameSpace};

import com.ecovacs.core.dao.GenericRepository;
import ${domain}.model.${moduleName}.${className};
import org.springframework.stereotype.Repository;

@Repository
public interface ${className}Dao extends GenericRepository<${className}, Long> {


}
