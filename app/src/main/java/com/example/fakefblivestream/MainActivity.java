package com.example.fakefblivestream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

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

    public CommentRVAdapter commentRVAdapter;

    public static List<CommentPojo> commentPojoList;

    static {

        commentPojoList = new ArrayList<>();
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start = Math.min(0, 1);
        int end = Math.max(0, 1);
        int difference = Math.abs(1 - 0);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 10000) * count;
            final int finalCount = ((0 > 1) ? 0 - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalCount == 0) {
                        commentPojoList.add(new CommentPojo("null", "Rz Tutul", "Hello how are you"));
                    }
                    if (finalCount == 1) {
                        commentPojoList.add(new CommentPojo("null", "Shuvo", "Oi ki obsta?"));

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


        int [] userImage = new int[]{R.drawable.hahaemoji, R.drawable.angryface,R.drawable.rztutul,R.drawable.like,R.drawable.love,R.drawable.sademoji};

        startCountAnimation();
        //For Emoji
        emoji_haha();
        emoji_haha();
        myContext = this;

        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);//90
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


        commentRV = findViewById(R.id.commentRV);
        commentRVAdapter = new CommentRVAdapter(MainActivity.this, commentPojoList,userImage);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        //llm.setStackFromEnd(true);

        commentRVAdapter.notifyDataSetChanged();
        commentRV.setLayoutManager(llm);
        commentRV.setAdapter(commentRVAdapter);

        commentRV.post(new Runnable() {
            @Override
            public void run() {
                // Call smooth scroll
                commentRV.smoothScrollToPosition(commentRVAdapter.getItemCount() - 1);
            }
        });



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
                        CommentPojo comment = new CommentPojo("null", "Tutul", "Hello how are you");
                        commentRVAdapter.UpdateCommnet(comment);
                    }
                    if (finalCount == 1) {
                        CommentPojo comment =(new CommentPojo("null", "Shuvo", "Oi ki obsta?"));
                        commentRVAdapter.UpdateCommnet(comment);
                    }
                    if (finalCount == 2) {
                        CommentPojo comment =(new CommentPojo("null", "Shahrukh", "Vai koi asen?"));
                        commentRVAdapter.UpdateCommnet(comment);
                    }
                    if (finalCount == 3) {
                        CommentPojo comment =(new CommentPojo("null", "Shina", "vai apni koi asen amak ekta call den urgent "));
                        commentRVAdapter.UpdateCommnet(comment);
                    }
                    if (finalCount == 4) {
                        CommentPojo comment =(new CommentPojo("null", "SB Tinu", "Sina Amak Rate call diba"));
                        commentRVAdapter.UpdateCommnet(comment);
                        commentRVAdapter.UpdateCommnet(comment);
                    }
                    if (finalCount == 5) {
                        CommentPojo comment =(new CommentPojo("null", "Antora ", "Tui jodi oka call dis tor kobor ase"));
                        commentRVAdapter.UpdateCommnet(comment);
                    }
                    if (finalCount == 6) {
                        CommentPojo comment =(new CommentPojo("null", "Mamud", "Yoyo bro"));
                        commentRVAdapter.UpdateCommnet(comment);
                    }


                }
            }, time);
        }
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
                    emoji_like();
                    emoji_haha();
                    emoji_love();
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

    public void emoji_love() {
        // You can change the number of emojis that will be flying on screen
        for (int i = 0; i < 1; i++) {
            flyEmoji(R.drawable.love);
        }
    }
    // You can change the number of emojis that will be flying on screen

    public void emoji_sad() {
        for (int i = 0; i < 1; i++) {
            flyEmoji(R.drawable.sademoji);
        }

    }
    // You can change the number of emojis that will be flying on screen

    public void emoji_haha() {
        for (int i = 0; i < 1; i++) {
            flyEmoji(R.drawable.hahaemoji);


        }

    }
        public void emoji_like() {
        for (int i = 0; i < 1; i++) {
            flyEmoji(R.drawable.like);


        }
    }     public void emoji_angry() {
        for (int i = 0; i < 2; i++) {
            flyEmoji(R.drawable.angryface);

        }
    }


}
