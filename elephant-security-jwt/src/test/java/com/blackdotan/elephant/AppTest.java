package com.blackdotan.elephant;

import static org.junit.Assert.assertTrue;

import com.auth0.jwt.algorithms.Algorithm;
import com.blackdotan.elephant.security.jwt.JWTAuthenticationV2;
import com.blackdotan.elephant.security.jwt.utils.JWTUtilitiesV2;
import com.blackdotan.elephant.utils.DateUtils;
import com.blackdotan.elephant.utils.JsonUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTAuthenticationV2 authentication = new JWTAuthenticationV2();
        authentication.setIssuer("https://localhost:8080/manager/");
        authentication.setSubject("Supervisor");
        authentication.setAudience(new String[]{"User"});
        authentication.setExp(DateUtils.nowWithOffset(1));
        authentication.setSubordinates(Arrays.asList(1,2,3,4,5,6));
        authentication.setRoles(Arrays.asList("管理员", "员工"));
        authentication.setClient("WEB");
        authentication.setIdentifer(10086L);
        authentication.setKey("Ryan");

        String text = JWTUtilitiesV2.encode(authentication, algorithm);
        System.out.println(text);
        System.out.println(JsonUtils.parserObj2String(JWTUtilitiesV2.decode(text, algorithm)));

    }
}
