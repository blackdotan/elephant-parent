package com.ipukr.elephant.payment.domain.ali;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class ExtUserInfo {
    /**
     * 姓名
     * 注： need_check_info=T时该参数才有效
     */
    @JsonProperty(value="name")
    private String name;
    /**
     * 手机号
     * 注：该参数暂不校验
     */
    @JsonProperty(value="mobile")
    private String mobile;
    /**
     * 身份证：IDENTITY_CARD、
     * 护照：PASSPORT、
     * 军官证：OFFICER_CARD、
     * 士兵证：SOLDIER_CARD、
     * 户口本：HOKOU等。如有其它类型需要支持，请与蚂蚁金服工作人员联系。
     * 注： need_check_info=T时该参数才有效
     */
    @JsonProperty(value="cert_type")
    private String certType;
    /**
     * 证件号
     * 注：need_check_info=T时该参数才有效
     */
    @JsonProperty(value="cert_no")
    private String certNo;
    /**
     * 允许的最小买家年龄，买家年龄必须大于等于所传数值
     * 注：
     * 1. need_check_info=T时该参数才有效
     * 2. min_age为整数，必须大于等于0
     */
    @JsonProperty(value="min_age")
    private String minAge;
    /**
     * 是否强制校验付款人身份信息
     * T:强制校验，F：不强制
     */
    @JsonProperty(value="fix_buyer")
    private String fixBuyer;
    /**
     * 是否强制校验身份信息
     * T:强制校验，F：不强制
     */
    @JsonProperty(value="need_check_info")
    private String needCheckInfo;

}
