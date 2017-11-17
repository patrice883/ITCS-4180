package com.example.devansh.inclass09;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateContact extends AppCompatActivity{

    static int REQ_CODE = 100;
    static String VALUE_KEY = "image";
    private ArrayList<Contact> contacts = new ArrayList<>();
    int profPicDraw = R.drawable.select_avatar;
    //private CreateNewContactFragment.OnFragmentInteractionListener mListener;

    //private int iconID = R.drawable.select_avatar;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        setTitle("Create New Contact");

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = database.getReference(user.getUid());


        /*
        contacts.add(new Contact("Dr. Shehab", "mshehab@uncc.edu", "Dr. Shehab Phone #", "CS", R.drawable.avatar_m_1));
        contacts.add(new Contact("Aileen Benedict", "abenedi3@uncc.edu", "XXX-XXX-XXXX", "CS", R.drawable.avatar_f_3));
        contacts.add(new Contact("Devansh Desai", "ddesai14@uncc.edu", "XXX-XXX-XXXX", "CS", R.drawable.avatar_m_2));
        contacts.add(new Contact("Sunand Pujari", "spujari@uncc.edu", "TA Phone 1", "CS", R.drawable.avatar_m_3));
        contacts.add(new Contact("Sai Mounika Pabbathireddy", "spabbath@uncc.edu", "TA PHONE 2", "CS", R.drawable.avatar_f_1));
        contacts.add(new Contact("Dummy Contact", "dummy@dummy.com", "dummyphone1", "SIS", R.drawable.avatar_f_2));

        Log.d("test", "onCreate: person = " + contacts.get(0).toString() + "\n" + contacts.get(0).image);
*/

        findViewById(R.id.imgAvatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test", "Setting Avatar!~~");
                //:DDDD
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.contentView2, new SelectAvatarFragment(), "devansh_is_poopy")
//                        .addToBackStack(null)
//                        .commit();

                Intent intent = new Intent(CreateContact.this, SelectAvatar.class);
                startActivityForResult(intent,REQ_CODE);


            }
        });

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                boolean image = true;
                boolean name = true;
                boolean email = true;
                String n = ((TextView)findViewById(R.id.txtName)).getText().toString();
                String e = ((TextView)findViewById(R.id.txtEmail)).getText().toString();
                String p = ((TextView)findViewById(R.id.txtPhone)).getText().toString();
                String d = "SIS";

                //profPicDraw = mListener.getIconId();

                //profPicDraw = some;


                RadioGroup rg = (RadioGroup)findViewById(R.id.radGroupDept);
                if(rg.getCheckedRadioButtonId() == R.id.radSIS){
                    d = "SIS";
                }
                else if(rg.getCheckedRadioButtonId() == R.id.radBIO){
                    d = "BIO";
                }
                else{
                    d = "CS";
                }


                if(!n.trim().equals(n)){
                    name = false;
                    Toast.makeText(CreateContact.this, "Please Enter a Valid Name without the leading or trailing spaces", Toast.LENGTH_SHORT).show();
                }
                if(n == null || n.length() == 0) {
                    name = false;
                    Toast.makeText(CreateContact.this, "Please Enter a Valid Name that is Not Null", Toast.LENGTH_SHORT).show();
                }

                if(name){
                    Log.d("Test", "I am stuck");


                    Log.d("Submit", "String is " + e);
                    Log.d("Test", "I am stuck here");
                    if(e.indexOf('@') == e.lastIndexOf('@') && e.indexOf('@') > 0){
                        Log.d("Test", "I am stuck here");
                        if(e.indexOf('.', e.indexOf('@') + 2)  > 0 && e.indexOf('.') == e.lastIndexOf('.')){
                            email = true;
                            Log.d("Test", "I am stuck heree");
                        }
                        else{
                            Log.d("Test", "I am stuck hereeee");
                            email = false;
                            Toast.makeText(CreateContact.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Log.d("Test", "I am stuck hereeeee");
                        email = false;
                        Toast.makeText(CreateContact.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                    }
                }


                // We are safe :D ~~~~~~~~~~~~~~~~~
                if(name && email) {
                    Log.d("Test", "I am stuck here33333");
                    // Intent intent = new Intent(MainActivity.this, DisplayActivity.class);




                    // Create Profile
                    Contact c = new Contact(n,e,p,d,profPicDraw);

                    Log.d("test", "WE FINALLY GOT HERE ... 1" + c.toString());
                    writeNewContact(user.getUid(), c);
                    Log.d("test", "WE FINALLY GOT HERE ... 2");
                    finish();
                }
            }
        });

    }

    private void writeNewContact(String userId, Contact c){
        //myRef.child("Users").child(userId).setValue(c);
        myRef.child("Contacts").push().setValue(c);
        //myRef.child("Users").child(userId).child("").setValue(c);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                int id = data.getExtras().getInt(VALUE_KEY);
                Log.d("test", "A value was received.");
                Log.d("test", "" + id);


                profPicDraw = id;
                switch (id) {
                    case Contact.GIRL1:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_1));
                        break;
                    case Contact.GIRL2:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_2));
                        break;
                    case Contact.GIRL3:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_f_3));
                        break;
                    case Contact.BOY1:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_1));
                        break;
                    case Contact.BOY2:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_2));
                        break;
                    case Contact.BOY3:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.avatar_m_3));
                        break;
                    default:
                        ((ImageView) findViewById(R.id.imgAvatar)).
                                setImageDrawable(getResources().getDrawable(R.drawable.select_avatar));
                        break;

                }

            } else if (resultCode == RESULT_CANCELED) {
                Log.d("test", "No value was received! :(");
            }
        }
    }
}

