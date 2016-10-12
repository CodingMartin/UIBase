package com.martin.framework;

import com.martin.framework.utils.DisplayUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String reslut = DisplayUtil.numForPlusW(99800);
        Assert.assertEquals(reslut,"321");
    }
}