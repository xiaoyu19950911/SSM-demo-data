package com.shxy.dao;

import com.shxy.model.User;

public interface UserMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table user
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer uId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table user
	 *
	 * @mbggenerated
	 */
	int insert(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table user
	 *
	 * @mbggenerated
	 */
	int insertSelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table user
	 *
	 * @mbggenerated
	 */
	User selectByPrimaryKey(Integer uId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table user
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table user
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(User record);

	User findUserByName(String uName);

	String getPasswordByName(String uname);
}