package com.ipukr.elephant.dssclient.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 19:19
 */
@Data
@Builder
public class DPChannel {
    private String id;
    private String name;
    private String type;
    private int offset;
}
