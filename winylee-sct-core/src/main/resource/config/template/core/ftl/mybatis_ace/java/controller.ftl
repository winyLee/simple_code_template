package ${nameSpace};

import com.winylee.framework.core.page.Page;
import com.winylee.framework.core.model.Result;
import com.winylee.framework.core.model.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ${domain}.service.${moduleName}.${className}Service;
import ${domain}.dto.${moduleName}.${className}DTO;

/**
* generate by WinyleeTemplate
*  @author   ${authorName} ${createTimeStr}
*/
@Controller
@RequestMapping("/${mappingUrl}")
public class ${className}Controller  {

    @Autowired
    private ${className}Service service;

    @RequestMapping("/listPage")
    public String findPage(Page<${className}DTO> page, Model model, ${className}DTO conditions) {
        page.setList(service.findPage(page, conditions));
        model.addAttribute("page",page);
        model.addAttribute("condition", conditions);
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
        return new ResultBean();
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(${className}DTO dto) {
        service.edit(dto);
        return new ResultBean();
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result del(Long id) {
        service.delete(id);
        return new ResultBean();
    }
}