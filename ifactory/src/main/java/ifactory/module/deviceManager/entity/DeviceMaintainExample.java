package ifactory.module.deviceManager.entity;

import java.util.Date;

public class DeviceMaintainExample {
	
	private Integer id;
	
	private int dId;//设备id
	
	private String maintainContent;//保养内容
	
	private float maintainCosts;//保养费用
	
	private Date maintainTime;//保养时间
	
	private String maintainPerson;//保养人

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getMaintainContent() {
		return maintainContent;
	}

	public void setMaintainContent(String maintainContent) {
		this.maintainContent = maintainContent;
	}

	public float getMaintainCosts() {
		return maintainCosts;
	}

	public void setMaintainCosts(float maintainCosts) {
		this.maintainCosts = maintainCosts;
	}

	public Date getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	public String getMaintainPerson() {
		return maintainPerson;
	}

	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}
	
	

}
