package ifactory.module.deviceManager.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;



public class DeviceExample {
	
	private Integer id;//id
	
	private String name;//设备名称
	
	private String companyCode;//工厂编码
	
	private String workshop;//车间
	
	private String type;//类型
	
	private String number;//编号
	
	private String specificationModel;//规格型号
	
	private Integer originalValue;//原始价值
	
	private Date productionTime;//投产时间
	
	private String manufacturerName;//厂家名称
	
	private String contacts;//联系人
	
	private String contactInformation;//联系方式
	
	private String guardian;//维护人
	
	private int status;//状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getWorkshop() {
		return workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSpecificationModel() {
		return specificationModel;
	}

	public void setSpecificationModel(String specificationModel) {
		this.specificationModel = specificationModel;
	}

	public Integer getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Integer originalValue) {
		this.originalValue = originalValue;
	}

	public Date getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(Date productionTime) {
		this.productionTime = productionTime;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
