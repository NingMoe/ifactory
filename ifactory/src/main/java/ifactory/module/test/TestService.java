package ifactory.module.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageHelperUtil;
import ifactory.module.common.PageModel;

@Service
public class TestService {

	@Autowired
	private TestMapper testMapper;
	
	public void add(TestModel a){
		testMapper.insert(a);
	}
	
	public void update(TestModel a){
		testMapper.update(a);
	}
	
	public PageInfo<TestModel> selects(PageModel<TestModel> con){
		if (con.getPageNum() == null || con.getPageNum() <= 0) 
        	con.setPageNum(1);
        if (con.getPageSize() == null || con.getPageSize() <= 0) 
        	con.setPageSize(10);
        
        
		PageHelper.startPage(con.getPageNum(), con.getPageSize(), PageHelperUtil.handleOrderBy(con.getSortColumn(), con.getSortType()));
		
		List<TestModel> list = testMapper.selects(null);	
		return new PageInfo<TestModel>(list);		
	}
	
	public void delete(Integer id){
		TestModel a = new TestModel(id, "aaaa");
		testMapper.delete(a);
	}
}
