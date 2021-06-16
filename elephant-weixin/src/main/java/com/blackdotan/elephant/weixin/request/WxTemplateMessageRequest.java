package com.blackdotan.elephant.weixin.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WxTemplateMessageRequest {

    /**
     * touser : OPENID
     * template_id : TEMPLATE_ID
     * page : index
     * form_id : FORMID
     * data : {"keyword1":{"value":"339208499"},"keyword2":{"value":"2015年01月05日 12:30"},"keyword3":{"value":"粤海喜来登酒店"},"keyword4":{"value":"广州市天河区天河路208号"}}
     * emphasis_keyword : keyword1.DATA
     */

    private String touser;
    @JsonProperty("template_id")
    private String templateId;
    private String page;
    @JsonProperty("form_id")
    private String formId;
    private Map data;
    @JsonProperty("emphasis_keyword")
    private String emphasis;

    public final static Builder custom() {
        return new Builder();
    }

    private WxTemplateMessageRequest(Builder builder) {
        setTouser(builder.touser);
        setTemplateId(builder.templateId);
        setPage(builder.page);
        setFormId(builder.formId);
        setData(builder.data);
        setEmphasis(builder.emphasis);
    }


    public static final class Builder {
        private String touser;
        private String templateId;
        private String page;
        private String formId;
        private Map data;
        private String emphasis;

        public Builder() {
        }

        public Builder touser(String val) {
            touser = val;
            return this;
        }

        public Builder templateId(String val) {
            templateId = val;
            return this;
        }

        public Builder page(String val) {
            page = val;
            return this;
        }

        public Builder formId(String val) {
            formId = val;
            return this;
        }

        public Builder data(Map val) {
            data = val;
            return this;
        }

        public Builder emphasis(String val) {
            emphasis = val;
            return this;
        }

        public WxTemplateMessageRequest build() {
            return new WxTemplateMessageRequest(this);
        }
    }
}
