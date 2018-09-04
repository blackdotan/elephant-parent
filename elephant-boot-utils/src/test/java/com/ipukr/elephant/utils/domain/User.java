package com.ipukr.elephant.utils.domain;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/20.
 */
public class User {
    private String nickname;
    private String username;
    private String password;

    public User() {
    }

    private User(Builder builder) {
        setNickname(builder.nickname);
        setUsername(builder.username);
        setPassword(builder.password);
    }

    public static Builder custom() {
        return new Builder();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public static final class Builder {
        private String nickname;
        private String username;
        private String password;

        private Builder() {
        }

        public Builder nickname(String val) {
            nickname = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
