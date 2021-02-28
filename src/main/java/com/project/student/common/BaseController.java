package com.project.student.common;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
}

