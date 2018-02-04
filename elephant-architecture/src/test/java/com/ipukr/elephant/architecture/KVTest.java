package com.ipukr.elephant.architecture;

import com.ipukr.elephant.architecture.factory.Factory;
import org.junit.Test;

import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class KVTest {
    @Test
    public void testKV() throws Exception {
        // 通过配置文件, 获取KV实例
        KV kv = Factory.getInstance().build("prop/prop.properties");
        // 执行KV操作
        kv.add("1", 123);
        kv.add("2", 456);
        Map<String, Object> rst = kv.match(".*");
        if(rst!=null) {
            for (Map.Entry<String, Object> entry : rst.entrySet()) {
                System.out.println(entry.getKey() + "-" + entry.getValue());
            }
        }
    }
}
