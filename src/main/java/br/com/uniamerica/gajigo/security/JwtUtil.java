package br.com.uniamerica.gajigo.security;

import br.com.uniamerica.gajigo.entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.stream.Collectors;
//@Component
public class JwtUtil {

    //@Value("${security.jwt.token.secret-key}")
    private String secretKey;
    private Algorithm algorithm;

    private final long acessExpireTime = 10 * 60 * 1000;
    private final long refreshExpireTime = 30 * 60 * 1000;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    private Date getExpiration(long expireTime) {
        return new Date(new Date().getTime() + expireTime);
    }

    public String generateAccessToken(AppUser user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(this.getExpiration(this.acessExpireTime))
                .withIssuedAt(new Date())
                .withClaim("roles",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                )
                .sign(this.algorithm);
    }

    public String generateRefreshToken(AppUser user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(this.getExpiration(this.refreshExpireTime))
                .withIssuedAt(new Date())
                .sign(this.algorithm);
    }


}
