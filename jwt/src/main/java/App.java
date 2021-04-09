import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;


public class  App {
    @Test
    public void testJwt() {
        String token = Jwts.builder()
                .setId("91001")
                .setIssuedAt(new Date())
                .setSubject("libin")
                .claim("cpn", "huawei")
                .claim("tel", "16670155539")
//                .setExpiration(LocalDateTime.now().plusMinutes(3))
                .signWith(SignatureAlgorithm.HS256, "honghu")
                .compact();
        System.out.printf("token is %s \n",token);
       Claims honghu = Jwts.parser().setSigningKey("honghu").parseClaimsJws(token).getBody();
        String id = honghu.getId();
        String subject = honghu.getSubject();
        Date issuedAt = honghu.getIssuedAt();
        Date expiration = honghu.getExpiration();
        String cpn = String.valueOf(honghu.get("cpn"));
        String tel = String.valueOf(honghu.get("tel"));
        System.out.printf("id-%s,subject-%s,issuedAt-%s,expiration-%s,cpn-%s,tel-%s,",
                id,subject,issuedAt,expiration,cpn,tel);


    }
}
