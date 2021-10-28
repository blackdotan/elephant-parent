package com.blackdotan.elephant.weixin.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class GetWxACodeUnlimitRequest {
    @JsonProperty(value = "scene")
    private String scene;
    @JsonProperty(value = "page")
    private String page;
    @JsonProperty(value = "width")
    private Integer width = 430;
    @JsonProperty(value = "auto_color")
    private Boolean autoColor = false;
    @JsonProperty(value = "line_color")
    private Map lineColor;
    @JsonProperty(value = "is_hyaline")
    private Boolean isHyaline = false;

}
