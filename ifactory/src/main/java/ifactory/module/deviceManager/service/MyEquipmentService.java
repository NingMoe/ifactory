package ifactory.module.deviceManager.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.MyEquipmentExample;
import ifactory.module.deviceManager.entity.StateStatisticsExample;
import ifactory.module.deviceManager.mapper.MyEquipmentMapper;

@Service
public class MyEquipmentService {
	
	@Autowired
	private MyEquipmentMapper myEquipmentMapper;
	
	public MyEquipmentService(MyEquipmentMapper myEquipmentMapper){
		this.myEquipmentMapper = myEquipmentMapper;
	}
	
	public void add(MyEquipmentExample myEquipmentExample){
		if (StringUtils.isEmpty(""+myEquipmentExample.getdId()))
            throw new IllegalArgumentException();
		
		myEquipmentMapper.insert(myEquipmentExample);
	}
	
	public void update(MyEquipmentExample myEquipmentExample){
		if (StringUtils.isEmpty(""+myEquipmentExample.getdId()))
            throw new IllegalArgumentException();
		
		myEquipmentMapper.update(myEquipmentExample);
	}
	
	public void delete(MyEquipmentExample myEquipmentExample){
		if (StringUtils.isEmpty(""+myEquipmentExample.getId()))
            throw new IllegalArgumentException();
		
		myEquipmentMapper.delete(myEquipmentExample);
	}
	
	public MyEquipmentExample select(MyEquipmentExample myEquipmentExample){
		
		return myEquipmentMapper.select(myEquipmentExample);
	}
	
	public StateStatisticsExample statusDisplay(String userId){
		
		return myEquipmentMapper.statusDisplay(userId);
				
	}
	
	public PageInfo<MyEquipmentExample> selects(PageModel<MyEquipmentExample> con){
		if (StringUtils.isEmpty(con.toString()))
            throw new IllegalArgumentException();
		
//		if (con.getPageNum() == null || con.getPageNum() <= 0)
//            con.setPageNum(1);
//        if (con.getPageSize() == null || con.getPageSize() <= 0)
//            con.setPageSize(10);
//
//        PageHelper.startPage(con.getPageNum(), con.getPageSize(), "desc");
        
        PageInfo<MyEquipmentExample> pageInfo = myEquipmentMapper.selects(con);
        
        return pageInfo;
	}

}
