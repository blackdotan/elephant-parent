package com.ipukr.elephant.common.mybatis.handler;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/6/29.
 */
public class CharactersArrayHandler extends JsonArrayHandler<String> {

	@Override
	public Class<String> getGeneric() {
		return String.class;
	}

}

