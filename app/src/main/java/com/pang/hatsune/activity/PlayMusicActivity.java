package com.pang.hatsune.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.info.SoundInfo;
import com.pang.hatsune.utils.PlayMusic;
import com.pang.hatsune.utils.StringFilter;

/**
 * 播放音乐的界面
 * Created by Administrator on 2016/8/19.
 */
public class PlayMusicActivity extends BaseActivity {
    public static final String KEYWORD = PlayMusicActivity.class.getName();
    private AppCompatSeekBar seekbar;
    private TextView title;
    private TextView timeStart;
    private TextView timeEnd;
    private ImageView pre;
    private ImageView play;
    private ImageView next;
    private SimpleDraweeView image;
    private PlayMusic playMusic;
    private SoundInfo soundSource;//跳转过来的音乐资源对象
    private String source;//跳转过来的音乐资源对象里的音乐地址
    private int currentPlayStatu = 0;//当前的播放状态 0暂停  1开始
//    private String  source = "http://abv.cn/music/光辉岁月.mp3";
//    private String  source = "http://m2.music.126.net/HoBJmZtM6541EPP8jfq5nA==/2097868185806343.mp3";

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
        playMusic.setCurrentTime(timeStart);
        playMusic.setEndTime(timeEnd);


        seekbar.setOnSeekBarChangeListener(new SeekBarChangeEvent());

        try {
            soundSource = (SoundInfo) getIntent().getSerializableExtra(KEYWORD);
            if (soundSource.getSource() == null) {//为空就进catch
                throw new NullPointerException();
            }
            source = soundSource.getSource();//这样操作  避免上面报空之后被赋值为空
        } catch (NullPointerException e) {
            source = "http://96.f.1ting.com/57ba89e7/fa027961cb21f4b26bf17fd73969e672/q/20040320b/3.mp3";
            Snackbar.make(seekbar, "获取音乐资源失败，准备播放默认歌曲。", Snackbar.LENGTH_SHORT).show();
        }

        try {
            title.setText(soundSource.getName());
            image.setImageURI(Uri.parse(soundSource.getPic()));
        } catch (NullPointerException e) {
            title.setText("");
            image.setImageURI(Uri.parse("http://f2.topitme.com/2/8c/49/1102507542417498c2l.jpg"));
        }


        togglePlayButton();
        new Thread() {
            @Override
            public void run() {
                playMusic.playUrl(source);
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

            timeStart.setText(StringFilter.getInstance().calcDurtions(playMusic.getMediaPlayer().getCurrentPosition()));//拖动进度条  修改当前播放的时间

        }

    }

    /**
     * 监听播放状态按钮的监听  开始 暂停  上/下一曲
     */
    public void click_listen_play_button(View v) {
        switch (v.getId()) {
            case R.id.play_pre:
                break;
            case R.id.play_next:
                break;
            case R.id.play_play:
                togglePlayButton();
                break;
        }
    }


    /**
     * 切换  暂停/开始 按钮的图标显示
     */
    private void togglePlayButton() {
        currentPlayStatu = ++currentPlayStatu % 2;
        if (currentPlayStatu == 0) {
            ((ImageView) play).setImageResource(R.drawable.ic_music_notification_play_gray);
            playMusic.pause();
        } else if (currentPlayStatu == 1) {
            ((ImageView) play).setImageResource(R.drawable.ic_music_notification_pause_gray);
            playMusic.play();
        }
    }

    /**
     * 销毁状态
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playMusic != null) {
            playMusic.stop();
            playMusic = null;
        }
    }
}
