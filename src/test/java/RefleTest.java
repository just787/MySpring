import com.alibaba.fastjson.JSON;
import com.wdl.spring.model.User;

import java.util.Map;

public class RefleTest {
    public void aVoid() {
        System.out.println("aVoid");
    }

    public void bVoid(String s) {
        System.out.println("bVoid:" + s);
    }

    public void cVoid(int i) {
        System.out.println("cVoid:" + i);
    }


    public String string(String s) {
        System.out.println("string:" + s);
        return s;
    }

    public int anInt(int i) {
        System.out.println("anInt:" + i);
        return i;
    }

    public User user(User user) {
        System.out.println(JSON.toJSON(user));
        return user;
    }

    public Map<String, Integer> map(Map<String, Integer> map) {
        System.out.println(JSON.toJSON(map));
        return map;
    }
}
