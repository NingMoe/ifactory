package ifactory.module.deviceManager.mapper;

import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.MyEquipmentExample;
import ifactory.module.deviceManager.entity.StateStatisticsExample;

public interface MyEquipmentMapper {
	
	public void insert(MyEquipmentExample myEquipment);
	
	public void update(MyEquipmentExample myEquipment);
	
	public void delete(MyEquipmentExample myEquipment);
	
	public StateStatisticsExample statusDisplay(String userId);
	
	public MyEquipmentExample select(MyEquipmentExample myEquipment);
	
	public PageInfo<MyEquipmentExample> selects(PageModel<MyEquipmentExample> myEquipment);

}
