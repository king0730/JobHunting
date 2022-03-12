package com.yee.jobhunting.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yee.jobhunting.R;
import com.yee.jobhunting.bean.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessageList;

    static public class ViewHolder extends RecyclerView.ViewHolder{
        View messageView;
        ImageView contactImage;
        TextView contactName,messageTime,lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageView = itemView;
            contactImage = itemView.findViewById(R.id.image_contact);
            contactName = itemView.findViewById(R.id.message_contact_name);
            messageTime = itemView.findViewById(R.id.message_time);
            lastMessage = itemView.findViewById(R.id.last_message);

        }
    }
    public MessageAdapter(List<Message> messageList) {
        mMessageList = messageList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Message message = mMessageList.get(position);
                Toast.makeText(v.getContext(),"点击"+message.getContactName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        holder.contactImage.setImageResource(message.getContactImageId());
        holder.contactName.setText(message.getContactName());
        holder.messageTime.setText(message.getMessageTime());
        holder.lastMessage.setText(message.getLastMessage());

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


}
