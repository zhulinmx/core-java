package com.basic.design.proxy;

public class UserServiceImpl implements UserService {

    @Override
    public void addUser() {
        System.out.println("添加用户");
    }

    @Override
    public void updateUser(String str) {
        System.out.println("更新用户信息" + str);
    }
}