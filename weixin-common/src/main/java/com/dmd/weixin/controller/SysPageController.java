package com.dmd.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {

	@RequestMapping("login")
	public String login(){
		return "views/login.html";
	}

	@RequestMapping("index")
	public String index(){
		return "views/index.html";
	}

	@RequestMapping("views/{url}.html")
	public String views(@PathVariable("url") String url){
		return url + ".html";
	}

	@RequestMapping("views/sys/{url}.html")
	public String sys(@PathVariable("url") String url){
		return "views/sys/" + url + ".html";
	}

	@RequestMapping("views/common/{url}.html")
	public String common(@PathVariable("url") String url){
		return "views/common/" + url + ".html";
	}

	@RequestMapping("views/cms/{url}.html")
	public String cms(@PathVariable("url") String url){
		return "views/cms/" + url + ".html";
	}

	@RequestMapping("views/shop/{url}.html")
	public String shop(@PathVariable("url") String url){
		return "views/shop/" + url + ".html";
	}

	@RequestMapping("views/generator/{url}.html")
	public String generator(@PathVariable("url") String url){
		return "views/generator/" + url + ".html";
	}
}
