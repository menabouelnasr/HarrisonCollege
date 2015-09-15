package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the INSTRUCTOR database table.
 * 
 */
@Entity
@Table(name="Instructor", schema="testdb")
@NamedQuery(name="Instructor.findAll", query="SELECT i FROM Instructor i")
public class Instructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int deptid;

	private String name;

	private String officenum;

	public Instructor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeptid() {
		return this.deptid;
	}

	public void setDeptid(int deptid) {
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