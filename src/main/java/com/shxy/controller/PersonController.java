package com.shxy.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shxy.model.Page;
import com.shxy.model.Person;

import com.shxy.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	PersonService personservice;

	@RequestMapping("/listall")
	public ModelAndView listall(@RequestParam(required = true, defaultValue = "1") Integer page) {
		ModelAndView mav = new ModelAndView();
		PageHelper.startPage(page, 6);//分页插件pagehelper
		List<Person> ls = personservice.listallperson();
		PageInfo<Person> p = new PageInfo<Person>(ls);
		mav.addObject("page", p);
		mav.addObject("cs", ls);
		mav.setViewName("person");
		return mav;
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = true, defaultValue = "1") Integer page, String pName) {
		ModelAndView mav = new ModelAndView();
		// PageHelper.startPage(page, 6);
		List<Person> ls = personservice.listallperson();
		// PageInfo<Person> p = new PageInfo<Person>(ls);
		// mav.addObject("page", p);
		mav.addObject("cs", ls);
		mav.addObject("list", "list");
		mav.setViewName("test");
		return mav;
	}

	@RequestMapping("/onlist") // 在编
	public ModelAndView onlist(@RequestParam(required = true, defaultValue = "1") Integer page, String pName) {
		ModelAndView mav = new ModelAndView();
		PageHelper.startPage(page, 6);
		List<Person> ls = personservice.listpersonbytype("0");
		PageInfo<Person> p = new PageInfo<Person>(ls);
		mav.addObject("page", p);
		mav.addObject("cs", ls);
		mav.addObject("list", "onlist");
		mav.setViewName("test");
		return mav;
	}

	@RequestMapping("/relist") // 减少
	public ModelAndView relist(@RequestParam(required = true, defaultValue = "1") Integer page, String pName) {
		ModelAndView mav = new ModelAndView();
		PageHelper.startPage(page, 6);
		List<Person> ls = personservice.listpersonbytype("1");
		PageInfo<Person> p = new PageInfo<Person>(ls);
		mav.addObject("page", p);
		mav.addObject("cs", ls);
		mav.addObject("list", "relist");
		mav.setViewName("test");
		return mav;
	}

	@RequestMapping("/outlist") // 离退
	public ModelAndView outlist(@RequestParam(required = true, defaultValue = "1") Integer page, String pName) {
		ModelAndView mav = new ModelAndView();
		PageHelper.startPage(page, 6);
		List<Person> ls = personservice.listpersonbytype("2");
		PageInfo<Person> p = new PageInfo<Person>(ls);
		mav.addObject("page", p);
		mav.addObject("cs", ls);
		mav.addObject("list", "outlist");
		mav.setViewName("test");
		return mav;
	}
	/*
	 * @RequestMapping("/OutputExcel") public ModelAndView
	 * OutputExcel(@RequestParam(required = true, defaultValue = "1") Integer
	 * page, HttpServletRequest request, HttpServletResponse response) {
	 * PageHelper.startPage(page, 6); List<Person> ls =
	 * personservice.listallperson(); PageInfo<Person> p = new
	 * PageInfo<Person>(ls); ModelAndView mav = new ModelAndView();
	 * mav.addObject("page", p); mav.addObject("cs", ls);
	 * personservice.importexcel(ls, request, response); return mav; }
	 */

	/**
	 * @肖宇
	 * @时间 2017-10-11
	 * @param file
	 *            上传的文件
	 * @return String 导入excel
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {

		// 文件保存路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "fileUpload/temp/"+ file.getOriginalFilename();
		// 转存文件
		file.transferTo(new File(filePath));
		
		String result = personservice.readExcelFile(new File(filePath));
		return result;
	}

	/**
	 * @肖宇
	 * @时间 2017-10-11
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @return Map 导出excel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/OutputExcel")
	public ModelAndView OutputExcel(@RequestParam(defaultValue = "1", required = false) String page,
			@RequestParam(defaultValue = "10", required = false) String rows,
			@RequestParam(value = "title", required = false) String title, HttpServletRequest request,
			HttpServletResponse response) {
		Page pageBean = new Page(Integer.parseInt(page), Integer.parseInt(rows));
		Map paraMap = new HashMap();
		paraMap.put("title", title);
		paraMap.put("firstPage", pageBean.getFirstPage());
		paraMap.put("rows", pageBean.getRows());
		List<Person> list = personservice.selectTraineeLimite(paraMap);
		long total = personservice.getTraineeTotal();
		System.out.println(total);
		System.out.println(list);
		ModelAndView mav = new ModelAndView();
		personservice.importexcel(list, request, response);
		return mav;
	}

	/**
	 * @肖宇
	 * @时间 2017-10-11
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param title
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/showAllTrainee")
	public Map showAllTrainee(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "title", required = false) String title) {
		Page pageBean = new Page(Integer.parseInt(page), Integer.parseInt(rows));
		Map reMap = new HashMap();
		Map paraMap = new HashMap();
		paraMap.put("title", title);
		paraMap.put("firstPage", pageBean.getFirstPage());
		paraMap.put("rows", pageBean.getRows());

		try {
			List<Map<String, Object>> list = personservice.selectTraineeLimit(paraMap);
			long total = personservice.getTraineeTotal();
			reMap.put("rows", list); // 存放每页记录数
			reMap.put("total", total); // 存放总记录数 ，必须的
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reMap;
	}

	@RequestMapping("/serchlist") // 查找
	public ModelAndView serchlist(@RequestParam(required = true, defaultValue = "1") Integer page, Person person)
			throws UnsupportedEncodingException {
		String s = person.getpName();
		s = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		ModelAndView mav = new ModelAndView();
		// PageHelper.startPage(page, 6);
		System.out.println(s);
		System.out.println(person.getpName());
		System.out.println(person.getpBirth());
		List<Person> ls = personservice.listbynameandbirth(person.getpName(), person.getpBirth());
		// PageInfo<Person> p = new PageInfo<Person>(ls);
		// mav.addObject("page", p);
		mav.addObject("cs", ls);
		mav.addObject("list", "serchlist");
		mav.setViewName("test");
		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("re", "addperson");
		mav.setViewName("addperson");
		return mav;
	}

	/*
	 * @RequestMapping("/addperson")
	 * 
	 * @ResponseBody public Map<String, Object> addperson(Person person) throws
	 * ParseException { Map<String, Object> result = new HashMap<String,
	 * Object>(); Date date = new Date(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy"); String idcard = person.getpIdcard(); String s1
	 * = sdf.format(date); Date today = sdf.parse(s1); String birthday =
	 * idcard.substring(6, 14); String year = idcard.substring(6, 10); Date
	 * birth = sdf.parse(year); // int age = Integer.intValue(new
	 * SimpleDateFormat("yyyy").format(new // Date())); int age =
	 * today.getYear() - birth.getYear(); person.setpBirth(birthday);
	 * person.setpAge(age); personservice.addperson(person);
	 * result.put("errorMsg", true); System.out.println(result); return result;
	 * }
	 */

	@RequestMapping("/addperson")
	public ModelAndView addperson(@RequestParam(required = true, defaultValue = "1") Integer page, Person person)
			throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String idcard = person.getpIdcard();
		String s1 = sdf.format(date);
		Date today = sdf.parse(s1);
		String birthday = idcard.substring(6, 14);
		String year = idcard.substring(6, 10);
		Date birth = sdf.parse(year);
		// int age = Integer.intValue(new SimpleDateFormat("yyyy").format(new
		// Date()));
		int age = today.getYear() - birth.getYear();
		person.setpBirth(birthday);
		person.setpAge(age);
		personservice.addperson(person);
		ModelAndView mav = new ModelAndView();// ("redirect:/person/list");
		return mav;
	}

	@RequestMapping("/deleteperson")
	public ModelAndView deleteperson(int pId) {
		personservice.deleteperson(pId);
		ModelAndView mav = new ModelAndView("redirect:/person/list");
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView update(int pId) {
		ModelAndView mav = new ModelAndView();
		Person person = personservice.listbyid(pId);
		mav.addObject("p", person);
		mav.addObject("re", "updateperson");
		mav.setViewName("addperson");
		return mav;
	}

	@RequestMapping("/updateperson")
	public ModelAndView updateperson(Person person) {
		personservice.update(person);
		ModelAndView mav = new ModelAndView("redirect:/person/list");
		return mav;
	}

	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		List<Person> ls = personservice.listallperson();
		mav.addObject("cs", ls);
		mav.addObject("list", "list");
		mav.setViewName("test");
		return mav;
	}

	@RequestMapping("/chart")
	@ResponseBody
	public Map<String, Object> chart() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<Integer, Integer>> ls = personservice.listagecount();
		List<Map<String, Object>> l = new LinkedList<Map<String, Object>>();
		for (int i = 0; i < ls.size(); i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", ls.get(i).get("pAge"));
			m.put("y", ls.get(i).get("pAgeCount"));
			if (i == 0) {
				m.put("sliced", "true");
				m.put("selected", "true");
			}
			l.add(m);
		}
		result.put("type", "pie");
		result.put("name", "百分比：");
		result.put("data", l);
		return result;
	}

}
