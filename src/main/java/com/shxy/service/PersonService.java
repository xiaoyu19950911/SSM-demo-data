package com.shxy.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.shxy.model.Person;

public interface PersonService {

	List<Person> listallperson();

	public void importexcel(List<Person> list, HttpServletRequest request, HttpServletResponse response);

	public List<Person> listpersonbytype(String t);

	public List<Person> listbynameandbirth(String m, String n);

	public void addperson(Person person);

	public void deleteperson(int pId);

	public void update(Person person);

	public Person listbyid(int pId);
	
	public List<Map<Integer,Integer>> listagecount();
	
	public List<Map<String, Object>> selectTraineeLimit(Map<Integer,Integer> m);
	
	public List<Person> selectTraineeLimite(Map<Integer,Integer> m);
	
	public long getTraineeTotal();
	
	String readExcelFile(File file);
	
	public void addsomeperson(List<Person> list);
}