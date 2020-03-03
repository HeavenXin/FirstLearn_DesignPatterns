package com.isoftstone.vsn.message.config.ids;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author XinLiu
 * @version 1.0
 * <p> xinliucl@isoftstone.com </p >
 */
public class RandomIdGeneratorTest {

    @Test
    public void testGetRandomTextByDigit(){
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String randomText = idGenerator.getRandomTextByDigit(8);
        Assert.assertNotNull(randomText);
        Assert.assertEquals(8,randomText.length());
    }

    @Test
    public void testGetRandomTextByDigit_DigitIsZero(){
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String randomText = idGenerator.getRandomTextByDigit(-1);
        Assert.assertNotNull(randomText);
        Assert.assertEquals("",randomText);
    }

    @Test
    public void testGetLastSubstrSplitByDot(){
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String lastFiled = idGenerator.getLastSubstrSplitByDot("www//11//ss");
        Assert.assertNotNull(lastFiled);
        Assert.assertEquals("ss",lastFiled);

        lastFiled = idGenerator.getLastSubstrSplitByDot("ss");
        Assert.assertNotNull(lastFiled);
        Assert.assertEquals("ss",lastFiled);

        lastFiled = idGenerator.getLastSubstrSplitByDot("www/11/ss");
        Assert.assertNotNull(lastFiled);
        Assert.assertEquals("ss",lastFiled);
    }

    @Test
    public void testGetLastSubstrSplitByDotIsNull(){
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String lastFiled = idGenerator.getLastSubstrSplitByDot("");
        Assert.assertNotNull(lastFiled);
        Assert.assertEquals("",lastFiled);

        lastFiled = idGenerator.getLastSubstrSplitByDot(null);
        Assert.assertNotNull(lastFiled);
        Assert.assertEquals("",lastFiled);
    }
}
