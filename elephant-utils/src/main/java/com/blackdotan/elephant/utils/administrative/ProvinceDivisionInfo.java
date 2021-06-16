package com.blackdotan.elephant.utils.administrative;

import lombok.*;

/**
 * 省份区划信息
 * The type Province division info.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProvinceDivisionInfo {

    /**
     * 名称
     */
    private String mc;

    /**
     * 简称
     */
    private String jc;

    /**
     * 编码
     */
    private String bm;

}
