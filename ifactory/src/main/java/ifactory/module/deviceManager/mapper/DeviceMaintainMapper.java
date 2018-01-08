package ifactory.module.deviceManager.mapper;


import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.DeviceMaintainExample;

public interface DeviceMaintainMapper {
	
	public void insert(DeviceMaintainExample deviceMaintainExample);
	
	public void delete(DeviceMaintainExample deviceMaintainExample);
	
	public void update(DeviceMaintainExample deviceMaintainExample);
	
	public DeviceMaintainExample select(DeviceMaintainExample deviceMaintainExample);
	
	public PageInfo<DeviceMaintainExample> selects(PageModel<DeviceMaintainExample> deviceMaintainExample);

}
