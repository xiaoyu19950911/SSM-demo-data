package com.shxy.model;

public class Student {
	private int id;
	private String name;
	private int age;
	private String address;
	private String tel;
	private char sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", tel=" + tel
				+ ", sex=" + sex + "]";
	}

	public Student(int id, String name, int age, String address, String tel, char sex) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.tel = tel;
		this.sex = sex;
	}

	public Student() {
		super();
	}
}
