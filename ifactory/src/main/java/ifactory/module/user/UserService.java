package ifactory.module.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User queryUserByCondition(UserExample condition){
		List<User> userList = userMapper.selectByExample(condition);
		if(userList!=null&&userList.size()>0){
			return userList.get(0);
		}else{
			return null;
		}
	}

	public boolean checkUserInfo(User user){
		String loginEmail = user.getUsername();
		String passwd = user.getPassword();
		if(StringUtils.isEmpty(loginEmail) || StringUtils.isEmpty(passwd)){
			return false;
		}else{
			return true;
		}
	}
}
