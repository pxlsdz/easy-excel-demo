package com.sdz.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONArray;
import com.sdz.easyexcel.bean.ProductDTO;
import com.sdz.easyexcel.listener.ProductListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        importExcel();
//        noModelWrite();
        String str = "[\"1\", \"1\",\"1\"]";
        JSONArray jSONArray = JSONArray.parseArray(str);
        jSONArray.stream().forEach(item-> System.out.println(item));

    }

    // 导入测试
    public static void importExcel() {
        String fileName = "D:\\Desktop\\导入.xlsx";

        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(new File(fileName), ProductDTO.class, new ProductListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    // 无模板导出测试
    /**
     * 不创建对象的写
     */
    public static void noModelWrite() {
        // 写法1
        String fileName = Test.class.getResource("/").getPath() + "noModelWrite" + System.currentTimeMillis() + ".xlsx";
        System.out.println(fileName);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("Sheet1").doWrite(dataList());
    }

    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private static List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }

}
