package com.lion.plugin.platform;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PluginLayoutInflater {
    public static LayoutInflater from(Context context, ClassLoader classLoader) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
        LayoutInflater.Factory2 baseFactory2 = layoutInflater.getFactory2();
        LayoutInflater.Factory baseFactory1 = layoutInflater.getFactory();

        PluginInflaterFactory factory;
        if (checkBaseFactory2(baseFactory2)) {
            factory = new PluginInflaterFactory(baseFactory2, classLoader);
            setFactory2(layoutInflater, factory);
        } else if (checkBaseFactory1(baseFactory1)) {
            factory = new PluginInflaterFactory(baseFactory1, classLoader);
            setFactory(layoutInflater, factory);
        }

        return layoutInflater;
    }

    private static boolean checkBaseFactory1(LayoutInflater.Factory baseFactory) {
        if (baseFactory == null) {
            return true;
        }

        if (baseFactory instanceof PluginInflaterFactory) {
            return false;
        }

        return true;
    }

    private static boolean checkBaseFactory2(LayoutInflater.Factory2 baseFactory2) {
        if (baseFactory2 == null || baseFactory2 instanceof PluginInflaterFactory) {
            return false;
        }

        return true;
    }

    private static void setFactory(LayoutInflater layoutInflater, PluginInflaterFactory factory) {
        try {
            Field mFactory = LayoutInflater.class.getDeclaredField("mFactory");
            mFactory.setAccessible(true);
            mFactory.set(layoutInflater, factory);
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    private static void setFactory2(LayoutInflater layoutInflater, PluginInflaterFactory factory) {
        try {
            Field mFactory = LayoutInflater.class.getDeclaredField("mFactory2");
            mFactory.setAccessible(true);
            mFactory.set(layoutInflater, factory);
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }
}

class PluginInflaterFactory implements LayoutInflater.Factory, LayoutInflater.Factory2 {
    private static final String TAG = "PluginInflaterFactory";
    private LayoutInflater.Factory mBaseFactory;
    private LayoutInflater.Factory2 mBaseFactory2;
    private ClassLoader mClassLoader;

    public PluginInflaterFactory(LayoutInflater.Factory base, ClassLoader classLoader) {
        if (null == classLoader) {
            throw new IllegalArgumentException("classLoader is null");
        }
        mBaseFactory = base;
        mClassLoader = classLoader;
    }

    public PluginInflaterFactory(LayoutInflater.Factory2 base2, ClassLoader classLoader) {
        if (null == classLoader) {
            throw new IllegalArgumentException("classLoader is null");
        }

        mBaseFactory2 = base2;
        mClassLoader = classLoader;
    }

    // 4、实现 onCreateView() 方法
    @Override
    public View onCreateView(String s, Context context, AttributeSet attributeSet) {
        if (!s.contains(".")) {
            return null;
        }

        View v = getView(s, context, attributeSet);
        if (v != null) {
            return v;
        }

        if (mBaseFactory != null && !(mBaseFactory instanceof PluginInflaterFactory)) {
            v = mBaseFactory.onCreateView(s, context, attributeSet);
        }

        return v;
    }

    // 4、实现 onCreateView() 方法
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (!name.contains(".")) {
            return null;
        }

        View v = getView(name, context, attrs);
        if (v != null) {
            return v;
        }

        if (mBaseFactory2 != null && !(mBaseFactory2 instanceof PluginInflaterFactory)) {
            v = mBaseFactory2.onCreateView(parent, name, context, attrs);
        }

        return v;
    }

    // 5、自己实现 ClassLoader 创建 View 的过程
    private View getView(String name, Context context, AttributeSet attrs) {
        View v = null;
        try {
            Class<?> clazz = mClassLoader.loadClass(name);
            Constructor c = clazz.getConstructor(Context.class, AttributeSet.class);
            v = (View) c.newInstance(context, attrs);
        } catch (ClassNotFoundException ignored) {

        } catch (NoSuchMethodException ignored) {

        } catch (IllegalAccessException ignored) {

        } catch (InstantiationException ignored) {

        } catch (InvocationTargetException ignored) {

        }

        return v;
    }
}
