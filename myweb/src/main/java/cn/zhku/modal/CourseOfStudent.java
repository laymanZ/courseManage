package cn.zhku.modal;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CourseOfStudent {
	@Id
	@GeneratedValue
	private Long id;	// 主键，自增加
	
	private String code;
	private String name;
	private String courseName; //课程名
	private String courseCode;  //课程号
	private Long xueFen;    //学分
	private String teacherCode; //教师编号
	
	
	public CourseOfStudent() {
		super();
	}


	public CourseOfStudent(String code, String name, String courseName, String courseCode, Long xueFen,
			String teacherCode) {
		super();
		this.code = code;
		this.name = name;
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.xueFen = xueFen;
		this.teacherCode = teacherCode;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCourseCode() {
		return courseCode;
	}


	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	public Long getXueFen() {
		return xueFen;
	}


	public void setXueFen(Long xueFen) {
		this.xueFen = xueFen;
	}


	public String getTeacherCode() {
		return teacherCode;
	}


	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}
	

}
