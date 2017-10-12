package com.shxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shxy.dao.ZtreeMapper;
import com.shxy.model.Ztree;
import com.shxy.service.ZtreeService;

@Service
public class ZtreeServiceImpl implements ZtreeService{
@Autowired
ZtreeMapper ztreeMapper;
	public List<Ztree> listall() {
		// TODO Auto-generated method stub
		return ztreeMapper.selectall();
	}

}
