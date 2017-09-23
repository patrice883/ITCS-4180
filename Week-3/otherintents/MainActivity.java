package itcs4180.otherintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simple intent
        // Doesn't require any permissions
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uncc.edu"));
        startActivity(intent);

        // This one requires permission from user!!!
        // TO make a phone call
        Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7046877476"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent1); // Will give an error. need to add something (the blob above
                                // was added automatically :'D )
    }
}
