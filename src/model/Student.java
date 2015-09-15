package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the STUDENT database table.
 * 
 */
@Entity
@Table(name="Student", schema="TESTDB")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String entryyear;

	private String gradyear;

	private String major;

	private String name;

	public Student() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntryyear() {
		return this.entryyear;
	}

	public void setEntryyear(String entryyear) {
		this.entryyear = entryyear;
	}

	public String getGradyear() {
		return this.gradyear;
	}

	public void setGradyear(String gradyear) {
		this.gradyear = gradyear;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}