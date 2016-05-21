package cn.zhku.modal;

import javax.persistence.Entity;

@Entity
public class Student extends User{
	private String classFrom;// 专业班级
	
	
	public Student() {
		super();
	}
	
	public Student(String code, String name, String sex, int age, String password,String classFrom) {
		super(code, name,sex, age, password);
		this.classFrom = classFrom;
	}

	public String getClassFrom() {
		return classFrom;
	}

	public void setClassFrom(String classFrom) {
		this.classFrom = classFrom;
	}
}
