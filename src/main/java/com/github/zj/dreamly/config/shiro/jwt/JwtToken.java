package com.github.zj.dreamly.config.shiro.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 * @author 苍海之南
 * @date 2018/8/30 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {
    /**
     * Token
     */
    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
