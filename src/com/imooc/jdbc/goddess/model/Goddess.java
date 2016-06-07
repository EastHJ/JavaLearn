package com.imooc.jdbc.goddess.model;

import java.sql.Date;

public class Goddess {
	private Integer id;
	private String user_name;
	private String sex ="女";
	private Integer age;
	private Date birthday;
	private String email;

	private String mobile;
	private String create_user;
	private String update_user;
	private Date create_date;
	private Date update_date;
	private Integer isdel=1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		if (0 < age && age < 100) {
			this.age = age;
		}
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();
		if (null != id) {
			info.append("编号：" + id + "\t");
		}
		if (null != user_name) {
			info.append("姓名：" + user_name.trim() + "\t");
		}
		if (null != age) {
			info.append("年龄：" + age + "\t");
		}
		if (null != sex) {
			info.append("性别：" + sex.trim() + "\t");
		}
		if (null != birthday) {
			info.append("生日：" + birthday + "\t");
		}
		if (null != email) {
			info.append("邮箱：" + email.trim() + "\t");
		}
		if (null != mobile) {
			info.append("手机：" + mobile.trim() + "\t");
		}
		if (null != create_user) {
			info.append("创建人：" + create_user.trim() + "\t");
		}
		if (null != create_date) {
			info.append("创建日期：" + create_date + "\t");
		}
		if (null != update_user) {
			info.append("更新人：" + update_user.trim() + "\t");
		}
		
		if (null != update_date) {
			info.append("更新日期" + update_date + "\t");
		}
		return "Goddess [" + info.toString() + "]";
	}

}
