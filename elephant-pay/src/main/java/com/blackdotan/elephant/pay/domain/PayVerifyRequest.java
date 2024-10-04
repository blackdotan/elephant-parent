package com.blackdotan.elephant.pay.domain;

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
public class PayVerifyRequest {
    private String sign;
}
