package namlxuit.socialnetworkgamers.UI.Chat.RoomChat.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import namlxuit.socialnetworkgamers.Adapter.RoomChatAdapter;
import namlxuit.socialnetworkgamers.Model.RoomChat;
import namlxuit.socialnetworkgamers.R;
import namlxuit.socialnetworkgamers.UI.Chat.RoomChat.Presenter.ChatRoomRoomPresenter;
import namlxuit.socialnetworkgamers.Utils.GlobalUtils;

public class RoomChatActivity extends AppCompatActivity {
    EditText editTextRoomChatName;
    Button btnAddRoomChat;

    ChatRoomRoomPresenter chatRoomPresenter;
    ListView lvRoomChat;
    ArrayAdapter<RoomChat> adapterRoomChat;
    ArrayList<RoomChat> listRoomChat;
    DatabaseReference roomChatRef;
    String userName;
    String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnAddRoomChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put(editTextRoomChatName.getText().toString(),"");
                GlobalUtils.mRootRef.updateChildren(map);
            }
        });
        lvRoomChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChatRoomActivity.class);
                intent.putExtra("roomName",((TextView)view.findViewById(R.id.txtRoomName)).getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        editTextRoomChatName  = (EditText) findViewById(R.id.editTextRoomChatName);
        btnAddRoomChat  = (Button) findViewById(R.id.btnAddRoomChat);
        lvRoomChat = (ListView) findViewById(R.id.lvRoomChat);
        listRoomChat  = new ArrayList<RoomChat>();
        adapterRoomChat  = new RoomChatAdapter(this,
                R.layout.room_chat,
                listRoomChat);
        lvRoomChat.setAdapter(adapterRoomChat);
        requestUserName();
        roomChatRef = GlobalUtils.mRootRef.getRoot();

    }

    private void requestUserName() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Enter Name");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                userName=  inputField.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                requestUserName();
            }
        });
        builder.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        roomChatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<RoomChat> set = new HashSet<RoomChat>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()){
                    set.add(new RoomChat((((DataSnapshot)i.next()).getKey()),null,0,0));
                }
                listRoomChat.clear();
                listRoomChat.addAll(set);
                adapterRoomChat.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
