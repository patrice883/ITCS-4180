package com.example.devansh.inclass07;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateNewContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateNewContactFragment extends Fragment {


    int profPicDraw = R.drawable.select_avatar;

    private OnFragmentInteractionListener mListener;

    public CreateNewContactFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_new_contact, container, false);
        //((ImageView) view.findViewById(R.id.imgAvatar)).setImageDrawable(getResources().getDrawable(R.drawable.select_avatar));
        getActivity().setTitle("Create New Contact");

        view.findViewById(R.id.imgAvatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test", "Setting Avatar!~~");
                //:DDDD
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentView, new SelectAvatarFragment(), "devansh_is_poopy")
                        .addToBackStack(null)
                        .commit();

            }
        });

        view.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                boolean image = true;
                boolean name = true;
                boolean email = true;
                String n = ((TextView)getActivity().findViewById(R.id.txtName)).getText().toString();
                String e = ((TextView)getActivity().findViewById(R.id.txtEmail)).getText().toString();
                String p = ((TextView)getActivity().findViewById(R.id.txtPhone)).getText().toString();
                String d = "SIS";

                profPicDraw = mListener.getIconId();

                //profPicDraw = some;


                RadioGroup rg = (RadioGroup) getActivity().findViewById(R.id.radGroupDept);
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
                    Toast.makeText(getContext(), "Please Enter a Valid Name without the leading or trailing spaces", Toast.LENGTH_SHORT).show();
                }
                if(n == null || n.length() == 0) {
                    name = false;
                    Toast.makeText(getContext(), "Please Enter a Valid Name that is Not Null", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getContext(), "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Log.d("Test", "I am stuck hereeeee");
                        email = false;
                        Toast.makeText(getContext(), "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                    }
                }


                // We are safe :D ~~~~~~~~~~~~~~~~~
                if(name && email) {
                    Log.d("Test", "I am stuck here33333");
                   // Intent intent = new Intent(MainActivity.this, DisplayActivity.class);




                    // Create Profile
                    Contact c = new Contact(n,e,p,d,profPicDraw);

                    Log.d("test", "WE FINALLY GOT HERE ... 1");

                    mListener.getList().add(c);
                    mListener.resetID();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        ((ImageView) getActivity().findViewById(R.id.imgAvatar)).setImageDrawable(getResources().getDrawable(mListener.getIconId()));

        getActivity().setTitle("Create New Contact");
        super.onResume();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        ArrayList<Contact> getList();
        int getIconId();
        void resetID();
    }
}
