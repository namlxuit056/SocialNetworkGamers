package namlxuit.socialnetworkgamers.UI.Chat.RoomChat.Presenter;

import namlxuit.socialnetworkgamers.UI.Chat.RoomChat.Interactor.ChatRoomRoomInteractor;

/**
 * Created by namlxuit on 24/08/2016.
 */

public class ChatRoomRoomPresenter implements ChatRoomPresenterOps {
    private final ChatRoomRoomInteractor chatRoomInteractor;

    public ChatRoomRoomInteractor getChatRoomInteractor() {
        return chatRoomInteractor;
    }

    public ChatRoomRoomPresenter() {
        this.chatRoomInteractor = new ChatRoomRoomInteractor();
    }

    @Override
    public void sendMessage(String userName,String message) {
        chatRoomInteractor.pushMessageToFirebase(userName,message);
    }
}
