package com.ipukr.elephant.qr;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/22.
 */
public enum QrFormat {
    JPG("jpg"),
    PNG("png");

    private String value;

    QrFormat(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
