package com.ipukr.elephant.common.dto;

import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/25.
 */
public class AbstractDTOTest {

    @Test
    public void testName() throws Exception {
        User user = new User("ryan", "123");
        UserDTO dto = new UserDTO().parserWithPaginator(user, UserDTO.class);
        System.out.println(dto.toString());
    }
}