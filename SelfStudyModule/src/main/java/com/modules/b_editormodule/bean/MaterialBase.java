package com.modules.b_editormodule.bean;

/**
 * 素材基类
 */
public class MaterialBase {

    public static final int TYPE_PIC = 1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_OVERLAY = 3;
    public static final int TYPE_STICKER = 4;

    /**
     * 该素材在视频里面的开始时间
     */
    protected float mStartTimeInVideo;

    /**
     * 该素材在视频里面的结束时间
     */
    protected float mEndTimeInVideo;

    /**
     * 该素材的持续时间
     */
    private float mTotalLength;

    /**
     * 该素材的类型
     */
    protected int mMaterialType;


    public float getStartTimeInVideo() {
        setTotalLength();
        return mStartTimeInVideo;
    }

    public void setStartTimeInVideo(float mStartTimeInVideo) {
        setTotalLength();
        this.mStartTimeInVideo = mStartTimeInVideo;
    }

    public float getEndTimeInVideo() {
        return mEndTimeInVideo;
    }

    public void setEndTimeInVideo(float mEndTimeInVideo) {
        this.mEndTimeInVideo = mEndTimeInVideo;
    }

    private void setTotalLength() {
        mTotalLength = mEndTimeInVideo - mStartTimeInVideo;
    }

    public float getTotalLength() {
        return mTotalLength;
    }
}
