package com.ipukr.elephant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/10.
 */
public class JWTUtilitiesTest {
	@Test
	public void test() {
		Date date = DateUtils.nowWithOffset(1);
		Algorithm algorithm = Algorithm.HMAC256("ipukrsecret");
		String token = JWT.create()
				.withIssuer("ebaoan")
				.withSubject("authentication")
				.withJWTId("1")
				.withAudience("E10001")
				.withClaim("username", "E10001")
				.withArrayClaim("roles", new String[]{"admin", "esp"})
				.withArrayClaim("permission", new String[]{
						"/organization/search",
						"/organization",
						"/organization/{id}",
						"/organization/{id}",
						"/organization/{id}/appoint",
						"/organization/{id}/member",
						"/organization/{id}/member",
						"/organization/{id}/forbidden",
						"/organization/{id}/activate",
						"/department/search",
						"/department",
						"/department/{id}",
						"/department/{id}",
						"/department/{id}/appoint",
						"/department/{id}/member",
						"/department/{id}/member",
						"/department/{id}/forbidden",
						"/department/{id}/activate",
						"/post/search",
						"/post",
						"/post/{id}",
						"/post/{id}",
						"/post/{id}/permission/authorise",
						"/post/{id}/organization/authorise",
						"/post/{id}/member",
						"/post/{id}/member",
						"/post/{id}/forbidden",
						"/post/{id}/activate",
						"/employee/search",
						"/employee",
						"/employee/{id}",
						"/employee/{id}/password/reset",
						"/employee/{id}/activate",
						"/employee/{id}/forbidden",
						"/role/search",
						"/role",
						"/role/{id}",
						"/role/{id}",
						"/role/{id}/permission/authorise",
						"/role/{id}/organization/authorise",
						"/role/{id}/member",
						"/role/{id}/member",
						"/role/{id}/forbidden",
						"/role/{id}/activate",
						"/security/guard/approval/search",
						"/security/guard/approval/passed",
						"/security/guard/approval/fail",
						"/security/guard/search",
						"/company/station/search",
						"/company/search",
						"/company/approval/search",
						"/company/approval/passed",
						"/company/approval/fail",
						"/company/approval/archive"
				})
				.withExpiresAt(date)
				.sign(algorithm);
		System.out.println(token);

//		Algorithm algorithm = Algorithm.HMAC256("secret");
		JWTVerifier verifier = JWT.require(algorithm)
//				.withIssuer("ebaoan")
				.build(); //Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		System.out.println(JsonUtils.parserObj2String(jwt));
		String[] roles = jwt.getClaim("roles").asArray(String.class);
		jwt.getExpiresAt();
		System.out.println(JsonUtils.parserObj2String(roles));

	}

	@Test
	public void name() {
		Date date = DateUtils.nowWithOffset(1);
		Algorithm algorithm = Algorithm.HMAC256("ipukrsecret");
		DecodedJWT decode =JWT.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdXRoZW50aWNhdGlvbiIsImF1ZCI6InBvbGljZSIsImlzcyI6Imh0dHBzOi8vbGFicy5pcHVrci5jbiIsImV4cCI6MTU4Mzk0NzI3NywianRpIjoiMSIsImF1dGhlbnRpY2F0aW9uIjoie1wiaWRcIjoxLFwia2V5XCI6XCJFMTAwMDFcIixcInJvbGVzXCI6W1wi566h55CG5ZGYXCIsXCLmma7pgJrkurrlkZhcIl0sXCJ1cmxzXCI6W1wiL3Bvc3Qve2lkfS9vcmdhbml6YXRpb24vYXV0aG9yaXNlXCIsXCIvb3JnYW5pemF0aW9uL3tpZH0vbWVtYmVyXCIsXCIvcG9zdFwiLFwiL2VtcGxveWVlL3tpZH0vYWN0aXZhdGVcIixcIi9yb2xlL3tpZH0vbWVtYmVyXCIsXCIvZGlyZWN0b3J5L3NlYXJjaFwiLFwiL2RhdGEvYWRkXCIsXCIvZGVwYXJ0bWVudFwiLFwiL2VtcGxveWVlL3tpZH0vbW9kaWZ5XCIsXCIvZGVwYXJ0bWVudC97aWR9L2FwcG9pbnRcIixcIi9vcmdhbml6YXRpb24ve2lkfS9mb3JiaWRkZW5cIixcIi9lbXBsb3llZS97aWR9L3Bhc3N3b3JkL3Jlc2V0XCIsXCIvb3JnYW5pemF0aW9uL3tpZH1cIixcIi9yb2xlL3NlYXJjaFwiLFwiL2RhdGEvdXBsb2FkXCIsXCIvZGVwYXJ0bWVudC97aWR9L2FjdGl2YXRlXCIsXCIvcG9zdC97aWR9L2FjdGl2YXRlXCIsXCIvaW5kaWNhdG9yL2NvbXBhcmUvc2VhcmNoXCIsXCIvcG9zdC97aWR9XCIsXCIvZW1wbG95ZWUvc2VhcmNoXCIsXCIvZGVwYXJ0bWVudC97aWR9XCIsXCIvZGVwYXJ0bWVudC97aWR9L21lbWJlclwiLFwiL3Bvc3Qvc2VhcmNoXCIsXCIvZW1wbG95ZWUve2lkfS9mb3JiaWRkZW5cIixcIi9yb2xlL3tpZH0vb3JnYW5pemF0aW9uL2F1dGhvcmlzZVwiLFwiL3Bvc3Qve2lkfS9wZXJtaXNzaW9uL2F1dGhvcmlzZVwiLFwiL3JvbGUve2lkfS9hY3RpdmF0ZVwiLFwiL29yZ2FuaXphdGlvbi9zZWFyY2hcIixcIi9kZXBhcnRtZW50L3NlYXJjaFwiLFwiL2VtcGxveWVlXCIsXCIvcm9sZVwiLFwiL3JvbGUve2lkfVwiLFwiL29yZ2FuaXphdGlvblwiLFwiL3JvbGUve2lkfS9wZXJtaXNzaW9uL2F1dGhvcmlzZVwiLFwiL2VtcGxveWVlL3tpZH1cIixcIi9yb2xlL3tpZH0vZm9yYmlkZGVuXCIsXCIvaW5kdXN0cnkvc2VhcmNoXCIsXCIvcG9zdC97aWR9L2ZvcmJpZGRlblwiLFwiL2luZGljYXRvci9jb21wYXJlL3VwbG9hZFwiLFwiL3Bvc3Qve2lkfS9tZW1iZXJcIixcIi9vcmdhbml6YXRpb24ve2lkfS9hcHBvaW50XCIsXCIvZGVwYXJ0bWVudC97aWR9L2ZvcmJpZGRlblwiLFwiL29yZ2FuaXphdGlvbi97aWR9L2FjdGl2YXRlXCJdfSJ9.XWy5OComJrAn8lS8xlXkIfv_qG-VIVDZ-v8HqRXG0mE");
		Date date1 = decode.getExpiresAt();
		System.out.println(date1);
		System.out.println(JsonUtils.parserObj2String(decode));
		String[] roles = decode.getClaim("roles").asArray(String.class);
		decode.getExpiresAt();
		System.out.println(JsonUtils.parserObj2String(roles));

	}

	@Test
	public void t() {
		System.out.println(SecurityUtils.md5("123456!!"));
		System.out.println(SecurityUtils.md5("!!123456"));
	}
}
