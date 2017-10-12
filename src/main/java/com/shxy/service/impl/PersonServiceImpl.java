package com.shxy.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shxy.dao.PersonMapper;
import com.shxy.model.ExportExcel;
import com.shxy.model.Person;
import com.shxy.service.PersonService;
import com.shxy.util.ReadExcel;


@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonMapper personMapper;

	
	/** 
	获取所有人员信息
	@param null
	@return List<Person> 所有人员信息的列表 
	*/
	public List<Person> listallperson() {
		// TODO Auto-generated method stub
		return personMapper.listAllPerson();
	}

	
	/** 
	将表格信息导出至excel
	@param list 人员信息的集合表
	@param request 
	@param response 
	@return null
	*/
	public void importexcel(List<Person> list, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String fileName = "导出Excel.xls";
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = null;
		try {
			output = response.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		// 定义单元格报头
		String worksheetTitle = "Excel导出Student信息";

		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		// cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		// cellStyle.setWrapText(true);
		// ------------------------------------------------------------------
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyleTitle.setFont(font);

		// 工作表名
		String id = "序号";
		String name = "姓名";
		String sex = "性别";
		String idcard = "身份证";
		String age = "年龄";
		String birth = "出生日期";
		String address = "籍贯";
		String grade = "最高学历";

		HSSFSheet sheet = wb.createSheet();
		ExportExcel exportExcel = new ExportExcel(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 6);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = row1.createCell(0);

		// 第一行第一列

		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(id));
		// 第一行第er列
		cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(name));

		// 第一行第san列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(sex));

		// 第一行第si列
		cell1 = row1.createCell(3);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(idcard));

		// 第一行第wu列
		cell1 = row1.createCell(4);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(age));

		// 第一行第liu列
		cell1 = row1.createCell(5);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(birth));

		// 第一行第七列
		cell1 = row1.createCell(6);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(address));

		// 第一行第八列
		cell1 = row1.createCell(7);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(grade));

		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		Person person = new Person();
		for (int i = 0; i < list.size(); i++) {
			person = list.get(i);
			row = sheet.createRow(i + 2);

			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(i + ""));

			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(person.getpName() + ""));

			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(person.getpSex() + ""));

			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(person.getpIdcard() + ""));

			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(person.getpAge() + ""));

			cell = row.createCell(5);
			cell.setCellValue(new HSSFRichTextString(person.getpBirth() + ""));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(6);
			cell.setCellValue(new HSSFRichTextString(person.getpAddress() + ""));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(7);
			cell.setCellValue(new HSSFRichTextString(person.getpGrade() + ""));
			cell.setCellStyle(cellStyle);

		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			list.clear();
		}
	}

	
	/** 
	根据类型列出人员信息
	@param t 人员的类型pType
	@return List<Person> 所有人员信息的列表
	*/
	public List<Person> listpersonbytype(String t) {
		// TODO Auto-generated method stub
		return personMapper.listPersonByType(t);
	}

	
	/** 
	根据类型列出人员信息
	@param t 人员的类型pType
	@return List<Person> 所有人员信息的列表
	*/
	public List<Person> listbynameandbirth(String m, String n) {
		// TODO Auto-generated method stub
		return personMapper.selectByNameAndBirth(m, n);
	}

	
	/** 
	添加人员信息至数据库
	@param person 人员对象
	@return null
	*/
	public void addperson(Person person) {
		// TODO Auto-generated method stub
		personMapper.insertSelective(person);
	}

	
	/** 
	根据pId删除人员信息
	@param pId 人员信息的数据库id
	@return null
	*/
	public void deleteperson(int pId) {
		// TODO Auto-generated method stub
		personMapper.deleteByPrimaryKey(pId);
	}

	
	/** 
	更新人员信息
	@param person 人员对象
	@return null
	*/
	public void update(Person person) {
		// TODO Auto-generated method stub
		personMapper.updateByPrimaryKeySelective(person);
	}

	
	/** 
	根据pId查找某个人的信息
	@param pId 人员信息的数据库id
	@return null
	*/
	public Person listbyid(int pId) {
		// TODO Auto-generated method stub
		return personMapper.selectByPrimaryKey(pId);
	}

	
	/** 
	列出每个年龄以及对应的数量
	@param null
	@return List<Map<Integer,Integer>> 每个年龄以及对应的数量list集合
	*/
	public List<Map<Integer,Integer>> listagecount() {
		// TODO Auto-generated method stub
	 return	personMapper.selectByAgeAndCount();
	}

	
	/** 
	查询limit限制条件的部分人员信息（同下）
	@param Map<Integer,Integer> m  limit的限制条件，即firstpage和row
	@return List<Map<String,Object>> 符合条件的人员信息的list集合
	*/
	public List<Map<String,Object>> selectTraineeLimit(Map<Integer,Integer> m) {
		// TODO Auto-generated method stub
		return personMapper.selectTraineeLimit(m);
	}
	
	
	/** 
	查询limit限制条件的部分人员信息（同上）
	@param Map<Integer,Integer> m  limit的限制条件，即firstpage和row
	@return List<Person> 符合条件的人员信息的list集合
	*/
	public List<Person> selectTraineeLimite(Map<Integer, Integer> m) {
		// TODO Auto-generated method stub
		return personMapper.selectTraineeLimite(m);
	}

	
	/** 
	查询人员的总数量
	@param null
	@return long 总的数量
	*/
	public long getTraineeTotal() {
		// TODO Auto-generated method stub
		return personMapper.getTraineeTotal();
	}

	
	/** 
	读取excel
	@param file 上传的文件
	@return String 
	*/	
	public String readExcelFile(File file) {
		// TODO Auto-generated method stub
		String result ="";//创建处理EXCEL的类
        ReadExcel readExcel=new ReadExcel();//解析excel，获取上传的事件单
        List<Person> useList = readExcel.getExcelInfo(file);//需要导入的person的list集合
        addsomeperson(useList);//将list导入数据库
        if(useList != null && !useList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
	}

	
	/** 
	批量添加数据至数据库
	@param list 人员信息的集合
	@return null 
	*/
	public void addsomeperson(List<Person> list) {
		// TODO Auto-generated method stub
		personMapper.addsomeperson(list);
	}
	
	

	

}
