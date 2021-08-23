package com.sdz.easyexcel.contoller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class TestController {
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("downloadFailedUsingJson")
    public void downloadFailedUsingJson(@RequestBody JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            String fileName = jsonObject.getString("fileName");

            JSONArray head = jsonObject.getJSONArray("head");
            List<List<String>> excelHead = new ArrayList<>();
            head.stream().forEach(item -> {
                List<String> headItem = new ArrayList<>();
                headItem.add(String.valueOf(item));
                excelHead.add(headItem);
            });

            JSONArray dataList = jsonObject.getJSONArray("dataList");
            List<List<Object>> excelDataList = new ArrayList<>();
            dataList.stream().forEach(item -> {
                List<Object> dataItem = new ArrayList<>();
                dataItem.add(item);
                excelDataList.add(dataItem);
            });

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream()).autoCloseStream(Boolean.FALSE).head(excelHead).sheet("导出")
                    .doWrite(excelDataList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
}
