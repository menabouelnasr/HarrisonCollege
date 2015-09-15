package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ENROLL database table.
 * 
 */
@Entity
@Table(name="Enroll", schema="TESTDB")
@NamedQuery(name="Enroll.findAll", query="SELECT e FROM Enroll e")
public class Enroll implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int courseid;

	private String grade;

	private int studid;

	public Enroll() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseid() {
		return this.courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getStudid() {
		return this.studid;
	}

	public void setStudid(int studid) {
		this.studid = studid;
	}

}