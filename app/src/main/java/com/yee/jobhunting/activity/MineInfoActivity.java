package com.yee.jobhunting.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yee.jobhunting.R;
import com.yee.jobhunting.adapter.JobAdapter;
import com.yee.jobhunting.bean.AddJob;
import com.yee.jobhunting.bean.User;
import com.yee.jobhunting.bean.UserInfo;
import com.yee.jobhunting.utils.ImageUtils;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class MineInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivMineInfoAvatar;
    private TextView tvMineInfoName;
    private TextView tvMineInfoGender;
    private TextView tvMineInfoBirth;
    private TextView tvMineInfoEmail;
    private List<UserInfo> userInfoList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    private static final String TAG = "MineInfoActivity";

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);

        ViewUtils.initWhiteTitleBar(this, "个人信息");

        //设置状态栏
        StatusBarUtils.initWhiteStatusBar(this);


        initView();

        initListener();



    }



    private void initView() {

        ivMineInfoAvatar = findViewById(R.id.iv_mine_info_avatar);
        tvMineInfoName = findViewById(R.id.tv_mine_info_name);
        tvMineInfoGender = findViewById(R.id.tv_mine_info_gender);
        tvMineInfoBirth = findViewById(R.id.tv_mine_info_birth);
        tvMineInfoEmail = findViewById(R.id.tv_mine_info_email);

        tvMineInfoName.setOnClickListener(this);
        tvMineInfoGender.setOnClickListener(this);
        tvMineInfoBirth.setOnClickListener(this);
        tvMineInfoEmail.setOnClickListener(this);


        //获取当前用户个人信息
        if (BmobUser.isLogin()) {

            User user = BmobUser.getCurrentUser(User.class);
            String nickName = user.getNickName();
            String userGender = user.getUserGender();
            String userBirth = user.getUserBirth();
            String userEmail = user.getEmail();

            tvMineInfoName.setText(nickName);
            tvMineInfoGender.setText(userGender);
            tvMineInfoBirth.setText(userBirth);
            tvMineInfoEmail.setText(userEmail);

        }else {
            Toast.makeText(MineInfoActivity.this, "获取失败！请先登录！", Toast.LENGTH_SHORT).show();
        }


    }


    private void initListener() {

        //监听头像的点击事件
        ivMineInfoAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出修改头像对话框
                showChoosePicDialog();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_info_name:
                Intent intent1 = new Intent(MineInfoActivity.this, ModifyInfoActivity.class);
                startActivity(intent1);
                break;
        }

    }


    /*
     * 显示修改头像的对话框
     * */
    public void showChoosePicDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String []items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    //1、选择本地照片
                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    //2、拍照
                    case TAKE_PICTURE:
                        takePicture();
                        break;
                }
            }

        });
        builder.create().show();
    }

    /*
     * 拍照
     * */
    private void takePicture() {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(MineInfoActivity.this, "com.yee.jobhunting.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //requestCode和resultCode不能写错
        if (resultCode  == RESULT_OK) {// 如果返回码正常
            switch (requestCode) {
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData());// 开始对图片进行裁剪处理
                    break;
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri);// 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {

                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;

            }
        }

    }


    /*
     * 裁剪图片
     * */
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);

    }

    /*
     * 保存裁剪之后的图片数据
     * */
    protected void setImageToView(Intent data) {

        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.d(TAG,"setImageToView:"+photo);
            photo = ImageUtils.toRoundBitmap(photo);
            ivMineInfoAvatar.setImageBitmap(photo);
            upload(photo);
        }

    }


    /*
     * 上传头像文件
     * */
    private void upload(Bitmap bitmap) {

        String picPath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()) );

        BmobFile bmobFile = new BmobFile(new File(picPath));

        Log.e("imagePath", picPath+"");

        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    UserInfo userInfo = new UserInfo();
                    userInfo.setAvatar(bmobFile);
//                    userInfo.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//                            if (e == null){
//
//
//
//                                Toast.makeText(MineInfoActivity.this, "文件上传成功！"+bmobFile.getFileUrl(), Toast.LENGTH_SHORT).show();
//
//                            }else {
//                                Toast.makeText(MineInfoActivity.this, "文件上传失败！"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                    Toast.makeText(MineInfoActivity.this, "文件上传成功！"+bmobFile.getFileUrl(), Toast.LENGTH_SHORT).show();
                    Log.w("hhh",bmobFile.getFileUrl());

                }else {
                    Toast.makeText(MineInfoActivity.this, "文件上传失败！"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }



}