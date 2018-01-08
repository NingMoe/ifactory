package ifactory.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ifactory.module.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    
	@Autowired
	public UserService userService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String login(@RequestBody User user) {
		if(!userService.checkUserInfo(user)){
			return "用户名/密码不能为空";
		}
		UserExample example = new UserExample();
		example.or().andUsernameEqualTo(user.getUsername());
		example.or().andFactoryCodeEqualTo(user.getFactoryCode());
		User userIn = userService.queryUserByCondition(example);
		if(user.getPassword().equals(userIn.getFactoryCode())){
			return "";
		}else{
			return "密码错误";
		}
	}
	
	
    
}
