package ifactory.module.deviceManager.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.DeviceMaintainExample;
import ifactory.module.deviceManager.mapper.DeviceMaintainMapper;

@Service
public class DeviceMaintainService {
	
	@Autowired
	private DeviceMaintainMapper deviceMaintainMapper;
	
	public DeviceMaintainService(DeviceMaintainMapper deviceMaintainMapper){
		this.deviceMaintainMapper = deviceMaintainMapper;
	}
	
	public void add(DeviceMaintainExample deviceMaintainExample){
		
		if (StringUtils.isEmpty(""+deviceMaintainExample.getdId()))
            throw new IllegalArgumentException();
		
		deviceMaintainMapper.insert(deviceMaintainExample);
	}
	
	public void update(DeviceMaintainExample deviceMaintainExample){
		
		if (StringUtils.isEmpty(""+deviceMaintainExample.getdId()))
            throw new IllegalArgumentException();
		
		deviceMaintainMapper.update(deviceMaintainExample);
	}
	
	public void delete(DeviceMaintainExample deviceMaintainExample){
		
		if (StringUtils.isEmpty(""+deviceMaintainExample.getdId()))
            throw new IllegalArgumentException();
		
		deviceMaintainMapper.delete(deviceMaintainExample);
	}
	
	public DeviceMaintainExample select(DeviceMaintainExample deviceMaintainExample){
		return deviceMaintainMapper.select(deviceMaintainExample);
	}
	
	public PageInfo<DeviceMaintainExample> selects(PageModel<DeviceMaintainExample> con){
		
		if (StringUtils.isEmpty(con.toString()))
            throw new IllegalArgumentException();
		
		if (con.getPageNum() == null || con.getPageNum() <= 0)
            con.setPageNum(1);
        if (con.getPageSize() == null || con.getPageSize() <= 0)
            con.setPageSize(10);

        PageHelper.startPage(con.getPageNum(), con.getPageSize(), "desc");
        
        PageInfo<DeviceMaintainExample> page = deviceMaintainMapper.selects(con);
		
		return page;
	}
	
	

}
