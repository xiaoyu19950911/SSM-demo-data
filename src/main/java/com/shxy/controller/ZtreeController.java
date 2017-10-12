package com.shxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shxy.model.Ztree;
import com.shxy.service.ZtreeService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ztree")
public class ZtreeController {
	@Autowired
	ZtreeService ztreeService;

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = null;
		List<Ztree> ls = ztreeService.listall();
		result = new HashMap<String, Object>();
		result.put("b", "参数不合法！");
		result.put("c", ls);
		return result;
	}
	/*
	 * public JSONObject list(HttpServletRequest request) { List<Ztree> ls =
	 * ztreeService.listall(); JSONObject json = new JSONObject();
	 * json.accumulate("a","7"); json.accumulate("b", ls);
	 * System.out.println(json); return json; }
	 */
}
