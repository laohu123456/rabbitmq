package com.server.utils;

import java.util.UUID;

public class GetUuid {

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
