package com.imdadur.student_api;

import com.imdadur.student_api.util.CommonUtil;

public class GenerateRandomUUID {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String uuid = CommonUtil.getUUID();
            System.out.println(uuid);
        }
    }
}
