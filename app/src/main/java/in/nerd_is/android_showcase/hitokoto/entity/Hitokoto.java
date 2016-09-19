package in.nerd_is.android_showcase.hitokoto.entity;

import com.squareup.moshi.Json;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public class Hitokoto {
    public String id;
    public String hitokoto;
    public String type;
    public String from;
    public String creator;
    @Json(name = "cearted_at")
    public Long createdAt;
}
/*
    {
      "id": "504",
      "hitokoto": "多行不义必自毙。",
      "type": "g",
      "from": "左传",
      "creator": "小强",
      "cearted_at": null
    }
 */
