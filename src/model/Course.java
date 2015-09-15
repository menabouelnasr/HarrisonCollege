package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@Table(name="Course", schema="TESTDB")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String coursenum;

	private String credits;

	private int deptid;

	private String description;

	private int enabled;

	private int instructorid;

	private String name;

	private int roomid;

	@Column(name="\"SECTION\"")
	private String section;

	private String semester;

	private String subjectcode;

	private int timeid;

	public Course() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoursenum() {
		return this.coursenum;
	}

	public void setCoursenum(String coursenum) {
		this.coursenum = coursenum;
	}

	public String getCredits() {
		return this.credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public int getDeptid() {
		return this.deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEnabled() {
		return this.enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getInstructorid() {
		return this.instructorid;
	}

	public void setInstructorid(int instructorid) {
		this.instructorid = instructorid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomid() {
		return this.roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSubjectcode() {
		return this.subjectcode;
	}

	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	public int getTimeid() {
		return this.timeid;
	}

	public void setTimeid(int timeid) {
		this.timeid = timeid;
	}

}