package composite_apps.bean;

public class EmployeeBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3759670352633400168L;
	private Integer employee_id;
	private String name;
	private String phone_number;
	private String supervisors;
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(String supervisors) {
		this.supervisors = supervisors;
	}
	
}
