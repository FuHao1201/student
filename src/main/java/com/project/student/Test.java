package com.project.student;

import cn.hutool.crypto.SecureUtil;

public class Test {
    public static void main(String[] args) {
        String a = SecureUtil.md5("123456");
        System.out.println(a);
        String b = SecureUtil.md5("123456");
        System.out.println(b);
    }
}
