package com.yee.jobhunting.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.yee.jobhunting.R;
import com.yee.jobhunting.adapter.MessageAdapter;
import com.yee.jobhunting.bean.Message;
import com.yee.jobhunting.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    private List<Message> messageList = new ArrayList<>();
    private RecyclerView messageRecyclerview;

    @Override
    public void onResume() {
        super.onResume();
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_messaage, container, false);
        messageRecyclerview = v.findViewById(R.id.message_recyclerview);

        initMessages();
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //调用标题栏方法，修改标题名
        ViewUtils.initTitleBar(getActivity(),"消息");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        messageRecyclerview.setLayoutManager(layoutManager);
        MessageAdapter adapter = new MessageAdapter(messageList);
        messageRecyclerview.setAdapter(adapter);
    }


    private void initMessages() {

        for (int i=0;i<4;i++) {

            messageList.add(new Message(R.drawable.image_hr01,"周女士","7月6日",
                    "您好，可以发一份简历过来嘛？"));
            messageList.add(new Message(R.drawable.image_hr02,"郑先生","7月5日",
                    "您好，有兴趣聊一下嘛？"));
            messageList.add(new Message(R.drawable.image_hr03,"郑先生","7月4日",
                    "下次再聊"));
            messageList.add(new Message(R.drawable.image_hr02,"陈女士","7月3日",
                    "找到心仪的工作了吗？"));
            messageList.add(new Message(R.drawable.image_hr01,"李先生","7月3日",
                    "有兴趣加入我们公司吗？"));

        }

    }

}
