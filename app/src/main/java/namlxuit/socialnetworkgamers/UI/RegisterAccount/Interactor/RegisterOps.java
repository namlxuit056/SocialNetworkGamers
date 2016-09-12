package namlxuit.socialnetworkgamers.UI.RegisterAccount.Interactor;

import java.util.Map;
import java.util.Objects;

/**
 * Created by namlxuit on 25/08/2016.
 */

public interface RegisterOps {
    public void receiveRegisterRequest(String email, String password);
    public Map<String,Objects> createUser(String email,String password);
}
