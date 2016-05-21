package cn.zhku.modal;

import javax.persistence.Entity;

@Entity
public class Teacher extends User {
	private String rank;// 职称

	
	public Teacher() {
		super();
	}

	public Teacher(String code, String name, String sex, int age, String password,String rank) {
		super(code, name,sex, age, password);
		this.rank = rank;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
