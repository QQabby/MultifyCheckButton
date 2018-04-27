package app.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多选按钮
 */
public class MainActivity extends AppCompatActivity {

    ButtonFlowLayout mButtonFLowLayout;
    List<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonFLowLayout = findViewById(R.id.buttnLayout);

        data.add("天气不错");
        data.add("咔嚓咔嚓咔嚓");
        data.add("多云");
        data.add("雷电交加");
        data.add("雨夹雪");

        mButtonFLowLayout.setDataList(data);
        mButtonFLowLayout.setSingleSelectItem(1);

//        mButtonFLowLayout.setOnSingleChanedListener(new ButtonFlowLayout.OnSingleChanedListener() {
//            @Override
//            public void onSingleChaned(int index) {
//                Toast.makeText(MainActivity.this,"当前选中："+index,Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        mButtonFLowLayout.setOnMultifyChanedListenerr(new ButtonFlowLayout.OnMultifyChanedListener() {
//            @Override
//            public void onMultifyChaned(List<Integer> indexs) {
//                for(int i=0;i<indexs.size();i++){
//                    Log.i("xx","当前选中的集合是："+indexs.get(i));
//                }
//            }
//        });
    }


}
