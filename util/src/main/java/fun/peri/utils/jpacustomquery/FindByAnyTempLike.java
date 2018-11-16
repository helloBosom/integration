package fun.peri.utils.jpacustomquery;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;
public class FindByAnyTempLike<T> {

    public Page<T> findByAnyLike(JpaSpecificationExecutor<T> po, JSONObject condition, Pageable pageable){
        Criteria<T> where = new Criteria<T>();
        Set<String> allkey  = condition.keySet();
        for (String key:allkey){
            where.add(Restrictions.like(key,condition.getString(key),true));
        }
        return po.findAll(where,pageable);
    }

    public Long countByAnyLike(JpaSpecificationExecutor<T> po,JSONObject condition){
        Criteria<T> where = new Criteria<T>();
        Set<String> allkey  = condition.keySet();
        for (String key:allkey){
            where.add(Restrictions.like(key,condition.getString(key),true));
        }
        return po.count(where);
    }
}
