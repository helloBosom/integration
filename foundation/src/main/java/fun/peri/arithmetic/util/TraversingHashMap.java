package fun.peri.arithmetic.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * traver hashMap
 * <p>
 * 映射项（键-值对）。Map.entrySet 方法返回映射的 collection 视图，其中的元素属于此类。
 * 获得映射项引用的唯一 方法是通过此 collection 视图的迭代器来实现。
 * 这些 Map.Entry 对象仅 在迭代期间有效；更确切地讲，如果在迭代器返回项之后修改了底层映射，
 * 则某些映射项的行为是不确定的，除了通过 setValue 在映射项上执行操作之外。
 *
 * @author logic
 */
public class TraversingHashMap {

    void entryHashMap(HashMap<Map.Entry, Map.Entry> hashMap) {
        for (Map.Entry entry : hashMap.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
    }

    /**
     * 返回此映射中包含的映射关系的 Set 视图。
     * 该 set 受映射支持，所以对映射的更改可在此 set 中反映出来，反之亦然。
     * 如果对该 set 进行迭代的同时修改了映射（通过迭代器自己的 remove 操作，
     * 或者通过对迭代器返回的映射项执行 setValue 操作除外），则迭代结果是不确定的。
     * set 支持元素移除，通过 Iterator.remove、Set.remove、removeAll、retainAll 和 clear 操作
     * 可从映射中移除相应的映射关系。它不支持 add 或 addAll 操作。
     *
     * @param hashMap
     */
    void entrySetHashMap(HashMap hashMap) {
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            entry.getKey();
            entry.getValue();
        }
    }

    void keySetHashMap(HashMap<String, String> hashMap) {
        for (String key : hashMap.keySet()) {
            hashMap.get(key);
        }
    }

    void eachEntrySet(HashMap<Map.Entry, Map.Entry> hashMap) {
        Set<Map.Entry<Map.Entry, Map.Entry>> entrySet = hashMap.entrySet();
        for (Map.Entry entry : entrySet) {
            entry.getKey();
            entry.getValue();
        }
    }
}
