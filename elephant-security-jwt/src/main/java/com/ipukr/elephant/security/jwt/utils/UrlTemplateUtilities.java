package com.ipukr.elephant.security.jwt.utils;

import com.ipukr.elephant.security.rabc.AccessAuthority;
import org.springframework.web.util.UriTemplate;

import java.util.List;
import java.util.Set;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/8.
 */
public class UrlTemplateUtilities {

    /**
     * @param authorities
     * @param valid
     * @return
     */
    public static boolean valid(List<AccessAuthority> authorities, AccessAuthority valid) {
        boolean bool = false;
        for (AccessAuthority authority : authorities) {
            bool = bool || (
                    authority.getMethod()!=null
                            && authority.getMethod().equals(valid.getMethod())
                            && authority.getUrl()!=null
                            && new UriTemplate(authority.getMethod()).matches(valid.getUrl())
                    );
        }
        return bool;
    }
    /**
     * 判断路径是否在URL列表中
     * @param urls
     * @param path
     * @return
     */
    public static boolean valid(Set<String> urls, String path) {
        for(String url : urls) {
            if (new UriTemplate(url).matches(path)) {
                return true;
            }
        }
        return false;
    }

//    /**
//     * @param types
//     * @param type
//     * @return
//     */
//    public static boolean contain(AbstractClientType[] types, AbstractClientType type) {
//        for (AbstractClientType t : types) {
//            if (t == type) {
//                return true;
//            }
//        }
//        return false;
//    }
}
