package namlxuit.socialnetworkgamers.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import namlxuit.socialnetworkgamers.Model.User;
import namlxuit.socialnetworkgamers.R;

/**
 * Created by namlxuit on 09/09/2016.
 */
//ArrrayList
    //ArrayApater
    //listView
public class MessageChatAdapter extends ArrayAdapter<User> {
    Context context;
    int resource;
    List<User> objects;
    public MessageChatAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource  = resource;
        this.objects  = objects;
    }


    //customView

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context,resource,null);
        CircleImageView imgAvatar = (CircleImageView) convertView.findViewById(R.id.imgAvatar);
        TextView txtTimeMessage = (TextView) convertView.findViewById(R.id.txtTimeMessage);
        TextView txtUser = (TextView) convertView.findViewById(R.id.txtUser);
        TextView txtMessege = (TextView) convertView.findViewById(R.id.txtMessage);
        User user = this.objects.get(position);
        txtUser.setText(user.getName());
        txtMessege.setText(user.getMessage());
        return convertView;
    }
}
