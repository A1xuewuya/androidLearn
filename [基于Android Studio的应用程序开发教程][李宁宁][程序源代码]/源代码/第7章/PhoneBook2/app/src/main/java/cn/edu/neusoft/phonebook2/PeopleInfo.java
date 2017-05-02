package cn.edu.neusoft.phonebook2;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "PeopleInfo")
public class PeopleInfo {
	@Id
	@Column(column = "ID")
	public int ID;
	@Column(column = "Name")
	public String Name;
	@Column(column = "Phone_number")
	public String Phone_number;
	@Column(column = "Address")
	public String Address;
	@Column(column = "E_mail")
	public String E_mail;

	public PeopleInfo()
	{

	}

	public PeopleInfo(String name,String phone_number,String address,String e_mail)	{
		this.Name=name;
		this.Phone_number=phone_number;
		this.Address=address;
		this.E_mail=e_mail;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getE_mail() {
		return E_mail;
	}

	public void setE_mail(String e_mail) {
		E_mail = e_mail;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone_number() {
		return Phone_number;
	}

	public void setPhone_Number(String phone_number) {
		Phone_number = phone_number;
	}

	@Override
	public String toString() {
		  return "PeopleInfo{" +
				"id=" + getID() +
				", name='" + Name + '\'' +
				", email='" + E_mail + '\'' +

				'}';
	}
}
