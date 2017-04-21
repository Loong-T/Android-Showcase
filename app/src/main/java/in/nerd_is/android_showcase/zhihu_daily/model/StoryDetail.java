package in.nerd_is.android_showcase.zhihu_daily.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * @author Xuqiang ZHENG on 2017/3/22.
 */
@AutoValue
public abstract class StoryDetail {
    public abstract long id();

    public abstract int type();

    public abstract String body();

    @Json(name = "image_source")
    public abstract String imageSource();

    public abstract String title();

    public abstract String image();

    @Json(name = "share_url")
    public abstract String shareUrl();

    public abstract List<String> js();

    @Nullable
    public abstract List<Recommender> recommenders();

    @Nullable
    public abstract Section section();

    public abstract List<String> css();

    public String toHtml(boolean nightMode) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append(title()).append("</title>")
                .append("<meta charset=\"UTF-8\"/>")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");

        for (String css : css()) {
            builder.append("<link rel=\"stylesheet\" href=\"")
                    .append(css)
                    .append("\"/>");
        }
        // hide headline
        builder.append("<style>")
                .append(".headline { display: none }")
                .append("</style>");

        for (String js : js()) {
            builder.append("<script src=\"")
                    .append(js)
                    .append("\"/>");
        }

        builder.append("</head>");

        builder.append(nightMode ? "<body class=\"night\">" : "<body>")
                .append(body())
                .append("</body>");

        // add click listener for images
        builder.append("<script type=\"text/javascript\">")
                .append("    var imgs = document.getElementsByClassName('content-image');")
                .append("    for (var i = 0; i< imgs.length; ++i) {")
                .append("        imgs[i].addEventListener('click', function (e) {")
                .append("            activity.startImageViewer(e.srcElement.src);")
                .append("        });")
                .append("    }")
                .append("</script>");

        builder.append("</html>");

        return builder.toString();
    }

    public static JsonAdapter<StoryDetail> jsonAdapter(Moshi moshi) {
        return new AutoValue_StoryDetail.MoshiJsonAdapter(moshi);
    }

    public static StoryDetail create(long id,
                                     int type,
                                     String body,
                                     String imageSource,
                                     String title,
                                     String image,
                                     String shareUrl,
                                     List<String> js,
                                     List<Recommender> recommenders,
                                     Section section,
                                     List<String> css) {
        return new AutoValue_StoryDetail(id, type, body, imageSource, title,
                image, shareUrl, js, recommenders, section, css);
    }

    public static class Recommender {
        public String avatar;
    }

    public static class Section {
        public String thumbnail;
        public long id;
        public String name;
    }
}
