package com.zhuoxin.news.utils;

import java.util.List;

/**
 * Created by nh on 2017/1/13.
 */

public class JsonImage {

    /**
     * error : false
     * results : [{"_id":"5877091e421aa93161103df7","createdAt":"2017-01-12T12:42:06.839Z","desc":"另辟蹊径实现Android多渠道打包","publishedAt":"2017-01-13T11:58:16.991Z","source":"chrome","type":"Android","url":"https://yrom.net/blog/2015/05/25/the_other_way_to_package_multi_channel_apks/","used":true,"who":"LHF"},{"_id":"587724fd421aa93161103dfa","createdAt":"2017-01-12T14:41:01.560Z","desc":"[译]Android Application启动流程分析","images":["http://img.gank.io/be9571a9-99d7-4537-a291-0c772ec2f08f"],"publishedAt":"2017-01-13T11:58:16.991Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/a5532ecc8377","used":true,"who":"lxxself"},{"_id":"58774901421aa93161103dff","createdAt":"2017-01-12T17:14:41.747Z","desc":"Android的.so文件、ABI和CPU的关系","publishedAt":"2017-01-13T11:58:16.991Z","source":"chrome","type":"Android","url":"http://blog.csdn.net/xx326664162/article/details/51163905","used":true,"who":"LHF"},{"_id":"587758d4421aa9315ea7993a","createdAt":"2017-01-12T18:22:12.444Z","desc":"Android 加速构建方案对比","publishedAt":"2017-01-13T11:58:16.991Z","source":"chrome","type":"Android","url":"https://www.zhihu.com/question/36892290/answer/125762516","used":true,"who":"lxxself"},{"_id":"587848b6421aa9119a6ca669","createdAt":"2017-01-13T11:25:42.7Z","desc":"支持给图片加锚点的 Android 组件","images":["http://img.gank.io/82259ebd-f80b-4101-955f-ce3b37d942c3"],"publishedAt":"2017-01-13T11:58:16.991Z","source":"chrome","type":"Android","url":"https://github.com/jcodeing/AnchorImageView","used":true,"who":"代码家"},{"_id":"5875e66c421aa9315ea79931","createdAt":"2017-01-11T16:01:48.998Z","desc":"在Chrome DevTools内查看Android设备上所有HTTP(S)流量","images":["http://img.gank.io/b33cc839-18de-48e0-8644-089e6986609a","http://img.gank.io/a4ce3373-2f20-4fe5-bbcf-9f6dc3847591"],"publishedAt":"2017-01-12T11:30:59.369Z","source":"web","type":"Android","url":"https://github.com/misakuo/Dream-Catcher","used":true,"who":"moxun"},{"_id":"5876e940421aa93161103df1","createdAt":"2017-01-12T10:26:08.237Z","desc":"类似 Facebook 加载时，条目的闪烁效果，酷酷的。","images":["http://img.gank.io/985a1011-632f-40c1-9ca5-1bd9cb17a69f","http://img.gank.io/c5c916fc-b391-40ec-9962-df8588f23cba"],"publishedAt":"2017-01-12T11:30:59.369Z","source":"chrome","type":"Android","url":"https://github.com/sharish/ShimmerRecyclerView","used":true,"who":"代码家"},{"_id":"5872fb61421aa9315bfbe843","createdAt":"2017-01-09T10:54:25.659Z","desc":"android多图选择 图片/视频 单选or多选，以及视频录制。","images":["http://img.gank.io/eafb5329-c252-42f3-a8cc-48f6b247c9f8","http://img.gank.io/f58b7772-ce21-4875-b709-2f21ba2d2c3f"],"publishedAt":"2017-01-11T12:05:20.787Z","source":"web","type":"Android","url":"https://github.com/LuckSiege/PictureSelector","used":true,"who":null},{"_id":"587469c5421aa9316407fb95","createdAt":"2017-01-10T12:57:41.909Z","desc":"top-5-android-libraries-january-2017","publishedAt":"2017-01-11T12:05:20.787Z","source":"web","type":"Android","url":"https://medium.cobeisfresh.com/top-5-android-libraries-january-2017-53e217783fc9","used":true,"who":"kg"},{"_id":"58759363421aa93161103ddd","createdAt":"2017-01-11T10:07:31.126Z","desc":"Android 多渠道打包的 Android Studio / IDEA 插件","images":["http://img.gank.io/e9e0de53-aaba-4e2b-a123-cdaac87eeb56"],"publishedAt":"2017-01-11T12:05:20.787Z","source":"web","type":"Android","url":"https://github.com/nukc/ApkMultiChannelPlugin","used":true,"who":"C君"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5877091e421aa93161103df7
         * createdAt : 2017-01-12T12:42:06.839Z
         * desc : 另辟蹊径实现Android多渠道打包
         * publishedAt : 2017-01-13T11:58:16.991Z
         * source : chrome
         * type : Android
         * url : https://yrom.net/blog/2015/05/25/the_other_way_to_package_multi_channel_apks/
         * used : true
         * who : LHF
         * images : ["http://img.gank.io/be9571a9-99d7-4537-a291-0c772ec2f08f"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
