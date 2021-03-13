package com.project.student.common;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.io.File;
import java.util.Map;

/**
 * @Description API接口基类，所有返回json格式数据应该继承该类
 * @Author peter
 * @Date 2019/1/31 10:02 AM
 */
@ResponseBody
public abstract class BaseController{

    protected <T> JsonResult<T> jr(){
        return jr(GlobalConstants.SUCCESS, "");
    }

    protected <T> JsonResult<T> jr(String message){
        return jr(GlobalConstants.SUCCESS, message);
    }

    protected <T> JsonResult<T> jr(T t){
        return jr(GlobalConstants.SUCCESS, "", t);
    }
    protected  <T> JsonResult<T> jr(String code, String message){
        return new JsonResult<>(code, message);
    }
    
    protected  <T> JsonResult<T> jr(String message, T t){
        return jr(GlobalConstants.SUCCESS, message, t);
    }
    
    protected  <T> JsonResult<T> jr(String code, String message, T t){
        return new JsonResult<>(code, message, t);
    }
    protected ModelAndView view() {
		return new ModelAndView();
	}

	protected ModelAndView view(String viewName) {
		return new ModelAndView(viewName);
	}

	protected ModelAndView view(View view) {
		return new ModelAndView(view);
	}

	protected ModelAndView view(String viewName, @Nullable Map<String, ?> model) {
		return new ModelAndView(viewName, model);
	}

	protected ModelAndView view(View view, @Nullable Map<String, ?> model) {
		return new ModelAndView(view, model);
	}

	protected ModelAndView view(String viewName, HttpStatus status) {
		return new ModelAndView(viewName, status);
	}

	protected ModelAndView view(@Nullable String viewName, @Nullable Map<String, ?> model, @Nullable HttpStatus status) {
		return new ModelAndView(viewName, model, status);
	}

	protected ModelAndView view(String viewName, String modelName, Object modelObject) {
		return new ModelAndView(viewName, modelName, modelObject);
	}

	protected ModelAndView view(String viewName, Object modelObject) {
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject(modelObject);
		return modelAndView;
	}

	protected ModelAndView view(Object modelObject) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(modelObject);
		return modelAndView;
	}

	protected ModelAndView view(View view, String modelName, Object modelObject) {
		return new ModelAndView(view, modelName, modelObject);
	}
	
	protected ModelAndView view(View view, Object modelObject) {
		ModelAndView modelAndView = new ModelAndView(view);
		modelAndView.addObject(modelObject);
		return modelAndView;
	}


	/**
	 * 提取上传方法为公共方法
	 * @param uploadDir 上传文件目录
	 * @param file 上传对象
	 * @throws Exception
	 */
	protected void executeUpload(String uploadDir, MultipartFile file) throws Exception
	{
		//文件后缀名
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//上传文件名
		String filename = file.getOriginalFilename();
		//服务器端保存的文件对象
		File serverFile = new File(uploadDir + filename);
		//将上传的文件写入到服务器端文件内
		file.transferTo(serverFile);
	}
}

