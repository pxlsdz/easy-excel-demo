package com.sdz.easyexcel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class ConstructionTypeConvert implements Converter<Byte> {
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
            throw new RuntimeException("施工类型填写为空");
        }

        switch (stringValue) {
            case "变电施工":
               return 0;
            case "线路施工":
                return 1;
            default:
                throw new RuntimeException("施工类型填写错误");
        }
    }



    @Override
    public CellData convertToExcelData(Byte status, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
