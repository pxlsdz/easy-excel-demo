package com.sdz.easyexcel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class ToolStatusConvert implements Converter<Byte> {
    @Override
    public Class supportJavaTypeKey() {
        return Byte.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Byte convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        if (stringValue == null) {
            throw new RuntimeException("工器具状态填写为空");
        }

        switch (stringValue) {
            case "在库":
               return 0;
            case "待试验":
                return 1;
            case "超时未试验":
                return 2;
            case "待报废":
                return 3;
            case "出库":
                return 4;
            case "超时未归还":
                return 5;
            case "试验中":
                return 6;
            case "已报废":
                return 7;
            default:
                throw new RuntimeException("工器具状态填写错误");
        }
    }



    @Override
    public CellData convertToExcelData(Byte status, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
