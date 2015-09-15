package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the INSTRUCTOR database table.
 * 
 */
@Entity
@Table(name="Instructor", schema="testDB")
@NamedQuery(name="Instructor.findAll", query="SELECT i FROM Instructor i")
public class Instructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal deptid;

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

	public BigDecimal getDeptid() {
		return this.deptid;
	}

	public void setDeptid(BigDecimal deptid) {
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