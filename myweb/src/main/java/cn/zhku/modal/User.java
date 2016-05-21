package cn.zhku.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)// 整个类层次结构用同一张表，DTYPE区分
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;	// 主键，自增加
	
	private String code;// 学号或者工号
	private String name;
	private String password;
	private String sex;
	private int age;
	
	@Transient // 不保存到数据库的属性，用于表示用户类型
	private String type;
	
	
	public User() {
		super();
	}

	public User(String code, String name, String sex, int age, String password) {
		super();
		this.code = code;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
