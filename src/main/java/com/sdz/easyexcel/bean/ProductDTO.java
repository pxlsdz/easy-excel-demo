package com.sdz.easyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sdz.easyexcel.convert.ConstructionTypeConvert;
import com.sdz.easyexcel.convert.ToolStatusConvert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    /**
     * 物资名称
     */
    private String name;

    /**
     * 物资编号
     */
    private String code;

    /**
     * 规格型号
     */
    private String spec;

    /**
     * 单位id
     */
    private String unitName;


    /**
     * 资产类别id
     */
    private String assetTypeName;


    /**
     * 工具器状态， 0 在库 1 待试验 2 超时未试验 3 待报废 4 出库 5 超时未归还 6试验中 7已报废
     */
    @ExcelProperty(converter = ToolStatusConvert.class)
    private Byte toolStatus;

    /**
     * 状态 0 已关闭 1 已开启
     */

    private Byte status;


    /**
     * 施工类型 0 变电施工 1 线路施工
     */
    @ExcelProperty(converter = ConstructionTypeConvert.class)
    private Byte constructionType;


    /**
     * 是否需要实验， 0 不需要实验 1 需要实验
     */
    private Byte isExperiment;

    /**
     * 实验周期
     */
    private Integer experimentCycle;

    /**
     * 下一次实验时间
     */
    private Date nextExperiment;


    /**
     * 排序降序
     */
    private Integer sorts;

    /**
     * 电子标签码
     */
    private String electronicCode;

}
