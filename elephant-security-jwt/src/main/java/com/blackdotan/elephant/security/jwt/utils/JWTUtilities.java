package com.blackdotan.elephant.security.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blackdotan.elephant.security.jwt.JWTAuthentication;
import com.blackdotan.elephant.security.jwt.JWTAuthenticationV2;
import com.blackdotan.elephant.security.rabc.AccessAuthority;
import com.blackdotan.elephant.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/10.
 */
@Slf4j
public class JWTUtilities {

//	public static Algorithm algorithm = Algorithm.HMAC256("---");

	/**
	 * @param authentication
	 * @return
	 */
	public static String sign(JWTAuthenticationV2 authentication, Algorithm algorithm) {
		String text = JWT.create()
				.withJWTId(authentication.getIdentifer().toString())
				.withKeyId(authentication.getKey())
				.withIssuer(authentication.getIssuer())
				.withSubject(authentication.getSubject())
				.withAudience(authentication.getAudience())

//				.withClaim("organizationId", authentication.getOrganizationId())
//				.withClaim("organizationName", authentication.getOrganizationName())
//				.withClaim("departmentId", authentication.getDepartmentId())
//				.withClaim("departmentName", authentication.getDepartmentName())
//				.withClaim("administrativeDivisionId", authentication.getAdministrativeDivisionId())

				.withArrayClaim("roles", authentication.getRoles().toArray(new String[authentication.getRoles().size()]))
				.withArrayClaim("subordinates", authentication.getSubordinates().toArray(new String[authentication.getSubordinates().size()]))
				.withClaim("access", JsonUtils.parserObj2String(authentication.getAuthorities()))

				.withExpiresAt(authentication.getExp())
				.sign(algorithm);

		// Cache

		return text;
	}

	/**
	 * @param text
	 * @return
	 */
	public static DecodedJWT decode(String text, Algorithm algorithm) {
		JWTVerifier verifier = JWT.require(algorithm)
				.build();
		DecodedJWT jwt = verifier.verify(text);
		return jwt;
	}

	/**
	 * @param jwt
	 * @return
	 * @throws IOException
	 */
	public static JWTAuthenticationV2 convert(DecodedJWT jwt) throws IOException {
		String issuer = jwt.getIssuer();
		String subject = jwt.getSubject();
		List<String> audience = jwt.getAudience();
		Date exp = jwt.getExpiresAt();

//		// Organization.Id
//		Claim organizationIdClaim =jwt.getClaim("organizationId");
//		Integer organizationId = organizationIdClaim.isNull() ? -1 : organizationIdClaim.asInt();
//
//		// Organization.Name
//		Claim organizationNameClaim =jwt.getClaim("organizationName");
//		String organizationName = organizationNameClaim.isNull() ? "-"  : organizationNameClaim.asString();
//
//		// Department.Id
//		Claim departmentIdClaim =jwt.getClaim("departmentId");
//		Integer departmentId = departmentIdClaim.isNull() ? -1 : departmentIdClaim.asInt();
//
//		// Department.Name
//		Claim departmentNameClaim =jwt.getClaim("departmentName");
//		String departmentName = departmentNameClaim.isNull() ? "-" : departmentNameClaim.asString();
//
//		Claim administrativeDivisionIdClaim =jwt.getClaim("administrativeDivisionId");
//		Integer administrativeDivisionId = administrativeDivisionIdClaim.isNull() ? -1 : administrativeDivisionIdClaim.asInt();

		String[] roles = jwt.getClaim("roles").asArray(String.class);
		String[] subordinates = jwt.getClaim("subordinates").asArray(String.class);
		String access = jwt.getClaim("access").asString();

		JWTAuthenticationV2 authen = new JWTAuthenticationV2();
		authen.setKey(jwt.getKeyId());
		authen.setIdentifer(Long.valueOf(jwt.getId()));
		authen.setIssuer(issuer);
		authen.setSubject(subject);
		authen.setAudience(audience.toArray(new String[audience.size()]));
		authen.setExp(exp);

//		authen.setOrganizationId(organizationId);
//		authen.setOrganizationName(organizationName);
//		authen.setDepartmentId(departmentId);
//		authen.setDepartmentName(departmentName);
//		authen.setAdministrativeDivisionId(administrativeDivisionId);

		authen.setRoles(Arrays.asList(roles));
		authen.setSubordinates(Arrays.asList(subordinates));
		authen.setAuthorities(JsonUtils.parserString2CollectionWithType(access, List.class, AccessAuthority.class));

//		List<String> organizations = Arrays.asList(subordinates);
//		List<String> organizations = new ArrayList<>(subordinates.length+1);
//		organizations.add(organizationId.toString());
//		Collections.addAll(organizations, subordinates);
//		authen.setOrganizations(organizations);

		return authen;
	}
}

