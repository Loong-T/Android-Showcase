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

package in.nerd_is.android_showcase.zhihu_daily.model.repository.remote;

import com.github.javafaker.Faker;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.Story;
import in.nerd_is.android_showcase.zhihu_daily.model.StoryDetail;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import io.reactivex.Single;

import static in.nerd_is.android_showcase.utils.CommonUtils.listOf;

/**
 * @author Xuqiang ZHENG on 2017/5/5.
 */
public class FakeZhihuDailyRemoteRepository implements ZhihuDailyDataSource {

    private final Faker faker;

    public FakeZhihuDailyRemoteRepository() {
        faker = new Faker();
    }

    @Override
    public Single<List<?>> getLatestNews() {
        List<Object> list = new ArrayList<>();
        list.add(Date.create(LocalDate.now()));
        list.addAll(generateStories(10));
        return Single.just(list);
    }

    @Override
    public Single<List<?>> getNewsBefore(Date date) {
        List<Object> list = new ArrayList<>();
        LocalDate localDate = date.date().minus(1, ChronoUnit.DAYS);

        list.add(Date.create(localDate));
        list.addAll(generateStories(10));

        return Single.just(list);
    }

    @Override
    public Single<StoryDetail> getNewsDetail(long id) {
        return Single.just(generateDetail());
    }

    private Story generateStory() {
        return Story.FACTORY.creator.create(
                faker.number().randomNumber(),
                0,
                faker.book().title(),
                faker.internet().image(),
                listOf(faker.internet().image()),
                false,
                LocalDate.now()
        );
    }

