package com.example.devansh.inclass07;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectAvatarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SelectAvatarFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    public SelectAvatarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_avatar, container, false);
        Log.d("testt", "onCreateView: WE ARE HEREEEE");
        getActivity().setTitle("Select Avatar");

        view.findViewById(R.id.imgBoy1).setOnClickListener(this);
        view.findViewById(R.id.imgBoy2).setOnClickListener(this);
        view.findViewById(R.id.imgBoy3).setOnClickListener(this);
        view.findViewById(R.id.imgGirl1).setOnClickListener(this);
        view.findViewById(R.id.imgGirl2).setOnClickListener(this);
        view.findViewById(R.id.imgGirl3).setOnClickListener(this);


        /*
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int num = view.getId();
                Log.d("testt", "onClick: something clicked" );
                //getActivity().getSupportFragmentManager().popBackStack();
            }
        });*/

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
    public void onClick(View view) {
        Log.d("test", "An avatar was clicked!");

        Log.d("test", "onClick: " + view.getId());

        switch(view.getId()) {
            case R.id.imgBoy1:
                mListener.setIconId(R.drawable.avatar_m_1);
                break;
            case R.id.imgBoy2:
                mListener.setIconId(R.drawable.avatar_m_2);
                break;
            case R.id.imgBoy3:
                mListener.setIconId(R.drawable.avatar_m_3);
                break;
            case R.id.imgGirl1:
                mListener.setIconId(R.drawable.avatar_f_1);
                break;
            case R.id.imgGirl2:
                mListener.setIconId(R.drawable.avatar_f_2);
                break;
            case R.id.imgGirl3:
                mListener.setIconId(R.drawable.avatar_f_3);
                break;
        }

        getActivity().getSupportFragmentManager().popBackStack();
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
        void setIconId(int iconId);
    }
}
