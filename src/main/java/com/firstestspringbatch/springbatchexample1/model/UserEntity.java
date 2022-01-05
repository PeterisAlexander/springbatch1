package com.firstestspringbatch.springbatchexample1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
	
	@Id
	private Integer id;
	private String name;
	private String dept;
	private Integer salary;
	private Date time;
	
	public UserEntity() {
		
	}
	
	public UserEntity(Integer id, String name, String dept, Integer salary, Date time) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		this.time = time;
	}

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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dept='").append(dept).append('\'');
        sb.append(", salary=").append(salary).append('\'');
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
	

}
