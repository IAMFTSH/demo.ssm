package demo.ssm.common;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 邝明山
 * on 2020/10/16 16:23
 */
public class JWT {
    //密匙
    private static final String SECRET = "aaaaaaa";
    //过期时间
    private static final String EXP = "exp";
    //载体
    private static final String PAYLOAD = "payload";

    //加密，传入一个对象和有效期
    public static <T> String sign(T object, long maxAge) {
        try {
            //签名人
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            //加密对象转json
            String jsonString = mapper.writeValueAsString(object);
            //json放入载体
            claims.put(PAYLOAD, jsonString);
            //设置过期时间
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            //签名加密
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static<T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}