package com.pang.hatsune.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 播放音乐的操作类
 * Created by Administrator on 2016/8/19.
 */
public class PlayMusic {
    private static volatile PlayMusic instance;
    private MediaPlayer mMediaPlayer;
    private SeekBar seekBar; // 拖动条;
    private Timer mTimer = new Timer(); // 计时器
    private TextView currentTime; //用于显示当前播放时间
    private TextView endTime; // 用于显示总播放时长


    Handler handler = new Handler() {
        private boolean isTimeEndSet = false; // 用于判断是否设置了总时间

        public void handleMessage(android.os.Message msg) {
            int position = mMediaPlayer.getCurrentPosition();
            int duration = mMediaPlayer.getDuration();
            if (duration > 0) {
                currentTime.setText(StringFilter.getInstance().calcDurtions(position));

                if (!isTimeEndSet) {
                    endTime.setText(StringFilter.getInstance().calcDurtions(duration));// 设置播放总时间}
                    isTimeEndSet = true;
                }
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                long pos = seekBar.getMax() * position / duration;
                seekBar.setProgress((int) pos);
            }
        }
    };

    timerTask task = new timerTask();

    private PlayMusic(SeekBar seekBar) {
        initPlayMusic(seekBar);

    }

    public static PlayMusic getInstance(SeekBar seekBar) {
        if (instance == null) {
            synchronized (StringFilter.class) {
                if (instance == null) {
                    instance = new PlayMusic(seekBar);
                }
            }
        }
        return instance;
    }


    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    /**
     * 注册播放的对象和监听器
     */
    private void initPlayMusic(SeekBar seekBar) {
        this.seekBar = seekBar;
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        mMediaPlayer.setOnBufferingUpdateListener(new seekbarBuffering());
        mMediaPlayer.setOnPreparedListener(new MpPrepared());
        mMediaPlayer.setOnCompletionListener(new MpComple());


        // 每一秒触发一次
        mTimer.schedule(task, 0, 1000);
    }

    public void play() {
        try {
            mMediaPlayer.start();
        } catch (NullPointerException e) {
            initPlayMusic(seekBar);
        }

    }

    /**
     * 播放网络音乐
     *
     * @param url
     */
    public void playUrl(String url) {
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(url);// 设置数据源
            mMediaPlayer.prepare();// prepare自动播放
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    /**
     * 停止音乐
     */
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


    public void setEndTime(TextView endTime) {
        this.endTime = endTime;
    }

    public void setCurrentTime(TextView currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * mediaPlayer缓存进度的监听器
     */
    class seekbarBuffering implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            seekBar.setSecondaryProgress(percent);//设置seekbar二级进度
//            Log.e("hhtjim","Current:"+ mMediaPlayer.getCurrentPosition() +"/ Duration:"+ mMediaPlayer.getDuration());
//            System.out.println("hhtjim:percent"+percent);
//            int currentProgress = seekBar.getMax() * mMediaPlayer.getCurrentPosition() / mMediaPlayer.getDuration();
//            Log.e(currentProgress + "% play", percent + " buffer");
        }

    }


    /**
     * mediaPlayer播放准备的监听
     */
    private class MpPrepared implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
//            Log.e("mediaPlayer", "onPrepared");
        }
    }


    /**
     * 播放完成的监听
     */
    private class MpComple implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
//...
        }
    }


    /**
     * 计时器的任务
     */
    private class timerTask extends TimerTask {

        @Override
        public void run() {

            if (mMediaPlayer == null)
                return;
            if (mMediaPlayer.isPlaying() && seekBar.isPressed() == false) {
//                System.out.println("hhtjim:sendEmptyMessage");
                handler.sendEmptyMessage(0); // 发送消息
            }
        }
    }

}