    private List<Story> generateStories(int num) {
        List<Story> stories = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            stories.add(generateStory());
        }
        return stories;
    }

    private StoryDetail generateDetail() {
        return StoryDetail.create(
                faker.number().randomNumber(),
                0,
                DETAIL_BODY,
                faker.app().name(),
                faker.book().title(),
                faker.internet().image(),
                faker.internet().url(),
                Collections.emptyList(),
                null,
                null,
                listOf(DETAIL_CSS)
        );
    }

    private static final String DETAIL_BODY = "\"<div class=\\\"main-wrap content-wrap\\\">\\n<div class=\\\"headline\\\">\\n\\n<div class=\\\"img-place-holder\\\"></div>\\n\\n\\n\\n</div>\\n\\n<div class=\\\"content-inner\\\">\\n\\n\\n\\n\\n<div class=\\\"question\\\">\\n<h2 class=\\\"question-title\\\">碎片化阅读的危害是什么？</h2>\\n\\n<div class=\\\"answer\\\">\\n\\n<div class=\\\"meta\\\">\\n<img class=\\\"avatar\\\" src=\\\"http://pic1.zhimg.com/v2-f169acc068f6a09ff0e60d2743961624_is.jpg\\\">\\n<span class=\\\"author\\\">采铜，</span><span class=\\\"bio\\\">5.25 知乎 Live 开讲</span>\\n</div>\\n\\n<div class=\\\"content\\\">\\n<p>讲到碎片化阅读，今天几乎每个人都有体会，甚至是很多人的痛点。但是对于怎样才算碎片化阅读，碎在哪个方面，碎到何种程度，不同人的观感可能是不同的，也难以有统一的认识。于是我想尽可能把与&ldquo;碎片化阅读&rdquo;相关的现象和症状罗列完全，这样大家自如就知道它的危害在哪里，然后再看有什么转机。</p>\\r\\n<p><strong>1. 时间碎化</strong></p>\\r\\n<p>据说现代人每天打开手机的次数有近两百次，也就是时间被切得很碎。古人读书可以一口气从早上读到晚上，而在今天这就非常奢侈了。假设我们每天总共花一个小时来读书，但这一个小时又被切成了十二份，也就是每份大概五分钟来读书，那意味着什么呢？那意味着，在这五分钟里的前一两分钟你可能都在回忆上次阅读的内容或者重读之前的内容，好把思路给接上，而当你刚刚接上以后不久，可能就要被打断。这就会造成至少两个后果：a）效率低下；2）难以对阅读材料构建整体性的理解。前者尚可以通过增加时间投入来弥补，而后者则可能是致命的。</p>\\r\\n<p><strong>2. 加工浅表</strong></p>\\r\\n<p>碎片化阅读还有一个典型特征就是加工浅表化，讲得更通俗一点就是浅表化的阅读。由于时间的匆忙和急促，我们都没有太大的耐心在手机上去仔仔细细阅读一篇文章，我们常常只是以刷屏替代阅读。但是如果你回想一下，你从小到大在学校里学习的经历，有没有可能是在对学习材料如此大致泛览的条件下就学到真东西的？如果没有的话，那么你现在又怎么能欺骗自信以为可以通过如此肤浅地涉猎就能有所受益呢？</p>\\r\\n<p><strong>3. 涉猎混杂</strong></p>\\r\\n<p>碎片化阅读的另一层含义，是内容的碎片，支离破碎，全无章法。由于信息爆炸和生存压力，很多人会产生信息焦虑，他们急需弄明白什么样的知识是非学不可的，否则就非死不可。但这个问题是没有准确答案的。于是你被各种吆喝声所包围。如果你的学习模式就是这样一种被动承接式的，只是每天重复着消费别人推送给你的、号称很重要的知识，那么你就失去了主动探索式学习的能力，就无法建立一条自己的学习主线，个人知识体系更是无从谈起。</p>\\r\\n<p><strong>4. 拣择失当</strong></p>\\r\\n<p>自媒体时代，太多的人为凑数而写作。在耸动的标题、精致的排版、炫目的配图、写意的分行之下的，是粗糙的见解、传抄的段子、娴熟的话术、自称的干货和连绵不绝的废话。太多这样的信息垃圾，充斥在我们的眼耳之间，甚至被模仿、被鼓励，生生不息。如果你没有起码的信息品味，没有对信息质量进行独立判断的意识，那么你很可能每天都会进食相当数量的此等货色。在食材太差的情况下，再好的厨子也无法做出合格的菜品。所以纵然是碎片式阅读，你也需要对阅读的材料进行严格挑选，不然就干脆做点别的事吧。</p>\\r\\n<p><strong>5. 兴趣随机</strong></p>\\r\\n<p>你感兴趣的到底是什么？这个看似简单的问题恐怕很多人都答不上来。物质和精神产品的空前丰盛让人们眼花缭乱，又不免观花走马。对一件事物的兴趣，迅速燃起，又迅速寂灭。如果你对某件事物有恒久的兴趣，那么不论你是碎片地去弄它还是整块地去弄它，都不至于捣腾得太差。但如果兴趣都是不稳定的，那么碎片化的阅读就更加显得徒劳。况且碎片化的行为会反过来影响你的兴趣形成，因为当你没有较完整的时间去做哪怕深入一点的探索的时候，当你刚刚点燃的兴趣火苗马上被其他事情所打断的时候，你也就不大可能塑造出稳定的兴趣。</p>\\r\\n<p><strong>6. 目标模糊</strong></p>\\r\\n<p>阅读到底要达到什么样的目标？恐怕很多人对这个问题连考虑都没有考虑过。如果以消遣时间为目标，那么玩游戏可能时间磨得更快，如果以提升自我为目标，那么碎片化阅读也并不是上佳的选择。所以，碎片化阅读处于一个不尴不尬的位置，看上去有那么一点用，实则用处又不大，它实现不了什么，但也离不开。</p>\\r\\n<p><strong>7. 功底不足</strong></p>\\r\\n<p>在一个领域里，有些是基础性的知识，有些是进阶性的知识，学校里的教学，一定是按照先易后难、先基础后进阶的顺序展开的。但是当你进入社会以后，原有的学习秩序荡然无存，于是出现了一个非常普遍但大多数人没有意识到的现象：很可能你读到的很多内容因为缺乏基础知识的学习而无法真正理解。比如我发现很多人对心理学既很感兴趣，又存有很多的误解，这是因为他们缺少相关的基础知识，特别是统计学、科学方法论的训练。同理，如果你在经济学、金融学、政治学、法学等方面缺乏训练，那么你也很可能对很多现实中的经济社会相关的讨论一知半解或者误入歧途。互联网上流行的&ldquo;干货&rdquo;式文章或者课程渲染了一种浮躁之气，它们轻易地许诺你只要掌握若干关键性技巧或者观念就可以速成，并且可以在碎片化时间、便携式场景中完成这种速成，就是忽略了学习活动中基础性知识的必要性。</p>\\r\\n<p><strong>8. 输出吃力</strong></p>\\r\\n<p>写作说白了是一种熟能生巧的活动，唯手熟耳。相比于说话，写作的一大优点是可以反复修改。所以对于大多数人来说，一般建议从写作开始输出，写而优则说，然后开课讲学。写作或者其他的输出活动，逼迫你把已知的碎片整合起来，写一篇文章就像完成一个拼图，写一本书就像完成一个硕大的立体拼图。但是很多人会因为对自己写作能力的不自信而对这样的输出退避三舍。尽管他总是在输入，但是对输入缺少必要的检视，没有输出的情况下，他甚至无法判断，他所摄入的信息到底哪些是真的有用的，这样的阅读当然就成效甚微了。</p>\\r\\n<p>说了上面这八种典型的症状，稍微让人有些泄气。那么我们可以怎么做，来改变这一切呢？下面是我的建议：</p>\\r\\n<p><strong>1. 每天留给自己至少一小时的完整阅读时间</strong></p>\\r\\n<p>既然已经知道碎片化阅读的弊端，为什么不从源头做一些改变呢？从时间分配的重新调整开始，给自己至少一小时的完整阅读时间。在这一小时里，你可以尽量让自己进入心流状态，全神贯注，进入书本内部的世界。当如我不是说摒弃碎片式的阅读，而是建议两种阅读方式并存，对于适合教浅程度加工的阅读材料，可以在碎片式环境中完成，对于比较重要和有深度的材料，还是在完整时间段阅读为好。</p>\\r\\n<p><strong>2. 清点自己已有知识的基础性欠缺</strong></p>\\r\\n<p>你家里的衣物、小食、玩具，你电脑里的文档、音乐、视频，尚且需要时不时清点一下，那么你头脑中的知识为什么不需要清点一下呢？其中一个重要的工作是，仔细想一想，对于某个或某几个你想了解的重点领域，你是否缺少的必要的基础知识学习就已经跳过去乱学一气了。如果是这样的话，建议你从这些领域的权威的教科书开始，踏踏实实地学起来。</p>\\r\\n<p><strong>3. 重新筛选订阅源，提高信息筛选的标准</strong></p>\\r\\n<p>把那些粗制滥造的订阅源取关，精选更权威、可信、高品质的信息源，宁缺毋滥，同时开拓新的信息获取渠道。</p>\\r\\n<p><strong>4. 重读</strong></p>\\r\\n<p>与其每天大量收纳低质量的信息，不如反复精度高质量的内容。在重读的过程中，由于已先前阅读经验打底，有回忆做辅助，所以阅读的抗干扰、抗打断能力更强，在移动、碎片式的场景中，也相对能够实现高质量的、深入的阅读。</p>\\r\\n<p><strong>5. 多关注指向&ldquo;知识间关联&rdquo;的信息，例如参考文献、脚注、前言等</strong></p>\\r\\n<p>内容的碎片化的一个成因是，我们没有悉心去探索内容与内容之间、知识与知识之间的可能关联。比如一本好书的作者可能在书的前言部分，告诉你他的这些思想来源于哪些前人的贡献，受益于哪里，那么你就可以从这些陈述出发，去找寻这些可能是同样精彩的作品。把这些关系型信息当作我们阅读过程中的路标和指示牌，我们的知识体系就会在这样一次次穿针引线的过程中建立起来。</p>\\r\\n<p><strong>6. 善用笔记软件，不放过阅读中的点滴收获</strong></p>\\r\\n<p>阅读一本书或者一篇文章，并不一定要以掌握这些对象的全部内涵为目标。很多时候，只要这些内容中有一些片段对我们有用就足够了。所以有可能你在等车的时候刷了一篇文章，全篇来看似乎写得平平，但是里面讲到一个知识点对你来说新鲜又有用，那么你就只要把这个知识点快速地转存起来，特别是放到笔记软件里，那么这几分钟阅读你就没有做无用功。</p>\\r\\n<p><strong>7 找到自己的可长期探索的问题，并进行专题输出</strong></p>\\r\\n<p>通过阅读，通过工作中的实践，还有随时随地的思考，你可能渐渐找到一两个值得自己长期探索的问题。在这些问题的指引下，你的阅读会越来越结构化，越来越成体系，当积累到一定程度后，你还可以进行主题写作，围绕一个主题进行写一些文章，或者找知乎上相关的问题来回答。一开始可以写短一点，几百字都没有关系，然后逐渐再增加篇幅和深度。</p>\\r\\n<p>以上就是我对碎片化阅读的看法和建议。对于阅读的方法和过程，我的总体看法是，它应该是多元的、灵活的、可设计的，碎片化阅读是阅读的一种方式，既不能全盘否定，也不能一味依赖，把它放入自己阅读的整个有机体中，悉心调教，才是上策。我在 5 月 25 日的 Live 中会分享更多关于阅读方法的思考。</p>\\n</div>\\n</div>\\n\\n\\n<div class=\\\"view-more\\\"><a href=\\\"http://www.zhihu.com/question/23169329\\\">查看知乎讨论<span class=\\\"js-question-holder\\\"></span></a></div>\\n\\n</div>\\n\\n\\n</div>\\n</div>\",";
    private static final String DETAIL_CSS = "article,\n" +
            "aside,\n" +
            "details,\n" +
            "figcaption,\n" +
            "figure,\n" +
            "footer,\n" +
            "header,\n" +
            "hgroup,\n" +
            "main,\n" +
            "nav,\n" +
            "section,\n" +
            "summary {\n" +
            "  display: block;\n" +
            "}\n" +
            "audio,\n" +
            "canvas,\n" +
            "video {\n" +
            "  display: inline-block;\n" +
            "}\n" +
            "audio:not([controls]) {\n" +
            "  display: none;\n" +
            "  height: 0;\n" +
            "}\n" +
            "html {\n" +
            "  font-family: sans-serif;\n" +
            "  -webkit-text-size-adjust: 100%;\n" +
            "}\n" +
            "body {\n" +
            "  font-family: 'Helvetica Neue', Helvetica, Arial, Sans-serif;\n" +
            "  background: #fff;\n" +
            "  padding-top: 0;\n" +
            "  margin: 0;\n" +
            "}\n" +
            "a:focus {\n" +
            "  outline: thin dotted;\n" +
            "}\n" +
            "a:active,\n" +
            "a:hover {\n" +
            "  outline: 0;\n" +
            "}\n" +
            "h1 {\n" +
            "  margin: .67em 0;\n" +
            "}\n" +
            "h1,\n" +
            "h2,\n" +
            "h3,\n" +
            "h4,\n" +
            "h5,\n" +
            "h6 {\n" +
            "  font-size: 16px;\n" +
            "}\n" +
            "abbr[title] {\n" +
            "  border-bottom: 1px dotted;\n" +
            "}\n" +
            "hr {\n" +
            "  box-sizing: content-box;\n" +
            "  height: 0;\n" +
            "}\n" +
            "mark {\n" +
            "  background: #ff0;\n" +
            "  color: #000;\n" +
            "}\n" +
            "code,\n" +
            "kbd,\n" +
            "pre,\n" +
            "samp {\n" +
            "  font-family: monospace,serif;\n" +
            "  font-size: 1em;\n" +
            "}\n" +
            "pre {\n" +
            "  white-space: pre-wrap;\n" +
            "}\n" +
            "q {\n" +
            "  quotes: \\201C\\201D\\2018\\2019;\n" +
            "}\n" +
            "small {\n" +
            "  font-size: 80%;\n" +
            "}\n" +
            "sub,\n" +
            "sup {\n" +
            "  font-size: 75%;\n" +
            "  line-height: 0;\n" +
            "  position: relative;\n" +
            "  vertical-align: baseline;\n" +
            "}\n" +
            "sup {\n" +
            "  top: -0.5em;\n" +
            "}\n" +
            "sub {\n" +
            "  bottom: -0.25em;\n" +
            "}\n" +
            "img {\n" +
            "  border: 0;\n" +
            "  vertical-align: middle;\n" +
            "  color: transparent;\n" +
            "  font-size: 0;\n" +
            "}\n" +
            "svg:not(:root) {\n" +
            "  overflow: hidden;\n" +
            "}\n" +
            "figure {\n" +
            "  margin: 0;\n" +
            "}\n" +
            "fieldset {\n" +
            "  border: 1px solid silver;\n" +
            "  margin: 0 2px;\n" +
            "  padding: .35em .625em .75em;\n" +
            "}\n" +
            "legend {\n" +
            "  border: 0;\n" +
            "  padding: 0;\n" +
            "}\n" +
            "table {\n" +
            "  border-collapse: collapse;\n" +
            "  border-spacing: 0;\n" +
            "  overflow: hidden;\n" +
            "}\n" +
            "a {\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            "blockquote {\n" +
            "  border-left: 3px solid #D0E5F2;\n" +
            "  font-style: normal;\n" +
            "  display: block;\n" +
            "  vertical-align: baseline;\n" +
            "  font-size: 100%;\n" +
            "  margin: .5em 0;\n" +
            "  padding: 0 0 0 1em;\n" +
            "}\n" +
            "ul,\n" +
            "ol {\n" +
            "  padding-left: 20px;\n" +
            "}\n" +
            ".main-wrap {\n" +
            "  max-width: 100%;\n" +
            "  min-width: 300px;\n" +
            "  margin: 0 auto;\n" +
            "}\n" +
            ".content-wrap {\n" +
            "  overflow: hidden;\n" +
            "  background-color: #f9f9f9;\n" +
            "}\n" +
            ".content-wrap a {\n" +
            "  word-break: break-all;\n" +
            "}\n" +
            ".headline {\n" +
            "  border-bottom: 4px solid #f6f6f6;\n" +
            "}\n" +
            ".headline-title.onlyheading {\n" +
            "  margin: 20px 0;\n" +
            "}\n" +
            ".headline img {\n" +
            "  max-width: 100%;\n" +
            "  vertical-align: top;\n" +
            "}\n" +
            ".headline-background-link {\n" +
            "  line-height: 2em;\n" +
            "  position: relative;\n" +
            "  display: block;\n" +
            "  padding: 20px 45px 20px 20px !important;\n" +
            "}\n" +
            ".icon-arrow-right {\n" +
            "  position: absolute;\n" +
            "  top: 50%;\n" +
            "  right: 20px;\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/share-icons.png);\n" +
            "  background-repeat: no-repeat;\n" +
            "  display: inline-block;\n" +
            "  vertical-align: middle;\n" +
            "  background-position: -70px -20px;\n" +
            "  width: 10px;\n" +
            "  height: 15px;\n" +
            "  margin-top: -7.5px;\n" +
            "}\n" +
            ".headline-background .heading {\n" +
            "  color: #999;\n" +
            "  font-size: 15px!important;\n" +
            "  margin-bottom: 8px;\n" +
            "  line-height: 1em;\n" +
            "}\n" +
            ".headline-background .heading-content {\n" +
            "  color: #444;\n" +
            "  font-size: 17px!important;\n" +
            "  line-height: 1.2em;\n" +
            "}\n" +
            ".headline-title {\n" +
            "  line-height: 1.2em;\n" +
            "  color: #000;\n" +
            "  font-size: 22px;\n" +
            "  margin: 20px 0 10px;\n" +
            "  padding: 0 20px!important;\n" +
            "  font-weight: bold;\n" +
            "}\n" +
            ".meta {\n" +
            "  white-space: nowrap;\n" +
            "  text-overflow: ellipsis;\n" +
            "  overflow: hidden;\n" +
            "  font-size: 16px;\n" +
            "  color: #b8b8b8;\n" +
            "}\n" +
            ".meta .source-icon {\n" +
            "  width: 20px;\n" +
            "  height: 20px;\n" +
            "  margin-right: 4px;\n" +
            "}\n" +
            ".meta .time {\n" +
            "  float: right;\n" +
            "  margin-top: 2px;\n" +
            "}\n" +
            ".content {\n" +
            "  color: #444;\n" +
            "  line-height: 1.6em;\n" +
            "  font-size: 17px;\n" +
            "  margin: 10px 0 20px;\n" +
            "}\n" +
            ".content img {\n" +
            "  max-width: 100%;\n" +
            "  display: block;\n" +
            "  margin: 30px auto;\n" +
            "}\n" +
            "\n" +
            ".content img + img {\n" +
            "  margin-top: 15px;\n" +
            "}\n" +
            "\n" +
            ".content img[src*=\"zhihu.com/equation\"] {\n" +
            "  display: inline-block;\n" +
            "  margin: 0 3px;\n" +
            "}\n" +
            ".content a {\n" +
            "  color: #259;\n" +
            "}\n" +
            ".content a:hover {\n" +
            "  text-decoration: underline;\n" +
            "}\n" +
            ".view-more {\n" +
            "  margin-bottom: 25px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            ".view-more a {\n" +
            "  font-size: 16px;\n" +
            "  display: inline-block;\n" +
            "  width: 125px;\n" +
            "  height: 30px;\n" +
            "  line-height: 30px;\n" +
            "  background: #f0f0f0;\n" +
            "  color: #B8B8B8;\n" +
            "}\n" +
            ".question {\n" +
            "  overflow: hidden;\n" +
            "  padding: 0 20px!important;\n" +
            "}\n" +
            ".question + .question {\n" +
            "  border-top: 5px solid #f6f6f6;\n" +
            "}\n" +
            ".question-title {\n" +
            "  line-height: 1.4em;\n" +
            "  color: #000;\n" +
            "  font-weight: 700;\n" +
            "  font-size: 18px;\n" +
            "  margin: 20px 0;\n" +
            "}\n" +
            ".meta .author {\n" +
            "  color: #444;\n" +
            "  font-weight: 700;\n" +
            "}\n" +
            ".answer + .answer {\n" +
            "  border-top: 2px solid #f6f6f6;\n" +
            "  padding-top: 20px;\n" +
            "}\n" +
            ".footer {\n" +
            "  text-align: center;\n" +
            "  color: #b8b8b8;\n" +
            "  font-size: 13px;\n" +
            "  padding: 20px 0;\n" +
            "}\n" +
            ".footer a {\n" +
            "  color: #b8b8b8;\n" +
            "}\n" +
            ".question .view-more a {\n" +
            "  width: 100%;\n" +
            "  display: block;\n" +
            "}\n" +
            ".hot-comment {\n" +
            "  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n" +
            "}\n" +
            ".comment-label {\n" +
            "  font-size: 16px;\n" +
            "  color: #333;\n" +
            "  line-height: 1.5em;\n" +
            "  font-weight: 700;\n" +
            "  border-top: 1px solid #eee;\n" +
            "  border-bottom: 1px solid #eee;\n" +
            "  margin: 0;\n" +
            "  padding: 9px 20px;\n" +
            "}\n" +
            ".comment-list {\n" +
            "  margin-bottom: 20px;\n" +
            "}\n" +
            ".comment-item {\n" +
            "  font-size: 15px;\n" +
            "  color: #666;\n" +
            "  border-bottom: 1px solid #eee;\n" +
            "  padding: 15px 20px;\n" +
            "}\n" +
            ".comment-meta {\n" +
            "  position: relative;\n" +
            "  margin-bottom: 10px;\n" +
            "}\n" +
            ".comment-meta .author {\n" +
            "  vertical-align: middle;\n" +
            "  color: #444;\n" +
            "}\n" +
            ".comment-meta .vote {\n" +
            "  position: absolute;\n" +
            "  color: #b8b8b8;\n" +
            "  font-size: 12px;\n" +
            "  right: 0;\n" +
            "}\n" +
            ".night .comment-label {\n" +
            "  color: #b8b8b8;\n" +
            "  border-top: 1px solid #303030;\n" +
            "  border-bottom: 1px solid #303030;\n" +
            "}\n" +
            ".night .comment-item {\n" +
            "  color: #7f7f7f;\n" +
            "  border-bottom: 1px solid #303030;\n" +
            "}\n" +
            ".icon-vote,\n" +
            ".icon-voted {\n" +
            "  background-repeat: no-repeat;\n" +
            "  display: inline-block;\n" +
            "  vertical-align: 0;\n" +
            "  width: 11px;\n" +
            "  height: 12px;\n" +
            "  margin-right: 4px;\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/app/Comment_Vote.png) !important;\n" +
            "}\n" +
            ".icon-voted {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/app/Comment_Voted.png) !important;\n" +
            "}\n" +
            ".night .icon-vote {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/app/Dark_Comment_Vote.png) !important;\n" +
            "}\n" +
            ".img-wrap .headline-title {\n" +
            "  bottom: 5px;\n" +
            "}\n" +
            ".img-wrap .img-source {\n" +
            "  right: 10px!important;\n" +
            "  font-size: 9px;\n" +
            "}\n" +
            ".global-header {\n" +
            "  position: static;\n" +
            "}\n" +
            ".button {\n" +
            "  width: 60px;\n" +
            "}\n" +
            ".button i {\n" +
            "  margin-right: 0;\n" +
            "}\n" +
            ".headline .img-place-holder {\n" +
            "  height: 200px;\n" +
            "}\n" +
            ".from-column {\n" +
            "  width: 280px;\n" +
            "  line-height: 30px;\n" +
            "  height: 30px;\n" +
            "  padding-left: 90px;\n" +
            "  color: #2aacec;\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/News_Column_Entrance.png);\n" +
            "  box-sizing: border-box;\n" +
            "  margin: 0 20px 20px;\n" +
            "}\n" +
            ".from-column:active {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/News_Column_Entrance_Highlight.png);\n" +
            "}\n" +
            ".night .headline {\n" +
            "  border-bottom: 4px solid #303030;\n" +
            "}\n" +
            ".night img {\n" +
            "  -webkit-mask-image: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(0, 0, 0, 0.7)), to(rgba(0, 0, 0, 0.7)));\n" +
            "}\n" +
            "body.night,\n" +
            ".night .content-wrap {\n" +
            "  background: #343434;\n" +
            "}\n" +
            ".night .answer + .answer {\n" +
            "  border-top: 2px solid #303030;\n" +
            "}\n" +
            ".night .question + .question {\n" +
            "  border-top: 4px solid #303030;\n" +
            "}\n" +
            ".night .view-more a {\n" +
            "  background: #292929;\n" +
            "  color: #666;\n" +
            "}\n" +
            ".night .icon-arrow-right {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/share-icons.png);\n" +
            "  background-repeat: no-repeat;\n" +
            "  display: inline-block;\n" +
            "  vertical-align: middle;\n" +
            "  background-position: -70px -35px;\n" +
            "  width: 10px;\n" +
            "  height: 15px;\n" +
            "}\n" +
            ".night blockquote,\n" +
            ".night sup {\n" +
            "  border-left: 3px solid #666;\n" +
            "}\n" +
            ".night .content a {\n" +
            "  color: #698ebf;\n" +
            "}\n" +
            ".night .from-column {\n" +
            "  color: #2b82ac;\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/Dark_News_Column_Entrance.png);\n" +
            "}\n" +
            ".night .from-column:active {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/Dark_News_Column_Entrance_Highlight.png);\n" +
            "}\n" +
            ".large .question-title {\n" +
            "  font-size: 24px;\n" +
            "}\n" +
            ".large .meta {\n" +
            "  font-size: 18px;\n" +
            "}\n" +
            ".large .content {\n" +
            "  font-size: 20px;\n" +
            "}\n" +
            ".large blockquote,\n" +
            ".large sup {\n" +
            "  line-height: 1.6;\n" +
            "}\n" +
            ".meta .meta-item {\n" +
            "  -o-text-overflow: ellipsis;\n" +
            "  width: 39%;\n" +
            "  overflow: hidden;\n" +
            "  white-space: nowrap;\n" +
            "  text-overflow: ellipsis;\n" +
            "  display: inline-block;\n" +
            "  color: #929292;\n" +
            "  margin-right: 7px;\n" +
            "}\n" +
            ".headline .meta {\n" +
            "  white-space: nowrap;\n" +
            "  text-overflow: ellipsis;\n" +
            "  overflow: hidden;\n" +
            "  font-size: 11px;\n" +
            "  color: #b8b8b8;\n" +
            "  margin: 15px 0;\n" +
            "  padding: 0 20px;\n" +
            "}\n" +
            ".headline .meta a,\n" +
            ".headline .meta a:hover {\n" +
            "  padding-left: 1em;\n" +
            "  margin-top: 2px;\n" +
            "  float: right;\n" +
            "  font-size: 11px;\n" +
            "  color: #0066cf;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            ".highlight {\n" +
            "  width: auto;\n" +
            "  overflow: auto;\n" +
            "  word-wrap: normal;\n" +
            "}\n" +
            ".highlight::-webkit-scrollbar {\n" +
            "  width: 6px;\n" +
            "  height: 6px;\n" +
            "}\n" +
            ".highlight code {\n" +
            "  overflow: auto;\n" +
            "}\n" +
            ".highlight::-webkit-scrollbar-thumb:horizontal {\n" +
            "  border-radius: 6px;\n" +
            "  background-color: rgba(0,0,0,.5);\n" +
            "}\n" +
            ".highlight::-webkit-scrollbar-thumb:horizontal:hover {\n" +
            "  background-color: rgba(0,0,0,.6);\n" +
            "}\n" +
            ".highlight pre {\n" +
            "  margin: 0;\n" +
            "  white-space: pre;\n" +
            "}\n" +
            ".highlight .hll {\n" +
            "  background-color: #ffc;\n" +
            "}\n" +
            ".highlight .err {\n" +
            "  color: #a61717;\n" +
            "  background-color: #e3d2d2;\n" +
            "}\n" +
            ".highlight .cp {\n" +
            "  color: #999;\n" +
            "  font-weight: 700;\n" +
            "}\n" +
            ".highlight .cs {\n" +
            "  color: #999;\n" +
            "  font-weight: 700;\n" +
            "  font-style: italic;\n" +
            "}\n" +
            ".highlight .gd {\n" +
            "  color: #000;\n" +
            "  background-color: #fdd;\n" +
            "}\n" +
            ".highlight .gi {\n" +
            "  color: #000;\n" +
            "  background-color: #dfd;\n" +
            "}\n" +
            ".highlight .gu {\n" +
            "  color: #aaa;\n" +
            "}\n" +
            ".highlight .ni {\n" +
            "  color: purple;\n" +
            "}\n" +
            ".highlight .nt {\n" +
            "  color: navy;\n" +
            "}\n" +
            ".highlight .w {\n" +
            "  color: #bbb;\n" +
            "}\n" +
            ".highlight .sr {\n" +
            "  color: olive;\n" +
            "}\n" +
            "[hidden],\n" +
            ".button span {\n" +
            "  display: none;\n" +
            "}\n" +
            "b,\n" +
            "strong,\n" +
            ".highlight .k,\n" +
            ".highlight .o,\n" +
            ".highlight .gs,\n" +
            ".highlight .kc,\n" +
            ".highlight .kd,\n" +
            ".highlight .kn,\n" +
            ".highlight .kp,\n" +
            ".highlight .kr,\n" +
            ".highlight .ow {\n" +
            "  font-weight: 700;\n" +
            "}\n" +
            "dfn,\n" +
            ".highlight .ge {\n" +
            "  font-style: italic;\n" +
            "}\n" +
            ".meta span,\n" +
            ".meta .source {\n" +
            "  vertical-align: middle;\n" +
            "}\n" +
            ".meta .avatar,\n" +
            ".comment-meta .avatar {\n" +
            "  width: 20px;\n" +
            "  height: 20px;\n" +
            "  border-radius: 2px;\n" +
            "  margin-right: 5px;\n" +
            "}\n" +
            ".meta .bio,\n" +
            ".highlight .gh,\n" +
            ".highlight .bp {\n" +
            "  color: #999;\n" +
            "}\n" +
            ".night .comment-meta .author,\n" +
            ".night .content,\n" +
            ".night .meta .author,\n" +
            ".highlight .go {\n" +
            "  color: #888;\n" +
            "}\n" +
            ".night .headline-title,\n" +
            ".night .headline-background .heading-content,\n" +
            ".night .question-title {\n" +
            "  color: #B8B8B8;\n" +
            "}\n" +
            ".highlight .c,\n" +
            ".highlight .cm,\n" +
            ".highlight .c1 {\n" +
            "  color: #998;\n" +
            "  font-style: italic;\n" +
            "}\n" +
            ".highlight .gr,\n" +
            ".highlight .gt {\n" +
            "  color: #a00;\n" +
            "}\n" +
            ".highlight .gp,\n" +
            ".highlight .nn {\n" +
            "  color: #555;\n" +
            "}\n" +
            ".highlight .kt,\n" +
            ".highlight .nc {\n" +
            "  color: #458;\n" +
            "  font-weight: 700;\n" +
            "}\n" +
            ".highlight .m,\n" +
            ".highlight .mf,\n" +
            ".highlight .mh,\n" +
            ".highlight .mi,\n" +
            ".highlight .mo,\n" +
            ".highlight .il {\n" +
            "  color: #099;\n" +
            "}\n" +
            ".highlight .s,\n" +
            ".highlight .sb,\n" +
            ".highlight .sc,\n" +
            ".highlight .sd,\n" +
            ".highlight .s2,\n" +
            ".highlight .se,\n" +
            ".highlight .sh,\n" +
            ".highlight .si,\n" +
            ".highlight .sx,\n" +
            ".highlight .s1,\n" +
            ".highlight .ss {\n" +
            "  color: #d32;\n" +
            "}\n" +
            ".highlight .na,\n" +
            ".highlight .nb,\n" +
            ".highlight .no,\n" +
            ".highlight .nv,\n" +
            ".highlight .vc,\n" +
            ".highlight .vg,\n" +
            ".highlight .vi {\n" +
            "  color: teal;\n" +
            "}\n" +
            ".highlight .ne,\n" +
            ".highlight .nf {\n" +
            "  color: #900;\n" +
            "  font-weight: 700;\n" +
            "}\n" +
            ".answer h1,\n" +
            ".answer h2,\n" +
            ".answer h3,\n" +
            ".answer h4,\n" +
            ".answer h5 {\n" +
            "  font-size: 19px;\n" +
            "}\n" +
            "@media only screen and (-webkit-min-device-pixel-ratio2), only screen and (min-device-pixel-ratio2) {\n" +
            "  .icon-arrow-right {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/share-icons@2x.png);\n" +
            "    -webkit-background-size: 82px 55px;\n" +
            "    background-size: 82px 55px;\n" +
            "  }\n" +
            "  .icon-vote,\n" +
            "  .icon-voted {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/app/Comment_Vote@2x.png) !important;\n" +
            "    background-size: 11px 12px;\n" +
            "  }\n" +
            "  .icon-voted {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/app/Comment_Voted@2x.png) !important;\n" +
            "  }\n" +
            "  .night .icon-vote {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/app/Dark_Comment_Vote@2x.png) !important;\n" +
            "  }\n" +
            "  .from-column {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/News_Column_Entrance@2x.png) !important;\n" +
            "    background-size: 280px 30px;\n" +
            "  }\n" +
            "  .from-column:active {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/News_Column_Entrance_Highlight@2x.png) !important;\n" +
            "  }\n" +
            "  .night .from-column {\n" +
            "    color: #2b82ac;\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/Dark_News_Column_Entrance@2x.png) !important;\n" +
            "  }\n" +
            "  .night .from-column:active {\n" +
            "    background-image: url(http://static.daily.zhihu.com/img/Dark_News_Column_Entrance_Highlight@2x.png) !important;\n" +
            "  }\n" +
            "}\n" +
            ".meta .meta-item {\n" +
            "  width: 39%;\n" +
            "  overflow: hidden;\n" +
            "  white-space: nowrap;\n" +
            "  text-overflow: ellipsis;\n" +
            "  display: inline-block;\n" +
            "  color: #929292;\n" +
            "  margin-right: 7px;\n" +
            "}\n" +
            ".headline .meta {\n" +
            "  white-space: nowrap;\n" +
            "  text-overflow: ellipsis;\n" +
            "  overflow: hidden;\n" +
            "  font-size: 11px;\n" +
            "  color: #b8b8b8;\n" +
            "  margin: 20px 0;\n" +
            "  padding: 0 20px;\n" +
            "}\n" +
            ".headline .meta a,\n" +
            ".headline .meta a:hover {\n" +
            "  margin-top: 2px;\n" +
            "  float: right;\n" +
            "  font-size: 11px;\n" +
            "  color: #0066cf;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            ".answer h1,\n" +
            ".answer h2,\n" +
            ".answer h3,\n" +
            ".answer h4,\n" +
            ".answer h5 {\n" +
            "  font-size: 19px;\n" +
            "}\n" +
            ".origin-source,\n" +
            "a.origin-source:link {\n" +
            "  display: block;\n" +
            "  margin: 25px 0;\n" +
            "  height: 50px;\n" +
            "  overflow: hidden;\n" +
            "  background: #f0f0f0;\n" +
            "  color: #888;\n" +
            "  position: relative;\n" +
            "  -webkit-touch-callout: none;\n" +
            "}\n" +
            ".origin-source .source-logo,\n" +
            "a.origin-source:link .source-logo {\n" +
            "  float: left;\n" +
            "  width: 50px;\n" +
            "  height: 50px;\n" +
            "  margin-right: 10px;\n" +
            "}\n" +
            ".origin-source .text,\n" +
            "a.origin-source:link .text {\n" +
            "  line-height: 50px;\n" +
            "  height: 50px;\n" +
            "  font-size: 13px;\n" +
            "}\n" +
            ".origin-source.with-link .text {\n" +
            "  color: #333;\n" +
            "}\n" +
            ".origin-source.with-link:after {\n" +
            "  display: block;\n" +
            "  position: absolute;\n" +
            "  border-color: transparent transparent transparent #f0f0f0;\n" +
            "  border-width: 7px;\n" +
            "  border-style: solid;\n" +
            "  height: 0;\n" +
            "  width: 0;\n" +
            "  top: 18px;\n" +
            "  right: 4px;\n" +
            "  line-height: 0;\n" +
            "  content: \"\";\n" +
            "}\n" +
            ".origin-source.with-link:before {\n" +
            "  display: block;\n" +
            "  height: 0;\n" +
            "  width: 0;\n" +
            "  position: absolute;\n" +
            "  top: 18px;\n" +
            "  right: 3px;\n" +
            "  border-color: transparent transparent transparent #000;\n" +
            "  border-width: 7px;\n" +
            "  border-style: solid;\n" +
            "  line-height: 0;\n" +
            "  content: \"\";\n" +
            "}\n" +
            ".origin-source-wrap {\n" +
            "  position: relative;\n" +
            "  background: #f0f0f0;\n" +
            "}\n" +
            ".origin-source-wrap .focus-link {\n" +
            "  position: absolute;\n" +
            "  right: 0;\n" +
            "  top: 0;\n" +
            "  width: 45px;\n" +
            "  color: #00a2ed;\n" +
            "  height: 50px;\n" +
            "  display: none;\n" +
            "  text-align: center;\n" +
            "  font-size: 12px;\n" +
            "  -webkit-touch-callout: none;\n" +
            "}\n" +
            ".origin-source-wrap .focus-link .btn-label {\n" +
            "  text-align: center;\n" +
            "  display: block;\n" +
            "  margin-top: 8px;\n" +
            "  border-left: solid 1px #ccc;\n" +
            "  height: 34px;\n" +
            "  line-height: 34px;\n" +
            "}\n" +
            ".origin-source-wrap.unfocused .focus-link {\n" +
            "  display: block;\n" +
            "}\n" +
            ".origin-source-wrap.unfocused .origin-source:before,\n" +
            ".origin-source-wrap.unfocused .origin-source:after {\n" +
            "  display: none;\n" +
            "}\n" +
            ".night .origin-source-wrap {\n" +
            "  background: #292929;\n" +
            "}\n" +
            ".night .origin-source-wrap .focus-link {\n" +
            "  color: #116f9e;\n" +
            "}\n" +
            ".night .origin-source-wrap .btn-label {\n" +
            "  border-left: solid 1px #3f3f3f;\n" +
            "}\n" +
            ".night .origin-source,\n" +
            ".night .origin-source.with-link {\n" +
            "  background: #292929;\n" +
            "  color: #666;\n" +
            "}\n" +
            ".night .origin-source .text,\n" +
            ".night .origin-source.with-link .text {\n" +
            "  color: #666;\n" +
            "}\n" +
            ".night .origin-source.with-link:after {\n" +
            "  border-color: transparent transparent transparent #292929;\n" +
            "}\n" +
            ".night .origin-source.with-link:before {\n" +
            "  border-color: transparent transparent transparent #666;\n" +
            "}\n" +
            "/* ==== */\n" +
            ".question-title {\n" +
            "  color: #494b4d;\n" +
            "}\n" +
            "\n" +
            "blockquote {\n" +
            "  color: #9da3a6;\n" +
            "  border-left: 3px solid #Dfe3e6;\n" +
            "}\n" +
            "\n" +
            ".content a {\n" +
            "  color: #4786b3;\n" +
            "}\n" +
            "\n" +
            ".content {\n" +
            "  font-size: 17px;\n" +
            "  color: #616466;\n" +
            "}\n" +
            "\n" +
            ".content-wrap {\n" +
            "  background: #fff;\n" +
            "}\n" +
            "\n" +
            "hr {\n" +
            "  margin: 30px 0;\n" +
            "  border-top-width: 0;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "p {\n" +
            "  margin: 20px 0 !important;\n" +
            "}\n" +
            "\n" +
            ".dudu-night .content {\n" +
            "  color: #797b80;\n" +
            "}\n" +
            "\n" +
            ".dudu-night hr {\n" +
            "  color: #27282b;\n" +
            "  border-color: #27282b;\n" +
            "}\n" +
            ".dudu-night .meta .author,\n" +
            ".dudu-night .meta .bio {\n" +
            "  color: #555659;\n" +
            "}\n" +
            ".dudu-night .headline-title,\n" +
            ".dudu-night .headline-background .heading-content,\n" +
            ".dudu-night .question-title {\n" +
            "  color: #919499;\n" +
            "}\n" +
            "\n" +
            ".dudu-night .headline {\n" +
            "  border-bottom: none;\n" +
            "}\n" +
            ".dudu-night img {\n" +
            "  -webkit-mask-image: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(0, 0, 0, 0.7)), to(rgba(0, 0, 0, 0.7)));\n" +
            "}\n" +
            "body.dudu-night,\n" +
            ".dudu-night .content-wrap {\n" +
            "  background: #1d1e1f;\n" +
            "}\n" +
            ".dudu-night .answer + .answer {\n" +
            "  border-top: 2px solid #27282b;\n" +
            "}\n" +
            ".dudu-night .question + .question {\n" +
            "  border-top: 4px solid #27282b;\n" +
            "}\n" +
            ".dudu-night .view-more a {\n" +
            "  background: #1d1e1f;\n" +
            "  color: #396280;\n" +
            "}\n" +
            ".dudu-night .icon-arrow-right {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/share-icons.png);\n" +
            "  background-repeat: no-repeat;\n" +
            "  display: inline-block;\n" +
            "  vertical-align: middle;\n" +
            "  background-position: -70px -35px;\n" +
            "  width: 10px;\n" +
            "  height: 15px;\n" +
            "}\n" +
            ".dudu-night blockquote,\n" +
            ".dudu-night sup {\n" +
            "  border-left: 3px solid #2e3033;\n" +
            "  color: #555659;\n" +
            "}\n" +
            ".dudu-night .content a {\n" +
            "  color: #396280;\n" +
            "}\n" +
            "\n" +
            ".dudu-night img {\n" +
            "  opacity: 0.7;\n" +
            "}\n" +
            "\n" +
            ".dudu-night .from-column {\n" +
            "  color: #2b82ac;\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/Dark_News_Column_Entrance.png);\n" +
            "}\n" +
            ".dudu-night .from-column:active {\n" +
            "  background-image: url(http://static.daily.zhihu.com/img/Dark_News_Column_Entrance_Highlight.png);\n" +
            "}\n" +
            "\n" +
            ".dudu .headline {\n" +
            "  border-bottom: none;\n" +
            "}\n" +
            "\n" +
            ".dudu-night .origin-source,\n" +
            ".dudu-night a.origin-source:link {\n" +
            "  background: #222324;\n" +
            "}\n" +
            "\n" +
            ".dudu-night .origin-source.with-link .text {\n" +
            "  color: #797b80;\n" +
            "}\n" +
            ".dudu-night .origin-source.with-link:after {\n" +
            "  border-color: transparent transparent transparent #797b80;\n" +
            "}\n";

}
