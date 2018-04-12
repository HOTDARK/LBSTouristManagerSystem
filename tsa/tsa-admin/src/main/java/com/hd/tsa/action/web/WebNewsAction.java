package com.hd.tsa.action.web;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WebNews;
import com.hd.tsa.service.web.WebNewsService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;

/**
 * 网页新闻
 * @author 李良正
 *
 */
@Controller
@RequestMapping("/webNews")
public class WebNewsAction extends BaseAction{

	@Autowired
	private WebNewsService webNewsService;
	
	/**
	 * 跳转网页新闻列表
	 * @return
	 */
	@RequestMapping("/forwardNewsList")
	public ModelAndView forwardNewsList(){
		ModelAndView model = new ModelAndView();
		model.setViewName("web/news_list.jsp");
		return model;
	}
	
	/**
	 * 分页查询网页新闻
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByPage")
	public Pagination<WebNews> findByPage(HttpServletRequest request, WebNews entity){
		Pagination<WebNews> pagination = null;
		try {
			entity.setDeleteFlag(0);
			entity.setSortColumns("publishTime desc");
			pagination = webNewsService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			pagination = new Pagination<>(0, new ArrayList<>());
		}
		return pagination;
	}
	
	/**
	 * 跳转新闻编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditWebNews")
	public ModelAndView forwardEditWebNews(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WebNews entity = webNewsService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("web/news_edit.jsp");
		return model;
	}
	
	/**
	 * 保存网页新闻
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveWebNews")
	public boolean saveWebNews(HttpServletRequest request, WebNews entity){
		try {
			entity.setCreateTime(new Date());
			entity.setCreateUser(getLoginUser(request).getUserAccount());
			entity.setDeleteFlag(0); //删除标识 0--未删除 1--已删除
			entity.setPublishFlag(1); //发布标识 0--未发布 1--已发布
			entity.setPublishTime(new Date());
			webNewsService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改网页新闻
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateWebNews")
	public boolean updateWebNews(HttpServletRequest request, WebNews entity){
		try {
			webNewsService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 跳转查看网页新闻页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardLookWebNews")
	public ModelAndView forwardLookWebNews(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WebNews entity = webNewsService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("web/news_look.jsp");
		return model;
	}
}
