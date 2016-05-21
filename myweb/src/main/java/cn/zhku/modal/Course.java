package cn.zhku.modal;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;	// 主键，自增加
	
	private String courseName; //课程名
	private String courseCode;  //课程号
	private Long xueFen;    //学分
	private Long hadSNum;   //已选人数 
	private Long mostSNum;  //可选人数
	private String teacherCode; //教师编号
	
	
	public Course() {
		super();
	}


	


	public Course(String courseName, String courseCode, Long xueFen, Long hadSNum, Long mostSNum,
			String teacherCode) {
		super();
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.xueFen = xueFen;
		this.hadSNum = hadSNum;
		this.mostSNum = mostSNum;
		this.teacherCode = teacherCode;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Long getHadSNum() {
		return hadSNum;
	}


	public void setHadSNum(Long hadSNum) {
		this.hadSNum = hadSNum;
	}


	public Long getMostSNum() {
		return mostSNum;
	}


	public void setMostSNum(Long mostSNum) {
		this.mostSNum = mostSNum;
	}


	public String getTeacherCode() {
		return teacherCode;
	}


	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}
	
}
