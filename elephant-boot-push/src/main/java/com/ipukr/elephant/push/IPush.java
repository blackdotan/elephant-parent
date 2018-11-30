package com.ipukr.elephant.push;

import java.util.List;

/**
 * Created by ryan on 下午10:43.
 */
public interface IPush {
    /**
     * 推送接口
     * @param CID
     * @param text
     * @return
     */
    public boolean push(String CID, String title, String text);

    /**
     * 推送接口
     * @param CIDs
     * @param text
     * @return
     */
    public boolean push(List<String> CIDs, String title, String text);

    /**
     * 推送所有
     * @param text
     * @return
     */
    public boolean push(String title, String text);
}
