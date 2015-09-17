package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "TIME" database table.
 * 
 */
@Entity
@Table(name="TIME",schema="testdb")
@NamedQuery(name="Time.findAll", query="SELECT t FROM Time t")
public class Time implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int courseid;

	@Column(name="\"DAY\"")
	private String day;

	private String duration;

	@Column(name="\"TIME\"")
	private String time;

	public Time() {
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

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}