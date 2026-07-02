package com.microservice.ecart.apigateway.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import jakarta.persistence.Entity;

//@Entity
@Table("users")
public class UserApp {
    
	@Transient
	@Id
    
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String mobile_no;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getMobile_no() {return mobile_no;}
	public void setMobile_no(String mobile_no) {this.mobile_no = mobile_no;}
    
    
    
    
    
}
