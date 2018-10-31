package fun.peri.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.List;

public class CommUtils {

    public static JSONArray getJSON(List<Object[]> data, String[] com, boolean isContainsNull) {
        JSONArray jsa = new JSONArray();
        Iterator<Object[]> it = data.iterator();
        int i = 0;
        while (it.hasNext()) {
            JSONObject jo = new JSONObject();
            Object m = it.next();
            Object[] oo = null;
            String so = null;
            if (m instanceof String) {
                so = (String) m;
                jo.put(com[0], so);
                jsa.add(jo);
            }
            if (m instanceof Object[]) {
                oo = (Object[]) m;
                i = 0;
                for (Object o : oo) {
                    if (null == o) {
                        if (isContainsNull) {
                            jo.put(com[i], oo[i]);
                        }
                        i++;
                        continue;
                    }
                    jo.put(com[i], oo[i]);
                    i++;
                }
                jsa.add(jo);
            }
        }
        return jsa;
    }

    public static JSONObject getJson(Object[] data, String[] keys, boolean isContainsNull) {
        JSONObject json = new JSONObject();
        int i = 0;
        for (Object o : data) {
            if (null == o) {
                if (isContainsNull) {
                    json.put(keys[i], o);
                    i++;
                    continue;
                }
            }
            json.put(keys[i], o);
            i++;
        }
        return json;
    }
}
