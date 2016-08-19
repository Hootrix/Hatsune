package com.pang.hatsune.activity;

import android.support.v7.widget.AppCompatSeekBar;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.utils.PlayMusic;

/**
 * 播放音乐的界面
 * Created by Administrator on 2016/8/19.
 */
public class PlayMusicActivity extends BaseActivity {
    private AppCompatSeekBar seekbar;
    private TextView title;
    private TextView timeStart;
    private TextView timeEnd;
    private ImageView pre;
    private ImageView play;
    private ImageView next;
    private SimpleDraweeView image;
    private PlayMusic playMusic;

    @Override
    protected void initView() {
        seekbar = $(R.id.play_seekbar);
        title = $(R.id.play_title);
        timeStart = $(R.id.play_start_time);
        timeEnd = $(R.id.play_end_time);
        pre = $(R.id.play_pre);
        play = $(R.id.play_play);
        next = $(R.id.play_next);
        image = $(R.id.play_image);
    }


    @Override
    protected void initViewData() {
        playMusic = PlayMusic.getInstance(seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBarChangeEvent());
       new Thread(){
           @Override
           public void run() {
               playMusic.playUrl("http://m5.file.xiami.com/ttpod_move/47/47/1104565/122627_1443114359.mp3?auth_key=2b8870f399a8b6f5fb4e3ba03436befe-1471651200-0-null");
           }
       }.start();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_mplay;
    }


    /**
     * 拖动条的监听器
     */
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * playMusic.getMediaPlayer().getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            playMusic.getMediaPlayer().seekTo(progress);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playMusic != null) {
            playMusic.stop();
            playMusic = null;
        }
    }
}
