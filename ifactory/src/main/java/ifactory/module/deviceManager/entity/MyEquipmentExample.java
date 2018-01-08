package ifactory.module.deviceManager.entity;


public class MyEquipmentExample {
	
	private Integer id;
	
	private Integer userId;//用户id
	
	private Integer dId;//设备id
	
	private DeviceExample deviceExample;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public DeviceExample getDeviceExample() {
		return deviceExample;
	}

	public void setDeviceExample(DeviceExample deviceExample) {
		this.deviceExample = deviceExample;
	}
}
