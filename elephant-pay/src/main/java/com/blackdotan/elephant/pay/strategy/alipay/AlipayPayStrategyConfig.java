package com.blackdotan.elephant.pay.strategy.alipay;

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
public class AlipayPayStrategyConfig extends PayStrategyConfig {
    private String signType;
    private String charset;
    private String format;
}
