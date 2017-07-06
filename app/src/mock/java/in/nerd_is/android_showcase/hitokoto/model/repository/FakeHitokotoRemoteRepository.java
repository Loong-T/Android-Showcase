/*
 *    Copyright 2017 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package in.nerd_is.android_showcase.hitokoto.model.repository;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.moshi.HitokotoDateTimeAdapter;
import io.reactivex.Single;

/**
 * @author Xuqiang ZHENG on 2017/5/5.
 */
public class FakeHitokotoRemoteRepository implements HitokotoDataSource {

    private final List<Hitokoto> hitokotoList;

    public FakeHitokotoRemoteRepository() {
        Moshi moshi = new Moshi.Builder().add(new HitokotoDateTimeAdapter()).build();
        JsonAdapter<Hitokoto> adapter = moshi.adapter(Hitokoto.class);
        hitokotoList = new ArrayList<>(JSON_STRINGS.length);
        try {
            for (String json : JSON_STRINGS) {
                Hitokoto hitokoto = adapter.fromJson(json);
                hitokotoList.add(hitokoto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Single<Hitokoto> getHitokoto() {
        return Single.just(hitokotoList.get(new Random().nextInt(hitokotoList.size())));
    }

    @Override
    public Single<Long> saveHitokoto(Hitokoto hitokoto) {
        throw new UnsupportedOperationException("save function is not support on remote");
    }

    private static String[] JSON_STRINGS = {
            "{ \"id\": \"3753/5574\", \"uid\": \"3753\", \"catname\": \"收藏\", \"text\": \"我们所过的每个平凡的日常，也许就是连续发生的奇迹。\", \"author\": \"aazzssxxdsa123\", \"source\": \"日常\", \"date\": \"2015.10.10 23:25:57\" }",
            "{ \"id\": \"3134/2713\", \"uid\": \"3134\", \"catname\": \"收藏\", \"text\": \"有你在的日子才是我的日常。\", \"author\": \"木风\", \"source\": \"琴浦小姐\", \"date\": \"2014.12.26 02:53:58\" }"
    };
}
