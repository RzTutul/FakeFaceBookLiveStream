package com.example.fakefblivestream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
    String [] girlsName;
    String [] girlsComment;
    String [] boysName;
    String [] boysComment;

    int [] userImage;
    int [] usermaleImage;
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
                      //  commentPojoList.add(new CommentPojo("null", "Sokina Akter", "Hello how are you"));
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


         userImage = new int[]{ R.drawable.f_user1,R.drawable.f_user2,
                R.drawable.f_user3,R.drawable.f_user4,R.drawable.f_user5,R.drawable.f_user6,R.drawable.f_user7,
                R.drawable.f_user8,R.drawable.f_user9,R.drawable.f_user10
        };

          usermaleImage = new int[]{R.drawable.m_user1,R.drawable.m_user2,
                R.drawable.m_user3,R.drawable.m_user4,R.drawable.m_user5,R.drawable.m_user6,R.drawable.m_user7,
                R.drawable.m_user8,R.drawable.m_user9,R.drawable.m_user10
        };



         girlsName = getResources().getStringArray(R.array.girlsName);
         girlsComment = getResources().getStringArray(R.array.girlsCommment);
         boysName = getResources().getStringArray(R.array.boyName);
         boysComment = getResources().getStringArray(R.array.boyscomment);

        startCountAnimation();
        //For Emoji
        emoji_haha();
        emoji_haha();


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


        commentRV = findViewById(R.id.commentRV);
        commentRVAdapter = new CommentRVAdapter(MainActivity.this, commentPojoList);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setStackFromEnd(true);
        commentRVAdapter.notifyDataSetChanged();
        commentRV.setLayoutManager(llm);
        commentRV.setAdapter(commentRVAdapter);




        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        final int start = Math.min(0, 9);
        int end = Math.max(0, 9);
        int difference = Math.abs(9 - 0);
        Handler handler = new Handler();

        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 40000) * count;
            final int finalCount = ((0 > 9) ? 0 - count : count);
            final int finalCount1 = count;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CommentPojo comment = new CommentPojo(userImage[finalCount1],girlsName[finalCount1], girlsComment[finalCount1]);
                    commentRVAdapter.UpdateCommnet(comment);
                    commentRV.smoothScrollToPosition(finalCount1);
                    commentRV.smoothScrollToPosition(commentRV.getBottom());
                }
            }, time);

        }

           DecelerateInterpolator decelerateInterpolator1 = new DecelerateInterpolator(0.8f);
        final int start1 = Math.min(0, 9);
        int end1= Math.max(0, 9);
        int difference1 = Math.abs(9 - 0);
        Handler handler1 = new Handler();

        for (int count = start1; count <= end1; count++) {
            int time = Math.round(decelerateInterpolator1.getInterpolation((((float) count) / difference1)) * 20000) * count;
            final int finalCount = ((0 > 9) ? 0 - count : count);
            final int finalCount1 = count;
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CommentPojo comment = new CommentPojo(usermaleImage[finalCount1],boysName[finalCount1], boysComment[finalCount1]);
                    commentRVAdapter.UpdateCommnet(comment);
                    commentRV.smoothScrollToPosition(finalCount1);
                    commentRV.smoothScrollToPosition(commentRV.getBottom());
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
