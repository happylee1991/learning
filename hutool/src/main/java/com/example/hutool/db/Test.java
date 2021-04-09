package com.example.hutool.db;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.time.LocalDateTime;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        double randomValue;
        String metric="收入";
        while (true) {
            randomValue=random.nextDouble();
            Db.use().insert(
                    Entity.create("grafana_income")
                            .set("metric", metric)
                            .set("value", randomValue*100)
                            .set("upd", LocalDateTime.now())

            );
            Thread.sleep(1000);

        }

    }
}
