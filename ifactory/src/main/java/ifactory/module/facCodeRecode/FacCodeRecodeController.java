package ifactory.module.facCodeRecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ifactory.module.common.PageModel;

@Controller
@RequestMapping("/FacCodeRecode")
public class FacCodeRecodeController {
	
	@Autowired
	private FacCodeRecodeService facCodeRecodeService;
	
	@GetMapping(path = "/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody PageModel<FacCodeRecodeExample> selects() {
		
		PageModel<FacCodeRecodeExample> result = new PageModel<FacCodeRecodeExample>();
		
		return result;
		
	}

}
