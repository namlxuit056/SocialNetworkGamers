package namlxuit.socialnetworkgamers.UI.Chat.RoomChat.Interactor;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import namlxuit.socialnetworkgamers.Model.RoomChat;
import namlxuit.socialnetworkgamers.Model.User;

/**
 * Created by namlxuit on 24/08/2016.
 */

public class ChatRoomRoomInteractor implements ChatRoomOps {
    User user;
    RoomChat roomChat;
    DatabaseReference messageRef;
    String tempKey;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ChatRoomRoomInteractor(User user, RoomChat roomChat) {
        this.user = user;
        this.roomChat = roomChat;
    }

    public RoomChat getRoomChat() {
        return roomChat;
    }

    public void setRoomChat(RoomChat roomChat) {
        this.roomChat = roomChat;
    }

    public ChatRoomRoomInteractor() {
        user  = new User();
        roomChat = new RoomChat();
    }

    public DatabaseReference getMessageRef() {
        return messageRef;
    }

    public void setMessageRef(DatabaseReference messageRef) {
        this.messageRef = messageRef;
    }

    @Override
    public void pushMessageToFirebase(String userName,String message) {
        Map<String,Object> map = new HashMap<String,Object>();
        tempKey = roomChat.getRoomChatRef().push().getKey();
        roomChat.getRoomChatRef().updateChildren(map);
        messageRef = roomChat.getRoomChatRef().child(tempKey);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name",userName);
        map2.put("message",message);
        messageRef.updateChildren(map2);

    }
}
