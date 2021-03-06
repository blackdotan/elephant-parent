package com.blackdotan.elephant.dssclient.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 19:28
 */
@Data
@Builder
public class DPSnapshot {
    // TODO 注释属性
    private DPChannel channel;

    private String data;

    @Builder.Default
    private StringBuffer buffer = new StringBuffer();

    public void append(byte[] data) {
        buffer.append(data);
    }



}
