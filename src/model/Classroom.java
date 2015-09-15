package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the CLASSROOM database table.
 * 
 */
@Entity
@Table(name="Classroom", schema="testDB")
@NamedQuery(name="Classroom.findAll", query="SELECT c FROM Classroom c")
public class Classroom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bldgname;

	private String maxcap;

	private String roomnum;

	public Classroom() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBldgname() {
		return this.bldgname;
	}

	public void setBldgname(String bldgname) {
		this.bldgname = bldgname;
	}

	public String getMaxcap() {
		return this.maxcap;
	}

	public void setMaxcap(String maxcap) {
		this.maxcap = maxcap;
	}

	public String getRoomnum() {
		return this.roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

}