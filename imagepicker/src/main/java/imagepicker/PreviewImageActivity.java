package imagepicker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.imagepicker.R;


public class PreviewImageActivity extends Activity {
    String path;
    MyImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preview_image_view);
        path=getIntent().getStringExtra("filePath");
        imageView= (MyImageView) findViewById(R.id.zv_imageView);
        Bitmap bt=BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bt);
    }
}
