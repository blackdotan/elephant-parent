package com.blackdotan.elephant.security.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blackdotan.elephant.security.jwt.JWTAuthenticationV2;
import com.blackdotan.elephant.security.rabc.AccessAuthority;
import com.blackdotan.elephant.utils.JsonUtils;
import com.blackdotan.elephant.utils.SecurityUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author black an
 * @date 2022/11/22
 */
public class JWTUtilitiesV2 {

    /**
     *
     */
    private static final Map<String, JWTAuthenticationV2> cache = new HashMap<String, JWTAuthenticationV2>();

    /**
     * @param authentication
     * @param algorithm
     * @return
     */
    public static String encode(JWTAuthenticationV2 authentication, Algorithm algorithm) {
        String text = JWT.create()
                .withJWTId(authentication.getIdentifer().toString())
                .withKeyId(authentication.getKey())
                .withIssuer(authentication.getIssuer())
                .withSubject(authentication.getSubject())
                .withAudience(authentication.getAudience())
//                .withArrayClaim("roles", authentication.getRoles().toArray(new String[authentication.getRoles().size()]))
//                .withArrayClaim("subordinates", authentication.getSubordinates().toArray(new String[authentication.getSubordinates().size()]))
//                .withClaim("access", JsonUtils.parserObj2String(authentication.getAuthorities()))
                .withExpiresAt(authentication.getExp())
                .sign(algorithm);

        String md5txt = SecurityUtils.md5(text);
        cache.put(md5txt, authentication);

        return text;
    }

    /**
     * @param text
     * @return
     */
    public static JWTAuthenticationV2 decode(String text, Algorithm algorithm) throws IOException {
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(text);
        JWTAuthenticationV2 convert = convert(jwt);
        //
        if (text!=null && !text.equals("")) {
            String md5txt = SecurityUtils.md5(text);
            JWTAuthenticationV2 au = cache.get(md5txt);
            // 从缓存获取
            convert.setAuthorities(au.getAuthorities());
            convert.setRoles(au.getRoles());
            convert.setSubordinates(au.getSubordinates());
        }

        return convert;
    }

    /**
     * @param jwt
     * @return
     * @throws IOException
     */
    private static JWTAuthenticationV2 convert(DecodedJWT jwt) throws IOException {
        String issuer = jwt.getIssuer();
        String subject = jwt.getSubject();
        List<String> audience = jwt.getAudience();
        Date exp = jwt.getExpiresAt();

//        String[] roles = jwt.getClaim("roles").asArray(String.class);
//        String[] subordinates = jwt.getClaim("subordinates").asArray(String.class);
//        String access = jwt.getClaim("access").asString();

        JWTAuthenticationV2 authen = new JWTAuthenticationV2();
        authen.setKey(jwt.getKeyId());
        authen.setIdentifer(Long.valueOf(jwt.getId()));
        authen.setIssuer(issuer);
        authen.setSubject(subject);
        authen.setAudience(audience.toArray(new String[audience.size()]));
        authen.setExp(exp);

//        authen.setRoles(Arrays.asList(roles));
//        authen.setSubordinates(Arrays.asList(subordinates));
//        authen.setAuthorities(JsonUtils.parserString2CollectionWithType(access, List.class, AccessAuthority.class));

        return authen;
    }
}
