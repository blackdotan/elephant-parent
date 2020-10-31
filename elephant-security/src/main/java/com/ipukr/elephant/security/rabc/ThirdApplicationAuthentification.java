package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authentification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThirdApplicationAuthentification extends Authentification<String> {

    /**
     * Application.Access_Key
     */
    private String key;

    /**
     * Application.Access_Secret
     */
    private String secret;

    /**
     * 授权机构（该用户可以访问的机构数据，[Organization.Id...] ）
     */
    private List<String> subordinates = new ArrayList<String>();

    /**
     * 授权角色（该用户持有的机构数据，[Role.Name...]）
     */
    private List<String> roles = new ArrayList<String>();

    /**
     * 授权权限（）
     */
    private List<AccessAuthority> authorities = new ArrayList<AccessAuthority>();

    @Override
    public String getKey() {
        return key;
    }
}
