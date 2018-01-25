package com.logic.arithmetic;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    /**
     * public abstract class ResourceBundle extends Object
     * 资源包包含特定于语言环境的对象。当程序需要一个特定于语言环境的资源时（如String），
     * 程序可以从适合当前用户语言环境的资源包中装入它。
     * 以这种方式可以编写很大程度上独立于用户语言环境的程序代码，它将资源包中大部分（如果不是全部）
     * 特定于语言环境的信息隔离开来
     */
    private static final String BUNDLE_NAME = "com.logic.model.Message";

    /**
     * getBundle(String baseName, Locale locale, ClassLoader loader)
     * 使用指定的基本名称、语言环境和类加载器获取资源包。
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
