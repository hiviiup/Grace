package com.evayinfo.grace.http.download;

/**
 * Created by hiviiup on 2017/3/28.
 * 下载监听进度条
 */

public interface DownloadProgressLisenter {
    void update(long downloadSize, long totalSize, boolean done);
}
