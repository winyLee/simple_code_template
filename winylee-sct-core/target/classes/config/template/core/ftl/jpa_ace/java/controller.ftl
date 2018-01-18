package ${nameSpace};

import com.ecovacs.common.EcovacsPage;
import com.ecovacs.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ${domain}.service.${moduleName}.${className}Service;
import ${domain}.dto.${moduleName}.${className}DTO;

/**
* generate by winylee CodeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
@Controller
@RequestMapping("/${mappingUrl}")
public class ${className}Controller  {

    @Autowired
    private ${className}Service service;

    @RequestMapping("/listPage")
    public Page<${className}DTO> findPage(Pageable pageable, Model model, ${className}DTO dto) {
        myPage.setDirection(Sort.Direction.DESC);
        myPage.setProperties("modifyTime");
        Pageable pageable = myPage.getPageRequest();
        model.addAttribute("page", service.findPage(pageable, conditions));
        model.addAttribute("conditions", conditions);
        return "${mappingUrl}/list";
    }

    @RequestMapping("/newPage")
    public String newPage() {
        return "${mappingUrl}/newPage";
    }

    @RequestMapping("/detailPage")
    public String detailPage(Model model, Long id) {
        ${className}DTO dto = service.get(id);
        model.addAttribute("data", dto);
        return "${mappingUrl}/detailPage";
    }

    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        ${className}DTO dto = service.get(id);
        model.addAttribute("data", dto);
        return "${mappingUrl}/editPage";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(${className}DTO dto) {
        service.save(dto);
        return new Result(true);
    }

    @RequestMapping("/edit")
    @ResponseBody
        public Result edit(${className}DTO dto) {
        service.edit(dto);
        return new Result(true);
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result del(Long id) {
        service.delete(id);
        return new Result(true);
    }
}
