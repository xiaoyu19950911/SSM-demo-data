package com.shxy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.shxy.model.Person;

public class ReadExcel {
	 //总行数
    private int totalRows = 0;  
    //总条数
    private int totalCells = 0; 
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;} 
    //获取总列数
    public int getTotalCells() {  return totalCells;} 
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }  
     
  /**
   * 读EXCEL文件，获取信息集合
   * @param fielName
   * @return
   */
    public List<Person> getExcelInfo(File mFile) {

        String fileName = mFile.getName();//获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }

            List<Person> userList = createExcel(new FileInputStream(mFile), isExcel2003);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
  /**
   * 根据excel里面的内容读取客户信息
   * @param is 输入流
   * @param isExcel2003 excel是2003还是2007版本
   * @return
   * @throws IOException
   */
    public List<Person> createExcel(InputStream is, boolean isExcel2003) {
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            List<Person> userList = readExcelValue(wb);// 读取Excel里面客户的信息
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
   
  /**
   * 读取Excel里面客户的信息
   * @param wb
   * @return
   */
    private List<Person> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Person> userList = new ArrayList<Person>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            Person user = new Person();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);    
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String name = String.valueOf(cell.getNumericCellValue());
                            user.setpName(name.substring(0, name.length()-2>0?name.length()-2:1));//姓名
                        }else{
                            user.setpName(cell.getStringCellValue());//姓名
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String sex = String.valueOf(cell.getNumericCellValue());
                            user.setpSex(sex.substring(0, sex.length()-2>0?sex.length()-2:1));//性别
                        }else{
                            user.setpSex(cell.getStringCellValue());//性别
                        }
                    }else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String id = String.valueOf(cell.getNumericCellValue());
                            String string=id.substring(0, id.length()-2>0?id.length()-2:1);
                            user.setpIdcard(string);//身份证
                        }else{
                            user.setpIdcard(String.valueOf(cell.getStringCellValue()));//身份证
                        }
                    } else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String age = String.valueOf(cell.getNumericCellValue());
                            String string=age.substring(0, age.length()-2>0?age.length()-2:1);
                            user.setpAge(Integer.parseInt(string));//年龄
                        }else{
                            user.setpAge(Integer.parseInt(cell.getStringCellValue()));//年龄
                        }
                    }else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String birth = String.valueOf(cell.getNumericCellValue());
                            String string=birth.substring(0, birth.length()-2>0?birth.length()-2:1);
                            user.setpBirth(string);//出生日期
                        }else{
                            user.setpBirth(String.valueOf(cell.getStringCellValue()));//出生日期
                        }
                    }else if (c == 5){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String address = String.valueOf(cell.getNumericCellValue());
                            String string=address.substring(0, address.length()-2>0?address.length()-2:1);
                            user.setpAddress(string);//籍贯
                        }else{
                            user.setpAddress(String.valueOf(cell.getStringCellValue()));//籍贯
                        }
                    }else if (c == 6){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String grade = String.valueOf(cell.getNumericCellValue());
                            String string=grade.substring(0, grade.length()-2>0?grade.length()-2:1);
                            user.setpGrade(string);//最高学历
                        }else{
                            user.setpGrade(String.valueOf(cell.getStringCellValue()));//最高学历
                        }
                    }
                }
            }
            // 添加到list
            userList.add(user);
        }
        return userList;
    }
     
    /**
     * 验证EXCEL文件
     * 
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }
     
    // @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xls)$");  
     }  
    
    //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xlsx)$");  
     }  
}
