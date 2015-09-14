package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USR database table.
 * 
 */
@Entity
@Table(name="Usr", schema="TESTDB")
@NamedQuery(name="Usr.findAll", query="SELECT u FROM Usr u")
public class Usr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="USRS_ID_GENERATOR", sequenceName="USRS_ID_GENERATOR", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USRS_ID_GENERATOR")
	private long id;

	private String email;

	@Column(name="\"MONEY\"")
	private String money;

	private String password;

	@Column(name="\"TYPE\"")
	private String type;

	private int typeid;

	private String username;

	public Usr()
	{
		
	}
	public Usr(String email, String name, String password, String type, int tID, String money) 
	{
		this.money = money;
		this.email = email;
		this.username = name;
		this.password = password;
		this.type=type;
		this.typeid=tID;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTypeid() {
		return this.typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}