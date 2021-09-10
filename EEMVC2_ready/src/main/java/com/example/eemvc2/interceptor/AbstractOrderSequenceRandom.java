/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.interceptor;

import com.sun.istack.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Administrator
 */
@Configuration
public abstract class AbstractOrderSequenceRandom{
    /**
     * 訂單字首
     */
    private static final String ORDERPREFIX = "SPSF";
    /**
     * 時間戳
     */
    private static final String FORMAT = "yyyyMMddHHmmss";
    /**
     * 數字隨機
     */
    public static final String numberChar = "0123456789";

    /**
     * 隨機數字
     */
    public static final int numberFor = 5;

    /**
     * @return
     * @author yugenhai
     */
    @NotNull
    public synchronized String createOrderSnRandom() {
        return AbstractOrderSequenceRandomInner.createOrderSnRandomInner();
    }

    /**
     * 建立訂單號<svsp + yyyy-mm-dd + Random>
     *
     * @author yugenhai
     */
    private final static class AbstractOrderSequenceRandomInner {
        private synchronized static String createOrderSnRandomInner() {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            StringBuffer sb = new StringBuffer();
            Random random = new Random();
            for (int i = 0; i < numberFor; i++) {
                sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
            }
            return ORDERPREFIX + sdf.format(new Date()) + sb.toString();
        }
    }
}
