package com.ljy.sso.server;

import entity.SsoUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import store.SsoSessionIdHelper;
import store.SsoTokenLoginHelper;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
