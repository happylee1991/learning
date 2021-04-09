import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    String key;
    long ttl;

    public String  createJwtToken(String id, String subject, Map<String,Object> claimMap) {
        long now = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date(now))
                .setSubject(subject)
                .setExpiration(new Date(now + ttl))
                .signWith(SignatureAlgorithm.HS256, key);

        for (Map.Entry<String, Object> entry : claimMap.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        return jwtBuilder.compact();
    }
    public String  parseJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        String id = claims.getId();
        String subject = claims.getSubject();
        Date issuedAt = claims.getIssuedAt();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("subject", subject);
        map.put("issuedAt", issuedAt);
      claims.get

        return jwtBuilder.compact();
    }
}
