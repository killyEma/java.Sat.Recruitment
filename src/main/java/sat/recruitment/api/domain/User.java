package sat.recruitment.api.domain;

import javax.validation.constraints.NotNull;

public class User {
	@NotNull(message = "400 BAD_REQUEST \"The name is required\"")
	public String name;
	@NotNull(message = "400 BAD_REQUEST \" The email is required\"")
	public String email;
	@NotNull(message = "400 BAD_REQUEST \" The address is required\"")
	public String address;
	@NotNull(message = "400 BAD_REQUEST \" The phone is required\"")
	public String phone;
	@NotNull(message = "400 BAD_REQUEST \" The userType is required\"")
	public UserType userType;
	@NotNull(message = "400 BAD_REQUEST \" The Money is required\"")
	public Double money;
	
	public User() {
		
	}

	public User(String name, String email, String address, String phone, UserType userType, Double money) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.userType = userType;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
}
