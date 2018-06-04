package com.fangzhi.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fangzhi.domain.FzFile;
import com.fangzhi.service.FzFileService;
import com.fangzhi.utils.CommonConstants;
import com.fangzhi.utils.FileConstants;
import com.fangzhi.utils.Tools;
import com.fangzhi.utils.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin  // 允许跨域
@RequestMapping("api/v1/files")
public class FzFileController extends BaseController{
    
    @Autowired
    FzFileService fzFileService;

    public static final String CLASSPATH = Tools.getUploadFilePath();

    @ApiOperation(value="获取文件列表")
    @RequestMapping(value="/all",method=RequestMethod.GET)
    public Map getAll(){
        Map map = new HashMap<>();
        try {
            List<FzFile> filelist = fzFileService.findAll();
            setMap(map, CommonConstants.SUCCESS, "获取列表成功！", filelist);
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED, "获取列表失败！", false);
        }
        return map;
    }

    @ApiOperation(value="删除文件并删除文件信息")
    @RequestMapping(value="/remove/{fileid}",method=RequestMethod.DELETE)
    public Map removeFile(@PathVariable Long fileid){
        Map map = new HashMap<>();
        try {
            FzFile fzFile = fzFileService.findById(fileid);
            if(fzFile == null){
                setMap(map, CommonConstants.FALIED,"不存在该文件！", false);
                return map;
            }
            fzFileService.deleteById(fileid);
            try {             
                new File(CLASSPATH + fzFile.getKey()).delete();
                return setMap(map, CommonConstants.SUCCESS, "删除成功！", true);
            } catch (Exception e) {
                return setMap(map, CommonConstants.FALIED, "删除信息成功！删除文件失败！", false);
            }
        } catch (Exception e) {
            setMap(map, CommonConstants.FALIED,"删除失败！", false);
        }
        return map;
    }

    @ApiOperation(value="保存文件信息", notes="根据FzFile对象保存文件信息")
    @ApiImplicitParam(name = "fzfile", value = "文件信息实体fzfile", required = true, dataType = "FzFile")
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public Map saveFile(@RequestBody FzFile fzfile){
        Map map = new HashMap<>();
        try {
            fzFileService.save(fzfile);
            setMap(map,CommonConstants.SUCCESS,"保存文件信息成功！",true);
        } catch (Exception e) {
            setMap(map,CommonConstants.FALIED,"保存文件信息失败",false);
        }       
        return map;
    }

    @ApiOperation(value="上传文件",notes = "上传的文件")
    @ApiImplicitParams({
        @ApiImplicitParam(name="file",value="上传的文件",required=false,dataType="file",paramType="form")
    })   
    @RequestMapping(value={"/upload"}, method=RequestMethod.POST,consumes="mutipart/*",headers="content-type=mutipart/form-data")
    public Map uploadFile(HttpServletRequest request , @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        Map map = new HashMap<>();
        //。。。 获取用户信息
        Long userid = 1020637302L;

        List<String> errorFiles = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                String fname = multipartFile.getOriginalFilename();
                if (multipartFile.getSize() <= FileConstants.MAX_FILE_SIZE) {
                    String fkey = Tools.getFileKey(fname,userid.toString());
                    String ftype = Tools.isImage(multipartFile.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType();
                    File file = new File(CLASSPATH + fkey);
                    try {
                        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print("name:"+fname+"\tkey:"+fkey+"\ttype:"+ftype);
                    try {
                        FzFile f = new FzFile(fname,fkey,ftype,userid);
                        fzFileService.save(f);
                        setMap(map,CommonConstants.SUCCESS,"上传成功！",true);
                    } catch (Exception e) {
                        setMap(map,CommonConstants.FALIED,"保存成功！入库失败！",false);
                    }
                } else {
                    errorFiles.add(fname);
                }
            }
        } catch (Exception e) {
            setMap(map,CommonConstants.FALIED,"上传失败",errorFiles.toString());
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
}