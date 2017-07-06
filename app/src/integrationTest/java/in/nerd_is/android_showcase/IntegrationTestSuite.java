package in.nerd_is.android_showcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import in.nerd_is.android_showcase.dribbble.model.repository.remote.DribbbleRemoteRepositoryTest;
import in.nerd_is.android_showcase.hitokoto.HitokotoRemoteRepositoryTest;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyRemoteRepositoryTest;

/**
 * @author Xuqiang ZHENG on 2016/10/22.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        HitokotoRemoteRepositoryTest.class,
        ZhihuDailyRemoteRepositoryTest.class,
        DribbbleRemoteRepositoryTest.class
})
public class IntegrationTestSuite { }
