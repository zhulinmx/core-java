package com.basic.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 代理模式
 * UserProxy代理类，实现InvocationHandler接口重写invoke方法
 *
 * 很多框架的基础
 * 静态代理、动态代理
 */
public class UserProxy implements InvocationHandler {
    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("---记录日志start---");

        Object res = method.invoke(target, args);

        System.out.println("---记录日志end---");

        return res;
    }

    public static void main(String[] args) {

        UserServiceImpl impl = new UserServiceImpl();
        UserProxy userProxy = new UserProxy(impl);
        UserService userService = (UserService) Proxy.newProxyInstance(
                impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), userProxy
        );
        //会发现在代理之后，方法执行结果和原始执行不一样了
        userService.addUser();
        System.out.println();
        userService.updateUser("：我是皮皮虾");

    }

}