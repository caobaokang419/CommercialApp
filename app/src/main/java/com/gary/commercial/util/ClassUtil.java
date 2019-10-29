/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gary.commercial.util;

import android.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiaoruikai on 2018/9/29 12:08 PM.
 */
public class ClassUtil {
    private static final String TAG = ClassUtil.class.getSimpleName();

    private static boolean init = false;

    private static List<Class> classes = new ArrayList<>();

    private static Map<String, Pair<Float, String>> avaliablePatches = new HashMap<>();

    private final static Map<String, List<Class>> mPatchClasses = new HashMap<>();

    /**
     * 用来区分内部类与外部类
     */
    private static String mFilter;

    /**
     * 根据是否加载过类判断是否初始化完毕
     *
     * @return
     */
    public static boolean recordClasses() {
        return classes.isEmpty();
    }


    public static <T> T constructClass(Class<T> targetClass, Object... arguments) {
        Class<?>[] classes;
        if (arguments == null || arguments.length == 0) {
            classes = null;
        } else {
            classes = new Class[arguments.length];
            for (int i = 0; i < arguments.length; i++) {
                classes[i] = arguments[i].getClass();
            }
        }

        return constructClass(targetClass, classes, arguments);
    }

    /**
     * 过滤类名
     *
     * @param className
     * @param filter
     * @return
     */
    private static boolean filterClass(String className, List<String> filter) {
        for (String accept : filter) {
            if (StringUtil.contains(className, accept)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 构造类
     *
     * @param targetClass 待构造类
     * @param arguments   构造函数
     * @return
     */
    public static <T> T constructClass(Class<T> targetClass, Class<?>[] classes, Object[] arguments) {
        // targetClass为空，无法构造
        if (targetClass == null) {
            return null;
        }

        try {
            // 通过参数类查找构造函数
            Constructor<T> constructor = targetClass.getDeclaredConstructor(classes);

            // 对于private的构造函数
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(arguments);
        } catch (InstantiationException e) {
            CLog.e(TAG, "Catch java.lang.InstantiationException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            CLog.e(TAG, "Catch java.lang.IllegalAccessException: " + e.getMessage());
        } catch (InvocationTargetException e) {
            CLog.e(TAG, "Catch java.lang.reflect.InvocationTargetException: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            CLog.e(TAG, "Catch java.lang.NoSuchMethodException: " + e.getMessage());
        }

        return null;
    }

    /**
     * 获取类包含的所有方法
     *
     * @param clazz
     * @return
     */
    public static List<Method> getAllMethods(Class clazz) {
        return getAllMethods(clazz, null);
    }

    /**
     * 获取类包含包含特定注解的所有方法
     *
     * @param clazz      类
     * @param annotation 注解
     * @return
     */
    public static List<Method> getAllMethods(Class clazz, Class<Annotation> annotation) {
        if (clazz == null) {
            CLog.e(TAG, "无法获取空对象的方法");
        }

        List<Method> allMethods = new ArrayList<>();
        Class currentClass = clazz;
        while (currentClass != null) {
            Method[] currentLevelMethods = currentClass.getDeclaredMethods();
            for (Method method : currentLevelMethods) {
                if (annotation != null) {
                    // 查找目标注解
                    Annotation targetAnnotation = method.getAnnotation(annotation);
                    if (targetAnnotation == null) {
                        continue;
                    }
                }

                // 添加该方法
                allMethods.add(method);
            }

            currentClass = currentClass.getSuperclass();
        }

        return allMethods;
    }

    /**
     * 查找子类，过滤掉接口类
     *
     * @param parent     父类
     * @param annotation 包含的注解
     * @return
     */
    public static <T> List<Class<? extends T>> findSubClass(Class<T> parent, Class<? extends Annotation> annotation) {
        return findSubClass(parent, annotation, false);
    }

    /**
     * 根据名称查找目标类
     *
     * @param name
     * @return
     */
    public static Class<?> getClassByName(String name) {
        synchronized (mPatchClasses) {
            // patch部分类
            for (List<Class> patchClasses : mPatchClasses.values()) {
                for (Class childClass : patchClasses) {
                    if (childClass != null && StringUtil.equals(childClass.getName(), name)) {
                        return childClass;
                    }
                }
            }
        }

        for (Class clz : classes) {
            if (clz != null && StringUtil.equals(clz.getName(), name)) {
                return clz;
            }
        }

        return null;
    }


    /**
     * 查找子类
     *
     * @param parent     父类
     * @param annotation 需要包含的注解
     * @return 找到的子类列表
     */
    public static <T> List<Class<? extends T>> findSubClass(Class<T> parent, Class<? extends Annotation> annotation, boolean acceptInterface) {
        if (parent == null) {
            return null;
        }

        List<Class<? extends T>> childrenClasses = new ArrayList<>();
        // 遍历查找子类
        for (Class childClass : classes) {
            if (childClass != null && parent.isAssignableFrom(childClass)) {

                // 如果需要包含特定注解
                if (annotation != null) {
                    Annotation targetAnnotation = childClass.getAnnotation(annotation);
                    if (targetAnnotation == null) {
                        continue;
                    }
                }

                if (!acceptInterface) {
                    if (childClass.isInterface()) {
                        continue;
                    }
                }
                childrenClasses.add(childClass);
            }
        }

        synchronized (mPatchClasses) {
            // patch部分类
            for (List<Class> patchClasses : mPatchClasses.values()) {
                for (Class childClass : patchClasses) {
                    if (childClass != null && parent.isAssignableFrom(childClass)) {

                        // 如果需要包含特定注解
                        if (annotation != null) {
                            Annotation targetAnnotation = childClass.getAnnotation(annotation);
                            if (targetAnnotation == null) {
                                continue;
                            }
                        }

                        childrenClasses.add(childClass);
                    }
                }
            }
        }

        return childrenClasses;
    }

    /**
     * 查找带有包含注解方法的类
     *
     * @param annotation
     * @return
     */
    public static List<Class<?>> findClassWithMethodAnnotation(Class<? extends Annotation> annotation) {
        List<Class<?>> childrenClasses = new ArrayList<>();
        // 遍历查找子类
        for (Class childClass : classes) {
            if (childClass != null) {
                // 不找父类，因为父类也包含待注入方法
                for (Method method : childClass.getDeclaredMethods()) {
                    if (method.getAnnotation(annotation) != null) {
                        childrenClasses.add(childClass);
                        break;
                    }
                }
            }
        }

        // patch部分类
        synchronized (mPatchClasses) {
            for (List<Class> patchClasses : mPatchClasses.values()) {
                for (Class childClass : patchClasses) {
                    if (childClass != null) {
                        // 不找父类，因为父类也包含待注入方法
                        for (Method method : childClass.getDeclaredMethods()) {
                            if (method.getAnnotation(annotation) != null) {
                                childrenClasses.add(childClass);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return childrenClasses;
    }
}
