package com.ipukr.elephant.dssclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 19:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DPChannel {
    // TODO 注释属性
    private String id;
    private String name;
    private String desc;
    private String status;
    private String channelType;
    private String channelSN;
    private String rights;
    private String alarmType;
    private String alarmLevel;

    private int offset;

    private int nsequence;

}
