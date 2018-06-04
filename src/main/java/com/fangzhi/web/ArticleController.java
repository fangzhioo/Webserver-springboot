package com.fangzhi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fangzhi.domain.Article;
import com.fangzhi.service.ArticleService;
import com.fangzhi.utils.CommonConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin  // 允许跨域
@RequestMapping("api/v1/articles")
public class ArticleController extends BaseController{

    @Autowired
    ArticleService articleService;

    @ApiOperation(value="获取文章列表",notes="获取文章列表")
    @RequestMapping(value={"/all"},method=RequestMethod.GET)
    public Map getArticleList(){
        Map map = new HashMap<>();
        try {
            List<Article> artlist = articleService.findAll();
            setMap(map, CommonConstants.SUCCESS, "获取列表成功", artlist);
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED, "获取列表失败", false);
        }
        return map;
    }

    @ApiOperation(value = "分页获取文章列表",notes = "分页获取文章列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="当前页 从0开始",required=false,dataType="Integer",paramType="path"),
        @ApiImplicitParam(name="size",value="每页大小 默认20",required=false,dataType="Integer",paramType="path")
    })
    @RequestMapping(value = "/list",method=RequestMethod.GET)
    public Map getUserList(Pageable pageable){
        Map map = new HashMap<>();    
        try {
            List<Article> r = articleService.findByPage(pageable).getContent();
            map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
            map.put(CommonConstants.RESP_MESSAGE, "获取用户列表成功！");
            map.put(CommonConstants.RESULT, r);
        } catch (Exception e) {
            map.put(CommonConstants.RESP_CODE, CommonConstants.FALIED);
            map.put(CommonConstants.RESP_MESSAGE, "获取失败");
            map.put(CommonConstants.RESULT, null);
        }
        return map;
    }

    @ApiOperation(value="获取文章详细",notes="根据文章id获取文章详情")
    @ApiImplicitParam(name = "articleid", value = "文章ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value={"/find/{articleid}"},method=RequestMethod.GET)
    public Map getArticleById(@PathVariable("articleid") Long articleid){
        Map map = new HashMap<>();
        try {
            Article art = articleService.findById(articleid);
            if(art == null){
                return setMap(map, CommonConstants.FALIED, "文章不存在", false);
            }
            setMap(map, CommonConstants.SUCCESS, "获取成功", art);
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED, "获取失败", false);
        }
        return map;
    }

    @ApiOperation(value="提交文章",notes="保存文章信息")
    @ApiImplicitParam(name="article",value="文章实体对象Article",required=true,dataType="Article")
    @RequestMapping(value={"/save"},method=RequestMethod.POST)
    public Map saveArticle(@RequestBody Article article){
        Map map = new HashMap<>();
        try {
            articleService.save(article);
            setMap(map, CommonConstants.SUCCESS, "保存成功", true);
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED, "保存失败", false);
        }
        return map;
    }

    @ApiOperation(value="修改文章信息",notes="修改文章信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name="articleid",value="需要修改文章的id",required=true,dataType="Long",paramType="path"),
        @ApiImplicitParam(name="article",value="修改的文章对象Article",required=true,dataType="Article")
    })
    @RequestMapping(value={"/update/{articleid}"},method=RequestMethod.PUT)
    public Map updateArticle(@PathVariable("articleid") Long articleid,@RequestBody Article article){
        Map map = new HashMap<>();
        try {
            Article oart = articleService.findById(articleid);
            if(oart == null){
                return setMap(map, CommonConstants.FALIED, "指定文章不存在", false);
            }
            // 标题
            if(article.getTitle() != null){
                oart.setTitle(article.getTitle());
            }
            // 文章缩略名
            if(article.getSlug() != null){
                oart.setSlug(article.getSlug());
            }
            // 主题内容
            if(article.getContent() != null){
                oart.setContent(article.getContent());
            }
            // 类型
            if(article.getType() != null){
                oart.setType(article.getType());
            }
            // 状态
            if(article.getStatus() != null){
                oart.setStatus(article.getStatus());
            }
            // 标签
            if(article.getTags() != null){
                oart.setTags(article.getTags());
            }
            // 分类
            if(article.getCategories() != null){
                oart.setCategories(article.getCategories());
            }
            // 是否允许评论
            if(article.getAllowComment() != null && article.getAllowComment() instanceof Boolean){
                oart.setAllowComment(article.getAllowComment());
            }
            // 是否允许Ping
            if(article.getAllowPing() != null && article.getAllowPing() instanceof Boolean){
                oart.setAllowPing(article.getAllowPing());
            }
            // 是否允许聚合
            if(article.getAllowFeed() != null && article.getAllowFeed() instanceof Boolean){
                oart.setAllowFeed(article.getAllowFeed());
            }

            articleService.save(oart);
            setMap(map, CommonConstants.SUCCESS, "修改文章成功", true);
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED, "修改文章失败", false);
        }
        return map;
    }

    @ApiOperation(value="删除文章",notes="删除文章")
    @ApiImplicitParam(name="articleid",value="删除文章的id",required=true,dataType="Long",paramType="path")
    @RequestMapping(value={"/remove/{articleid}"},method=RequestMethod.DELETE)
    public Map removeArticle(@PathVariable Long articleid){
        Map map = new HashMap<>();
        try {
            articleService.deleteById(articleid);
            setMap(map, CommonConstants.SUCCESS, "删除成功", true);
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED, "删除失败", false);
        }
        return map;
    }

    private static Map setMap(Map map,String code,String message,Boolean result) {
        map.put(CommonConstants.RESP_CODE, code);
        map.put(CommonConstants.RESP_MESSAGE, message);
        map.put(CommonConstants.RESULT, result );
        return map;
    }
    private static Map setMap(Map map,String code,String message,String result) {
        map.put(CommonConstants.RESP_CODE, code);
        map.put(CommonConstants.RESP_MESSAGE, message);
        map.put(CommonConstants.RESULT, result );
        return map;
    }
    private static Map setMap(Map map,String code,String message,List result) {
        map.put(CommonConstants.RESP_CODE, code);
        map.put(CommonConstants.RESP_MESSAGE, message);
        map.put(CommonConstants.RESULT, result );
        return map;
    }
    private static Map setMap(Map map,String code,String message,Article result) {
        map.put(CommonConstants.RESP_CODE, code);
        map.put(CommonConstants.RESP_MESSAGE, message);
        map.put(CommonConstants.RESULT, result );
        return map;
    }
    
}
