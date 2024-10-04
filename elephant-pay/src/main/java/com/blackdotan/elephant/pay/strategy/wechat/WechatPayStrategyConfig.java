package com.blackdotan.elephant.pay.strategy.wechat;

import com.blackdotan.elephant.pay.PayStrategyConfig;
import lombok.*;

/**
 * @author black an
 * @date 2024/10/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WechatPayStrategyConfig extends PayStrategyConfig {
    private String tradeType;
    private String signType;
    private String apiV3Ke;
    private boolean useSandboxEnv = false;
    private boolean ifSaveApiData = false;
}
