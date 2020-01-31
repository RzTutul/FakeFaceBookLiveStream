package com.example.fakefblivestream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fakefblivestream.emoji_overly.Direction;
import com.example.fakefblivestream.emoji_overly.ZeroGravityAnimation;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private Button capture, viewCounter;
    private ImageView switchCamera;
    private Context myContext;
    private LinearLayout cameraPreview;
    private boolean cameraFront = false;
    public static Bitmap bitmap;
    private RecyclerView commentRV;
    private int progress = 0;

    public CommentRVAdapter commentRVAdapter;

    public static List<CommentPojo> commentPojoList;

    static {

        commentPojoList = new ArrayList<>();
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start = Math.min(0, 6);
        int end = Math.max(0, 6);
        int difference = Math.abs(6 - 0);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 10000) * count;
            final int finalCount = ((0 > 6) ? 0 - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalCount == 0) {
                        commentPojoList.add(new CommentPojo("null", "Rz Tutul", "Hello how are you"));
                    }
                    if (finalCount == 1) {
                        commentPojoList.add(new CommentPojo("null", "Shuvo", "Oi ki obsta?"));

                    }
                    if (finalCount == 2) {
                        commentPojoList.add(new CommentPojo("null", "Shahrukh", "Vai koi asen?"));
                    }
                    if (finalCount == 3) {
                        commentPojoList.add(new CommentPojo("null", "Shina", "vai apni koi asen amak ekta call den urgent "));
                    }
                    if (finalCount == 4) {
                        commentPojoList.add(new CommentPojo("null", "SB Tinu", "Sina Amak Rate call diba"));
                    }
                    if (finalCount == 5) {
                        commentPojoList.add(new CommentPojo("null", "Antora ", "Tui jodi oka call dis tor kobor ase"));
                    }
                    if (finalCount == 6) {
                        commentPojoList.add(new CommentPojo("null", "Mamud", "Yoyo bro"));
                    }


                }
            }, time);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        viewCounter = findViewById(R.id.viewCounter);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        startCountAnimation();
        //For Emoji
        emoji_one();
        emoji_two();
        emoji_three();
        myContext = this;

        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        cameraPreview = (LinearLayout) findViewById(R.id.cPreview);
        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);

        capture = (Button) findViewById(R.id.btnCam);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
        releaseCamera();
        chooseCamera();


        switchCamera = findViewById(R.id.btnSwitch);
        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the number of cameras
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

                    releaseCamera();
                    chooseCamera();
                } else {

                }
            }
        });

        mCamera.startPreview();
    }

    public void updateUI() {
        commentRV = findViewById(R.id.commentRV);
        commentRVAdapter = new CommentRVAdapter(MainActivity.this, commentPojoList);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        commentRV.setLayoutManager(llm);
        commentRV.setAdapter(commentRVAdapter);

    }

    private void startCountAnimation() {
        ///Count 0 to 150 in 5sec

      /*  ValueAnimator animator = ValueAnimator.ofInt(0, 150);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                viewCounter.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
        */


        ///This is worked slowly
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start = Math.min(0, 150);
        int end = Math.max(0, 150);
        int difference = Math.abs(150 - 0);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 5000) * count;
            final int finalCount = ((0 > 150) ? 0 - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewCounter.setText(String.valueOf(finalCount));
                        updateUI();
                    emoji_two();
                }
            }, time);
        }
    }

    private int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;

    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }

    public void onResume() {

        super.onResume();
        if (mCamera == null) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mPicture = getPictureCallback();
            mPreview.refreshCamera(mCamera);
            Log.d("nu", "null");
        } else {
            Log.d("nu", "no null");
        }

    }

    private void chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        }
    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //when on Pause, release camera in order to be used from other applications
        releaseCamera();
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                //Intent intent = new Intent(MainActivity.this,PictureActivity.class);
                // startActivity(intent);
            }
        };
        return picture;
    }


    ///FOR Emoji

    public void flyEmoji(final int resId) {
        ZeroGravityAnimation animation = new ZeroGravityAnimation();
        animation.setCount(1);
        animation.setScalingFactor(0.2f);
        animation.setOriginationDirection(Direction.BOTTOM);
        animation.setDestinationDirection(Direction.TOP);
        animation.setImage(resId);
        animation.setAnimationListener(new Animation.AnimationListener() {
                                           @Override
                                           public void onAnimationStart(Animation animation) {

                                           }

                                           @Override
                                           public void onAnimationEnd(Animation animation) {

                                           }

                                           @Override
                                           public void onAnimationRepeat(Animation animation) {

                                           }
                                       }
        );

        ViewGroup container = findViewById(R.id.animation_holder);
        animation.play(this, container);

    }

    public void emoji_one() {
        // You can change the number of emojis that will be flying on screen
        for (int i = 0; i < 4; i++) {
            flyEmoji(R.drawable.love);
        }
    }
    // You can change the number of emojis that will be flying on screen

    public void emoji_two() {
        for (int i = 0; i < 1; i++) {
            flyEmoji(R.drawable.sademoji);
        }

    }
    // You can change the number of emojis that will be flying on screen

    public void emoji_three() {
        for (int i = 0; i < 3; i++) {
            flyEmoji(R.drawable.hahaemoji);
            flyEmoji(R.drawable.like);
            flyEmoji(R.drawable.angryface);

        }


    }
}
