package com.modules.study.bean;

public class AudioMaterial extends MaterialBase{


    /**
     * 该Audio的总长度，等于该audio的结束时间（假设开始时间是0）
     */
    private int mAudioTotalLength;

    /**
     * 该素材被选则的部分的开始时间
     */

    private int mAudioBeginTime;

    /**
     * 该素材被选择的部分的结束时间
     */
    private int mAudioEndTime;

    /**
     * 该素材被选择的部分的长度
     */
    private int mAudioSelectedLength;

    private int[] mTotalWaveLines;

    private int[] mSelectWaveLines;

}
