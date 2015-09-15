package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@Table(name="Course", schema="testDB")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String coursenum;

	private String credits;

	private BigDecimal deptid;

	private String description;

	private BigDecimal enabled;

	private BigDecimal instructorid;

	private String name;

	private BigDecimal roomid;

	@Column(name="\"SECTION\"")
	private String section;

	private String semester;

	private String subjectcode;

	private BigDecimal timeid;

	public Course() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public BigDecimal getDeptid() {
		return this.deptid;
	}

	public void setDeptid(BigDecimal deptid) {
		this.deptid = deptid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getEnabled() {
		return this.enabled;
	}

	public void setEnabled(BigDecimal enabled) {
		this.enabled = enabled;
	}

	public BigDecimal getInstructorid() {
		return this.instructorid;
	}

	public void setInstructorid(BigDecimal instructorid) {
		this.instructorid = instructorid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRoomid() {
		return this.roomid;
	}

	public void setRoomid(BigDecimal roomid) {
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

	public BigDecimal getTimeid() {
		return this.timeid;
	}

	public void setTimeid(BigDecimal timeid) {
		this.timeid = timeid;
	}

}