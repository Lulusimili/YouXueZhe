package imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imagepicker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImageSelectorDetailsActivity extends Activity {
    TextView _title_text, _r_text, _left_text, _tv_selector_list_preview, _tv_selector_list_confirm;
    RelativeLayout _title_bar;
    RecyclerView _rec_image_list;
    Map<String, Object> map;
    List<String> fileList;
    MyAdapter adapter;
    String filePath = "";
    ArrayList<String> list;

    int type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_selector);
        type = getIntent().getIntExtra("type", 1);
        list = new ArrayList<String>();
        initView();
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");
        _tv_selector_list_preview.setVisibility(View.INVISIBLE);
        map = serializableMap.getMap();
        _title_text.setText(map.get("fileName").toString());
        fileList = (List<String>) map.get("filelist");
        _rec_image_list.setLayoutManager(new GridLayoutManager(ImageSelectorDetailsActivity.this, 3));
        adapter = new MyAdapter();
        _rec_image_list.setAdapter(adapter);
        adapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                filePath = fileList.get(position);
                if ("".equals(filePath) || filePath == null) {
                    Toast.makeText(ImageSelectorDetailsActivity.this, "请先选择一张图片", Toast.LENGTH_SHORT).show();
                } else {
                    if (type!=1) {
                        Intent it = new Intent(ImageSelectorDetailsActivity.this, PreviewImageActivity.class);
                        it.putExtra("filePath", filePath);
                        startActivity(it);
                    }
                    else
                    {
                        Intent it = new Intent();
                        ArrayList<String> flielist = new ArrayList<String>();
                        flielist.add(filePath);
                        it.putStringArrayListExtra("filePath", flielist);
                        setResult(ImageSelectorListActivity.RESULT_OK, it);
                        finish();
                    }
                }
            }
        });

        //多选
        if (type != 1) {


            adapter.setOnItemSelectListener(new OnItemSelectListener() {
                @Override
                public void OnItemSelect(View view, int position, boolean isChecked) {
                    if (isChecked) {
                        if(!list.contains(fileList.get(position))) {
                            list.add(fileList.get(position));
                            if (list == null || list.size() == 0) {
                                _tv_selector_list_confirm.setText("确认");
                            } else {
                                _tv_selector_list_confirm.setText("确认(" + list.size() + ")");

                            }
                        }

                    } else {
                        if(list.contains(fileList.get(position))) {
                            list.remove(fileList.get(position));
                            if (list == null || list.size() == 0) {
                                _tv_selector_list_confirm.setText("确认");
                            } else {
                                _tv_selector_list_confirm.setText("确认(" + list.size() + ")");
                            }
                        }
                    }
                }
            });
        }
    }

    private void initView() {
        _title_text = (TextView) findViewById(R.id.title_text);
        if (type!=1)
        findViewById(R.id.rl_selector_bottom).setVisibility(View.VISIBLE);
        _r_text = (TextView) findViewById(R.id.r_text);
        _title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        _rec_image_list = (RecyclerView) findViewById(R.id.rec_image_list);
        _left_text = (TextView) findViewById(R.id.left_text);
        _left_text.setText("相册");
        _left_text.setVisibility(View.VISIBLE);
        _tv_selector_list_preview = (TextView) findViewById(R.id.tv_selector_list_preview);
        _tv_selector_list_confirm = (TextView) findViewById(R.id.tv_selector_list_confirm);
        _left_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _tv_selector_list_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    if ("".equals(filePath) || filePath == null) {
                        Toast.makeText(ImageSelectorDetailsActivity.this, "请先选择一张图片", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent it = new Intent();
                        ArrayList<String> flielist = new ArrayList<String>();
                        flielist.add(filePath);
                        it.putStringArrayListExtra("filePath", flielist);
                        setResult(ImageSelectorListActivity.RESULT_OK, it);
                        finish();
                    }
                } else {
                    if (list == null || list.size() == 0) {
                        Toast.makeText(ImageSelectorDetailsActivity.this, "请先选择一张图片", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent it = new Intent();
                    it.putStringArrayListExtra("filePath", list);
                    setResult(ImageSelectorListActivity.RESULT_OK, it);
                    finish();
                }
            }
        });

    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);

    }

    public interface OnItemSelectListener {
        void OnItemSelect(View view, int position, boolean isChecked);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        private OnItemClickListener mOnItemClickListener;
        private OnItemSelectListener mOnItemSelectListener;
        private Map<Integer, Boolean> map = new HashMap<>();

        public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
            this.mOnItemClickListener = mOnItemClickLitener;
        }

        public void setOnItemSelectListener(OnItemSelectListener mOnItemSelectListener) {
            this.mOnItemSelectListener = mOnItemSelectListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(ImageSelectorDetailsActivity.this).inflate(R.layout.image_rec_item, null);


            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Glide.with(ImageSelectorDetailsActivity.this).load(fileList.get(position)).fitCenter()
                    .centerCrop().into(holder.iv);
            final int pos = position;
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.OnItemClick(v, pos);
                }
            });
            if (type == 1) {
                holder.select.setVisibility(View.INVISIBLE);
            } else {
                holder.select.setVisibility(View.VISIBLE);

                holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked == true) {
                            map.put(pos, true);
                        } else {
                            map.remove(pos);
                        }

                        if (mOnItemSelectListener != null)
                            mOnItemSelectListener.OnItemSelect(buttonView, pos, isChecked);
                    }
                });
                if (map != null && map.containsKey(position)) {
                    holder.select.setChecked(true);
                } else {
                    holder.select.setChecked(false);
                }
            }
        }

        @Override
        public int getItemCount() {
            return fileList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            //CheckBox ck;
            CheckBox select;

            public MyViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.iv_rec_item);

                ViewGroup.LayoutParams params = iv.getLayoutParams();
                int width = (ScreenUtils.getScreenWidth(ImageSelectorDetailsActivity.this) - 20) / 3;
                int height = (ScreenUtils.getScreenHeight(ImageSelectorDetailsActivity.this)) / 5;

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, height);
                iv.setLayoutParams(lp);
                //ck = (CheckBox) itemView.findViewById(R.id.ck_rec_item);
                select=(CheckBox) itemView.findViewById(R.id.ck_rec_item);
                select.setChecked(false);

            }
        }
    }
}
