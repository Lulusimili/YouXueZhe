package imagepicker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class ImagePicker {
    private int TYPE;
    private final String TYPE_KEY="type";

    private static ImagePicker imagePicker;
    private String originPath;
    private ArrayList<String> originPaths;
    private static final int RESULT=ImageSelectorListActivity.RESULT_OK;

    public ImagePicker(){
    }
    public static ImagePicker create(){
        if (imagePicker==null){
            imagePicker=new ImagePicker();
        }
        return imagePicker;
    }

    public ImagePicker single(){
        TYPE=1;
        return imagePicker;
    }

    public ImagePicker multi(){
        TYPE=2;
        return imagePicker;
    }

    public  ImagePicker origin(String path){
        originPath=path;
        return  imagePicker;
    }
    public ImagePicker origin(ArrayList<String> originPaths){
        this.originPaths=originPaths;
        return imagePicker;
    }

    public void start(Activity activity){
        final Context context =activity;
        if(hasPermission(context)) {
            activity.startActivityForResult(createIntent(context),ImageSelectorListActivity.RESULT_OK);
        }else{
            Toast.makeText(context,"没有权限!", Toast.LENGTH_SHORT).show();
        }
    }

    public void start(Fragment fragment){
        final Context context = fragment.getContext();
        if(hasPermission(context)) {
            fragment.startActivityForResult(createIntent(context),ImageSelectorListActivity.RESULT_OK);
        }else{
            Toast.makeText(context, "没有权限！", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasPermission(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            // Permission was added in API Level 16
            return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private Intent createIntent(Context context){
        Intent intent= new Intent(context, ImageSelectorListActivity.class);
        intent.putExtra(TYPE_KEY, TYPE);
        if(TYPE==1) {
            intent.putExtra(ImageSelectorListActivity.ORIGIN_PATH, originPath);
        }else {
            intent.putStringArrayListExtra(ImageSelectorListActivity.ORIGIN_PATH,originPaths);
        }
        return intent;
    }
}
