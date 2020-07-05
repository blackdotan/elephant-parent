package com.ipukr.elephant.common.web.http;

import com.ipukr.elephant.utils.JsonUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/4/5.
 */
public class SingletonResponseEntityTest {
	@Test
	public void name() {
		SingletonResponseEntity entity = SingletonResponseEntity.status(HttpStatus.OK).body(new HashMap(){{
			put(1, 2);
		}});
		System.out.println(JsonUtils.parserObj2String(entity));

		SingletonResponseEntity entity2 = SingletonResponseEntity.ok(new HashMap(){{
			put(1, 2);
		}});
		System.out.println(JsonUtils.parserObj2String(entity2));

		SingletonResponseEntity entity3 = SingletonResponseEntity.ok()
				.code(400)
				.success(true).msg("123").data(
				new HashMap(){{
					put(1, 2);
				}}).build();
		System.out.println(JsonUtils.parserObj2String(entity3));

		PaginationResponseWarpper warpper = PaginationResponseWarpper.status(HttpStatus.OK).body(Collections.singletonList(new HashMap() {{
			put(1, 2);
		}}));
		System.out.println(JsonUtils.parserObj2String(warpper));
	}
}
