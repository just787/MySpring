import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

public class MainTest {
    public static void main(String[] args) {

        Object object = invoke("aVoid", null, null);

        //Object object = invoke("bVoid", new Class<?>[]{String.class}, new Object[]{"abc"});

        //Object object = invoke("cVoid", new Class<?>[]{int.class}, new Object[]{0});

        //Object object = invoke("string", new Class<?>[]{String.class}, new Object[]{null});

        //Object object = invoke("anInt", new Class<?>[]{int.class}, new Object[]{1});

        /*User u = new User();
        u.setUid("wdl");
        Object object = invoke("user", new Class<?>[]{User.class}, new Object[]{null});*/

        /*Map<String, Integer> map = new HashMap<>();
        map.put("test", 1);
        Object object = invoke("map", new Class<?>[]{Map.class}, new Object[]{map});*/

        System.out.println(JSON.toJSON(object));
    }

    /**
     * 通过反射动态执行方法
     *
     * @param mName      方法名
     * @param argsClazzs 参数类型数组
     * @param args       参数数组,注:基本类型的参数勿传其包装类型,因为包装类型若为空时调用方法将报错
     * @return
     */
    public static Object invoke(String mName, Class<?>[] argsClazzs, Object[] args) {
        Object ret = null;
        try {
            Class<?> clazz = RefleTest.class;
            RefleTest refleTest = (RefleTest) clazz.newInstance();
            Method method = clazz.getDeclaredMethod(mName, argsClazzs);
            ret = method.invoke(refleTest, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
