package com.wentao.ncov;

import com.wentao.ncov.scheduled.InitializerDataFromMongo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NcovApplication.class)
public class InitializerDataFromMongoTest {

    @Resource
    private InitializerDataFromMongo initializerDataFromMongo;

    @Test
    public void name() {
        initializerDataFromMongo.scheduledMethod();
    }
}
