package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the STUDENT database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="STUDENT_ID_GENERATOR", sequenceName="STUDENT_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STUDENT_ID_GENERATOR")
	private long id;

	private String entryyear;

	private String gradyear;

	private String major;

	private String name;

	public Student() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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