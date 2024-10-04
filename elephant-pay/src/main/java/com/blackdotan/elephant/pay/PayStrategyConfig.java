package com.blackdotan.elephant.pay;

import lombok.Data;
import lombok.ToString;

/**
 * @author black an
 * @date 2024/10/4
 */
@Data
@ToString
public class PayStrategyConfig {
    protected String appId;
    protected String mchId;
    protected String mchKey;
    protected String host;
    protected String notifyUrl;
    protected byte[] privateKeyContent;
    protected byte[] privateCertContent;
    protected byte[] thirdPlatformCertContent;
}
