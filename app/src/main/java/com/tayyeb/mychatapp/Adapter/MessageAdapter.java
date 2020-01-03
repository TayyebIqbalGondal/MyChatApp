package com.tayyeb.mychatapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tayyeb.mychatapp.Model.Chat;
import com.tayyeb.mychatapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
private Context context;
private List<Chat> chats;
private String imageurl;


FirebaseUser firebaseUser;

public MessageAdapter(Context context, List<Chat> chats,String imageurl){
        this.chats=chats;
        this.context=context;
        this.imageurl=imageurl;
        }

@NonNull
@Override
public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if(viewType==MSG_TYPE_LEFT) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
        return new MessageAdapter.ViewHolder(view);
    }
    else{
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
        return new MessageAdapter.ViewHolder(view);
    }
        }

@Override
public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

    Chat chat=chats.get(position);
    holder.show_message.setText(chat.getMessage());

    if(imageurl.equals("default")){
        holder.profile_image.setImageResource(R.mipmap.user_profile);
    }
    else{
        Glide.with(context).load(imageurl).into(holder.profile_image);
    }

    if(position==chats.size()-1){

        if(chat.isIsseen()){

            holder.txt_seen.setTextColor(Color.parseColor("#34B7F1"));
            holder.txt_seen.setText("Seen");
        }
        else{

            holder.txt_seen.setText("Delivered");
        }
    }
    else {
        holder.txt_seen.setVisibility(View.GONE);
    }



        }

@Override
public int getItemCount() {
        return chats.size();
        }

public  class ViewHolder extends RecyclerView.ViewHolder{

    public TextView show_message ;
    public ImageView profile_image;


    public TextView txt_seen;
    public CircleImageView unreadImg;

    public ViewHolder(View itemView){

        super(itemView);

        show_message=itemView.findViewById(R.id.show_message);
        profile_image=itemView.findViewById(R.id.profile_image);
        txt_seen=itemView.findViewById(R.id.txt_seen);

        unreadImg=itemView.findViewById(R.id.image_unread);

    }
}

    @Override
    public int getItemViewType(int position) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(chats.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_RIGHT;
        }
        else {
            return MSG_TYPE_LEFT;
        }
    }




}
