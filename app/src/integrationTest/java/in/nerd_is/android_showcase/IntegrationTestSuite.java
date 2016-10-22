package in.nerd_is.android_showcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import in.nerd_is.android_showcase.hitokoto.HitokotoRemoteTest;
import in.nerd_is.android_showcase.zhihu_daily.ZhihuDailyRemoteTest;

/**
 * Created by Xuqiang ZHENG on 2016/10/22.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        HitokotoRemoteTest.class,
        ZhihuDailyRemoteTest.class,
})
public class IntegrationTestSuite { }
