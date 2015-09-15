package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the "TIME" database table.
 * 
 */
@Entity
@Table(name="Time", schema="testDB")
@NamedQuery(name="Time.findAll", query="SELECT t FROM Time t")
public class Time implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal courseid;

	@Column(name="\"DAY\"")
	private String day;

	private String duration;

	@Column(name="\"TIME\"")
	private String time;

	public Time() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getCourseid() {
		return this.courseid;
	}

	public void setCourseid(BigDecimal courseid) {
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