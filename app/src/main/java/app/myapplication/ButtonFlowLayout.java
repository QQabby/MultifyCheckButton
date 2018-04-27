package app.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqianqian on 2018/4/26.
 */

public class ButtonFlowLayout extends LinearLayout {

    boolean isMulitfy = false;
    List<String> data = new ArrayList<>();
    private Context context;
    SparseBooleanArray stateMap = new SparseBooleanArray();
    /**
     * current selected index
     */
    private int currentCheckedIndex = 0;

    /**
     * default selected first one
     */
    private int checkedIndex = 0;
    private OnSingleChanedListener onSingleChanedListener;
    private OnMultifyChanedListener onMultifyChanedListener;

    public ButtonFlowLayout(Context context) {
        this(context,null);
    }

    public ButtonFlowLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ButtonFlowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void initView(Context context) {
        FlowLayout flowLayout = new FlowLayout(context,null);
        addView(flowLayout);

        for (int i = 0; i < data.size(); i++) {
            View child = View
                    .inflate(context, R.layout.item_button, null);
            final TextView tagText = (TextView) child
                    .findViewById(R.id.new_money_frame);
            //为每一个标签打上标签
            tagText.setTag(i);

            if(stateMap.get(i)){
                setChecked(tagText);
            }else{
                setUnChecked(tagText);
            }

            tagText.setText(data.get(i));
            tagText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tagIndex = (int) v.getTag();
                    if(!isMulitfy){//single check
                        if(currentCheckedIndex != tagIndex){
                            setChecked(tagText);
                            setUnChecked((TextView) findViewWithTag(currentCheckedIndex));
                            currentCheckedIndex = tagIndex;
                            if(null != onSingleChanedListener){
                                onSingleChanedListener.onSingleChaned(tagIndex);
                            }
                        }
                    }else{//mulitify check
                        if(stateMap.get(tagIndex)){
                            setUnChecked(tagText);
                            stateMap.put(tagIndex,false);
                        }else{
                            setChecked(tagText);
                            stateMap.put(tagIndex,true);
                        }

                        if(null != onMultifyChanedListener){
                            List<Integer> checkedList = new ArrayList<>();

                            for(int i=0;i<stateMap.size();i++){
                                if(stateMap.get(i)){
                                    checkedList.add(i);
                                }
                            }
                            onMultifyChanedListener.onMultifyChaned(checkedList);
                        }
                    }
                }
            });
            flowLayout.addView(child);
        }
    }

    public void setSingleSelectItem(int checkedIndex){
        this.checkedIndex = checkedIndex;
    }


    public void setChecked(TextView tagText){
        tagText.setBackgroundResource(R.drawable.shape_oval_button);
        tagText.setTextColor(getResources().getColor(R.color.white));
    }

    public void setUnChecked(TextView tagText){
        tagText.setBackgroundResource(R.drawable.shape_white);
        tagText.setTextColor(getResources().getColor(R.color.grey));
    }


    public void setDataList(List<String> data){
        setDataList(data,false);
    }

    public void setDataList(List<String> data,boolean isMulitfy){
        if(null == data){
            throw new NullPointerException("data is not be null");
        }
        this.data.clear();
        this.data = data;
        this.isMulitfy = isMulitfy;

        for(int i=0;i<data.size();i++){
            if(i == checkedIndex){
                stateMap.put(i,true);
            }else{
                stateMap.put(i,false);
            }
        }
        initView(context);
    }


    public void setOnSingleChanedListener(OnSingleChanedListener onSingleChanedListener){
        this.onSingleChanedListener = onSingleChanedListener;
    }
    public void setOnMultifyChanedListenerr(OnMultifyChanedListener onMultifyChanedListener){
        this.onMultifyChanedListener = onMultifyChanedListener;
    }

   public interface OnSingleChanedListener{
        void onSingleChaned(int index);
    }

    public interface OnMultifyChanedListener{
        void onMultifyChaned(List<Integer> indexs);
    }
}
