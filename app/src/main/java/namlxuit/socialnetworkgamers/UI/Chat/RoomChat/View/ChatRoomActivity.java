package namlxuit.socialnetworkgamers.UI.Chat.RoomChat.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import namlxuit.socialnetworkgamers.Adapter.MessageChatAdapter;
import namlxuit.socialnetworkgamers.Model.User;
import namlxuit.socialnetworkgamers.R;
import namlxuit.socialnetworkgamers.UI.Chat.RoomChat.Presenter.ChatRoomRoomPresenter;

/**
 * Created by namlxuit on 07/09/2016.
 */
public class ChatRoomActivity extends AppCompatActivity {
    Button btnSendMessage;
    ArrayList<User> listMessage;
    ListView lvShowMessage;
    MessageChatAdapter messageChatAdapter;

    EditText editTextMessage;
    private ChatRoomRoomPresenter chatRoomPresenter;
    private String userName,roomName;
    String chatMessage, chatUserName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatRoomPresenter.sendMessage(userName,editTextMessage.getText().toString());
                editTextMessage.setText("");
            }
        });

    }

    private void addControls() {
        btnSendMessage = (Button) findViewById(R.id.btnSendMessage);
        lvShowMessage = (ListView) findViewById(R.id.lvShowMessage);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        userName = getIntent().getExtras().get("userName").toString();
        roomName = getIntent().getExtras().get("roomName").toString();
        setTitle("Room - "+ roomName);
        chatRoomPresenter = new ChatRoomRoomPresenter();
        chatRoomPresenter.getChatRoomInteractor().getRoomChat().setName(roomName);
        chatRoomPresenter.getChatRoomInteractor().getUser().setName(userName);
        listMessage = new ArrayList<User>();
        messageChatAdapter = new MessageChatAdapter(this,
                R.layout.item_room_chat_left,
                listMessage);
        lvShowMessage.setAdapter(messageChatAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        chatRoomPresenter.getChatRoomInteractor().getRoomChat().getRoomChatRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void appendChatConversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        Set<User> set = new HashSet<User>();
        while (i.hasNext()){
            chatMessage = (String)((DataSnapshot) i.next()).getValue();
            chatUserName = (String)((DataSnapshot) i.next()).getValue();
            set.add(new User(null,chatUserName,chatMessage));
        }
        //listMessage.clear();
        listMessage.addAll(set);
        messageChatAdapter.notifyDataSetChanged();
    }
}
