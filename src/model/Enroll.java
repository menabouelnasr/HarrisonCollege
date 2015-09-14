package model;
import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the ENROLL database table.
 * 
 */
@Entity
@Table(name="ENROLL", schema="TESTDB")
@NamedQuery(name="Enroll.findAll", query="SELECT e FROM Enroll e")
public class Enroll implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="ENROLL_ID_GENERATOR", sequenceName="ENROLL_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENROLL_ID_GENERATOR")
	private long id;

	private long courseid;

	private String grade;

	private long studid;

	public Enroll() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCourseid() {
		return this.courseid;
	}

	public void setCourseid(long courseid) {
		this.courseid = courseid;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public long getStudid() {
		return this.studid;
	}

	public void setStudid(long studid) {
		this.studid = studid;
	}

}