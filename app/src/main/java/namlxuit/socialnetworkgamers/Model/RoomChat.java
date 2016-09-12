package namlxuit.socialnetworkgamers.Model;

import com.google.firebase.database.DatabaseReference;

import namlxuit.socialnetworkgamers.Utils.GlobalUtils;

/**
 * Created by namlxuit on 08/09/2016.
 */

public class RoomChat {
    private String id;
    private String name;
    private String image;
    private int counterOnline;
    private int counterViewer;
    DatabaseReference roomChatRef;

    public RoomChat(String name, String image, int counterOnline, int counterViewer) {
        this.name = name;
        this.image = image;
        this.counterOnline = counterOnline;
        this.counterViewer = counterViewer;
    }

    public int getCounterOnline() {
        return counterOnline;
    }

    public void setCounterOnline(int counterOnline) {
        this.counterOnline = counterOnline;
    }

    public int getCounterViewer() {
        return counterViewer;
    }

    public void setCounterViewer(int counterViewer) {
        this.counterViewer = counterViewer;
    }

    public  RoomChat(){
        counterOnline = 0;
        counterViewer = 0;

    }
    public RoomChat(String name) {
        this.name = name;
    }

    public DatabaseReference getRoomChatRef() {
        return roomChatRef;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        roomChatRef = GlobalUtils.mRootRef.child(name);
    }

}
