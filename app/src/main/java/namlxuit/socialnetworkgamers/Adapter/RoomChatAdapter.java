package namlxuit.socialnetworkgamers.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import namlxuit.socialnetworkgamers.Model.RoomChat;
import namlxuit.socialnetworkgamers.R;

/**
 * Created by namlxuit on 08/09/2016.
 */

public class RoomChatAdapter extends ArrayAdapter<RoomChat> {
    Context context;
    int resource;
    List<RoomChat> objects;
    public RoomChatAdapter(Context context, int resource, List<RoomChat> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context,resource,null);
        ImageView imgRoomChat = (ImageView) convertView.findViewById(R.id.imgRoomChat);
        TextView txtRoomName = (TextView) convertView.findViewById(R.id.txtRoomName);
        TextView txtCounterOnline = (TextView) convertView.findViewById(R.id.txtCounterOnline);
        TextView txtCounterViewer = (TextView) convertView.findViewById(R.id.txtCounterViewer);
        TextView txtRim = (TextView) convertView.findViewById(R.id.txtRim);

       RoomChat roomChat = this.objects.get(position);
        txtRoomName.setText(roomChat.getName());
        txtCounterOnline.setText(roomChat.getCounterOnline() + " Online ");
        txtCounterViewer.setText(roomChat.getCounterViewer() + " Viewer ");
        return convertView;
    }
}
