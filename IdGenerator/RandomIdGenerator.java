package com.isoftstone.vsn.message.config.ids;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author XinLiu
 * @version 1.0
 * <p>
 * 这个类是实现了IdGenerator接口的实现类,用于生成一个随机id,id出现重复的几率很小
 * </p >
 */
public class RandomIdGenerator implements IdGenerator {
    //获取到logger对象,方便打印日志
    private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);
    private static final String ASCIIPATTERNTEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final char[] patternBytes = ASCIIPATTERNTEXT.toCharArray();

    private static String HOSTNAME;

    public RandomIdGenerator() {
        getLastFiledOfHostName();
    }

    /**
     * 此函数用于自动生成一个字符串形式的id,并不能保证绝对的唯一性
     * 但是出现重复的可能性很低
     *
     * @return 字符串形式的id
     */
    public String generator() {
        String id = "";
        String randomText = getRandomTextByDigit(8);
        id = String.format("%s-%s-%d", HOSTNAME, randomText, System.currentTimeMillis());
        logger.info("本次获取的id为{}", id);
        return id;
    }


    /**
     * 获取到主机名中的最后一段
     * 如果获取不到,则填充为随机的8位字符
     */
    private void getLastFiledOfHostName() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            if (Strings.isNullOrEmpty(hostName)) {
                HOSTNAME = getLastSubstrSplitByDot(hostName);
            } else {
                HOSTNAME = getRandomTextByDigit(8);
            }
        } catch (UnknownHostException e) {
            HOSTNAME = getRandomTextByDigit(8);
            logger.error("hostname获取不到", e);
        }
    }


    /**
     * 根据传入的位数生成对应位数的字符串
     * 如果传入的位数小于0,则返回空字符串
     *
     * @param digit 位数
     * @return 随机字符串
     */
    @VisibleForTesting
    protected String getRandomTextByDigit(Integer digit) {
        if (digit <= 0) {
            return "";
        }
        int length = ASCIIPATTERNTEXT.length();

        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = digit; i > 0; i--) {
            char patternByte = patternBytes[random.nextInt(length)];
            result.append(patternByte);
        }
        return result.toString();
    }

    /**
     * 根据传入的字符串,获取分隔字符串得到的数字中最后一个
     * 如果传入的为空,则返回空字符串
     *
     * @param text 传入字符串
     */
    @VisibleForTesting
    protected String getLastSubstrSplitByDot(String text) {
        String result = "";
        if (Strings.isNullOrEmpty(text)) {
            return result;
        }
        String[] splitTokens = text.split("//");
        if (splitTokens.length > 0) {
            result = splitTokens[splitTokens.length - 1];
        }
        return result;
    }
}
