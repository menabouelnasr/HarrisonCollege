package model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the INSTRUCTOR database table.
 * 
 */
@Entity
@NamedQuery(name="Instructor.findAll", query="SELECT i FROM Instructor i")
public class Instructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="INSTRUCTOR_ID_GENERATOR", sequenceName="INSTRUCTOR_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSTRUCTOR_ID_GENERATOR")
	private long id;

	private long deptid;

	private String name;

	private String officenum;

	public Instructor() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDeptid() {
		return this.deptid;
	}

	public void setDeptid(long deptid) {
		this.deptid = deptid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOfficenum() {
		return this.officenum;
	}

	public void setOfficenum(String officenum) {
		this.officenum = officenum;
	}

}