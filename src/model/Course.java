package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@Table(name="COURSE", schema="TESTDB")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="COURSE_ID_GENERATOR", sequenceName="COURSE_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COURSE_ID_GENERATOR")
	private long id;

	private String coursenum;

	private String credits;

	private long deptid;

	private String description;

	private long enabled;

	private long instructorid;

	private String name;

	private long roomid;

	private String section;

	private String semester;

	private String subjectcode;

	private long timeid;

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

	public long getDeptid() {
		return this.deptid;
	}

	public void setDeptid(long deptid) {
		this.deptid = deptid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getEnabled() {
		return this.enabled;
	}

	public void setEnabled(long enabled) {
		this.enabled = enabled;
	}

	public long getInstructorid() {
		return this.instructorid;
	}

	public void setInstructorid(long instructorid) {
		this.instructorid = instructorid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRoomid() {
		return this.roomid;
	}

	public void setRoomid(long roomid) {
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

	public long getTimeid() {
		return this.timeid;
	}

	public void setTimeid(long timeid) {
		this.timeid = timeid;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", coursenum=" + coursenum + ", credits="
				+ credits + ", deptid=" + deptid + ", description="
				+ description + ", enabled=" + enabled + ", instructorid="
				+ instructorid + ", name=" + name + ", roomid=" + roomid
				+ ", section=" + section + ", semester=" + semester
				+ ", subjectcode=" + subjectcode + ", timeid=" + timeid + "]";
	}
	
}