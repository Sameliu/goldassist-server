package com.sutian.goldassist.core;

import java.util.List;
import java.util.Map;

/**
 * @author sutian
 * @email sijin.zsj@alibaba-inc.com
 * @create 2018/11/25 上午10:32
 */
public interface  Spider<T,E> {

    List<T> getSpiderTask();

    String getSpiderResult(String url, Map<String, String> map);

    List<E> parseSpiderResult();

    void finishSpider();
}
