package com.yjx;

import com.yjx.service.ProductService;
import com.yjx.service.ProductTypeService;
import com.yjx.service.UserService;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class FrontMainTests {
    @Autowired
    UserService userService;
    @Resource
    ProductTypeService productTypeService;
    @Resource
    ProductService productService;

    @Test
    void test() {
        System.out.println(userService.list());

    }
    @Test
    void test1(){
        System.out.println(productTypeService.list());
    }
    @Test
    void test2(){
        System.out.println(productService.topN(7));
    }
    private String secrect ="aksASDFASD!@#$!@##$!ajsdkj91834asjdf!@#$!@#kasdf";

    @Test
    void testJWTEncode(){

        JwtBuilder jwtBuilder = Jwts.builder();

        //头部
        jwtBuilder.setHeaderParam("alg","HS256");
        jwtBuilder.setHeaderParam("typ","JWT");


        //载荷
        jwtBuilder.setIssuer("ShoppingSystem");
        jwtBuilder.setSubject("Nation");
        //过期时间
        Date expireDate = new Date(  new Date().getTime() + 60*1000 );
        jwtBuilder.setExpiration(expireDate);
        //增加自定义信息
        jwtBuilder.claim("username","Monkey");
        jwtBuilder.claim("age","666");


        //签名
        jwtBuilder.signWith(SignatureAlgorithm.HS256,secrect);


        String token = jwtBuilder.compact();

        System.out.println(token);
    }
    @Test
    void testParser(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJTaG9wcGluZ1N5c3RlbSIsInN1YiI6Ik5hdGlvbiIsImV4cCI6MTY3OTMxMTU3NCwidXNlcm5hbWUiOiJNb25rZXkiLCJhZ2UiOiI2NjYifQ.ktDfHK_v_CLOI8akIEfpFjQCSIybmF3wSNTbJ5IsUgc";

        JwtParser parser = Jwts.parser();
        // ExpiredJwtException
        Jws<Claims> claimsJws = parser.setSigningKey(secrect).parseClaimsJws(token);
        System.out.println(claimsJws);
        System.out.println(claimsJws.getHeader());
        System.out.println(claimsJws.getBody());
        System.out.println(claimsJws.getSignature());

        Claims body = claimsJws.getBody();
        System.out.println( body.getIssuer() );
        System.out.println( body.getSubject() );
        System.out.println( body.getExpiration() );
        System.out.println( body.get("username") );
        System.out.println( body.get("age") );

    }
}
