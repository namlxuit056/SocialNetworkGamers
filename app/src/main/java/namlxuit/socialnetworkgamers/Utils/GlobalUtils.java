package namlxuit.socialnetworkgamers.Utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by namlxuit on 01/09/2016.
 */

public class GlobalUtils {
   public static final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
}
