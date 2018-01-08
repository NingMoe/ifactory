package ifactory.module.deviceManager.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.DeviceExample;
import ifactory.module.deviceManager.entity.StateStatisticsExample;
import ifactory.module.deviceManager.mapper.DeviceMapper;
import ifactory.utils.VerifyCodeConstant;

@Service
public class DeviceService {
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	public DeviceService(DeviceMapper deviceMapper){
		this.deviceMapper = deviceMapper;
	}
	
	public void add(DeviceExample deviceExample){
		
		if (StringUtils.isEmpty(deviceExample.getCompanyCode()))
            throw new IllegalArgumentException();
		
		if(!StringUtils.isEmpty(select(deviceExample).getWorkshop()))
			throw new IllegalArgumentException(""+VerifyCodeConstant.EQUIPMENTEXIST);
		
		deviceMapper.insert(deviceExample);
	}
	
	public void update(DeviceExample deviceExample){
		
		if (StringUtils.isEmpty(deviceExample.getCompanyCode()))
            throw new IllegalArgumentException();
		
		if(!StringUtils.isEmpty(select(deviceExample).getWorkshop()))
			throw new IllegalArgumentException(""+VerifyCodeConstant.EQUIPMENTEXIST);
		
		deviceMapper.update(deviceExample);
		
	}
	
	public void delete(DeviceExample deviceExample){
		
		if (StringUtils.isEmpty(""+deviceExample.getId()))
            throw new IllegalArgumentException();
		
		deviceMapper.delete(deviceExample);
	}
	
	public DeviceExample select(DeviceExample deviceExample){
		
		return deviceMapper.select(deviceExample);
	}
	
	public StateStatisticsExample statusDisplay(){
		
		return deviceMapper.statusDisplay();
	}
	
	public PageInfo<DeviceExample> selects(PageModel<DeviceExample> con){
		
		if (StringUtils.isEmpty(con.toString()))
            throw new IllegalArgumentException();
		
		if (con.getPageNum() == null || con.getPageNum() <= 0)
            con.setPageNum(1);
        if (con.getPageSize() == null || con.getPageSize() <= 0)
            con.setPageSize(10);

        PageHelper.startPage(con.getPageNum(), con.getPageSize(), "desc");
        
		PageInfo<DeviceExample> dePageInfo = deviceMapper.selects(con);
		
		return dePageInfo;
	}
	
}
