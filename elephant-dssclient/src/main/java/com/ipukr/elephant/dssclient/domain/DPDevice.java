package com.ipukr.elephant.dssclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class DPDevice {
    private String id;
    private String name;
    private String type;
}
