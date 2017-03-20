package in.nerd_is.android_showcase.zhihu_daily;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.BeforeClass;
import org.junit.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.IOException;

import in.nerd_is.android_showcase.zhihu_daily.moshi.DateAdapter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Xuqiang ZHENG on 2016/12/5.
 */
public class MoshiDateTimeAdapterUnitTest {

    private static Moshi moshi;

    private static final LocalDate NOW = LocalDate.now();
    private static final int YEAR = NOW.getYear();
    private static final int MONTH = NOW.getMonthValue();
    private static final int DAY = NOW.getDayOfMonth();
    private static String JSON = "{ \"date\": \"" +
            DateTimeFormatter.BASIC_ISO_DATE.format(NOW) + "\" }";

    @BeforeClass
    public static void setupMoshi() {
        moshi = new Moshi.Builder()
                .add(new DateAdapter())
                .build();
    }

    @Test
    public void fromJson_getLocalDate() throws IOException {
        JsonAdapter<Waa> adapter = moshi.adapter(Waa.class);
        LocalDate date = adapter.fromJson(JSON).date;

        assertThat("has right year", date.getYear(), equalTo(YEAR));
        assertThat("has right month", date.getMonthValue(), equalTo(MONTH));
        assertThat("has right day", date.getDayOfMonth(), equalTo(DAY));
    }

    private static class Waa {
        LocalDate date;
    }
}
