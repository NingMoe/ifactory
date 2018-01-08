package ifactory.module.deviceManager.mapper;

import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.DeviceExample;
import ifactory.module.deviceManager.entity.StateStatisticsExample;

public interface DeviceMapper {
	
	public void insert(DeviceExample deviceExample);
	
	public void delete(DeviceExample deviceExample);
	
	public void update(DeviceExample deviceExample);
	
	public DeviceExample select(DeviceExample deviceExample);
	
	public StateStatisticsExample statusDisplay();
	
	public PageInfo<DeviceExample> selects(PageModel<DeviceExample> deviceExample);
	

}
