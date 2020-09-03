package com.example.manage_platform.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component // 将组建加载到spring中,可以通过依赖进行自动注入
public class RelfexUtils {

    /**
     * 根据不同的class创建对象
     *
     * @param classes java类的class
     * @param <T>
     * @return 返回传入的类型
     */
    public static <T> Object relfex(Class<?> classes) {
        try {
            // 任何运行在内存中的所有类都是该 Class 类的实例对象，每个 Class 类对象内部都包含了本来的所有信息
            // 反射时先找class
            Class<?> aClass = Class.forName(classes.getName());
            Constructor<?> constructor = aClass.getConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e);
            return null;
        }
    }

    public static <T> Object relfex(String classes) {
        try {
            // 任何运行在内存中的所有类都是该 Class 类的实例对象，每个 Class 类对象内部都包含了本来的所有信息
            // 反射时先找class
            Class<?> aClass = Class.forName(classes);
            // 描述一个类的构造方法，内部包含了构造方法的所有信息，例如参数类型，参数名字，访问修饰符...
            Constructor<?> constructor = aClass.getConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e);
            return null;
        }
    }


    public static <T> Object relfex(String classes, Class<?> classesType1, Class<?> classesType2) {
        try {
            // 任何运行在内存中的所有类都是该 Class 类的实例对象，每个 Class 类对象内部都包含了本来的所有信息
            // 反射时先找class
            Class<?> aClass = Class.forName(classes);
            // 描述一个类的构造方法，内部包含了构造方法的所有信息，例如参数类型，参数名字，访问修饰符...
            Constructor<?> constructor = aClass.getConstructor(classesType1, classesType2);
            return constructor.newInstance("1", "2");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 根据不同的name获取value
     *
     * @param name 需要获取的名称
     * @param o    传入的对象
     * @return
     */
    public static Object getNameValue(String name, Object o) {
        try {
            String firstLetter = name.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + name.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object invoke = method.invoke(o, new Object[]{});
            return invoke;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param name 需要获取value的名称
     * @param o    限制!传进来的实体bean
     * @param <T>
     * @return
     * @see RelfexUtils#getNameValue(String, Object)
     */
    public static <T> Map<String, T> getFieldsInfo(String name, Object o) {
        // 描述一个类的属性，内部包含了该属性的所有信息，例如数据类型，属性名，访问修饰符
        Field[] declaredFields = o.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : declaredFields) {
            // 根据fieldname获取到对应的文件值 作比较之后返回
            if (name.equalsIgnoreCase(field.getName())) {
                String type = field.getType().toString();
                Object nameValue = getNameValue(field.getName(), o);
                map.put(name, nameValue);
            }
        }
        return (Map<String, T>) map;
    }

    /**
     * @param name 需要获取value的名称
     * @param o    限制!传进来的实体bean
     * @return Object
     * s
     * @see java.lang.Class#getFields()  获取当前类的所有方法!会获取继承的父类方法
     * @see java.lang.Class#getDeclaredFields() 获取当前类的所有方法!但是不会获取继承的父类方法
     * @see java.lang.Class#getDeclaredField(String) 获取当前参数的field,但是不会获取继承的父类方法
     */
    public static Object getFielddInfo(String name, Object o) {
        // 描述一个类的属性，内部包含了该属性的所有信息，例如数据类型，属性名，访问修饰符
        Class<?> aClass = o.getClass();
        try {
            Field declaredField = aClass.getDeclaredField(name);
            return getNameValue(declaredField.getName(), o);
        } catch (NoSuchFieldException e) {
            System.out.println(e);
            return null;
        }
    }
}
