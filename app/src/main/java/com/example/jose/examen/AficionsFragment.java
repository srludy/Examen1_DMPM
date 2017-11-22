package com.example.jose.examen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AficionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AficionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AficionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RadioButton llibres;
    RadioButton xarxes;
    RadioButton revistes;
    RatingBar ratingBar;
    Button btnDesaDades;
    DesaDades desaDades;
    private OnFragmentInteractionListener mListener;

    public AficionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AficionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AficionsFragment newInstance(String param1, String param2) {
        AficionsFragment fragment = new AficionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_aficions, container, false);

        llibres = (RadioButton) v.findViewById(R.id.radioButtonLlibres);
        revistes = (RadioButton) v.findViewById(R.id.radioButtonRevistes);
        xarxes = (RadioButton) v.findViewById(R.id.radioButtonXarxes);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        btnDesaDades = (Button) v.findViewById(R.id.saveAficions);

        btnDesaDades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() > 0){
                    String numStars = String.valueOf(ratingBar.getRating());

                    if (!llibres.isChecked() && !revistes.isChecked() && !xarxes.isChecked()){
                        Toast.makeText(getContext(),"Selecciona una aficion.", Toast.LENGTH_SHORT).show();

                    }else{
                        String aficio = "";
                        if(llibres.isChecked()){
                            aficio = "Llibres";
                        }else{
                            if(xarxes.isChecked()){
                                aficio = "Xarxes";
                            }else{
                                aficio = "Revistes";
                            }
                        }

                        desaDades.desaDadesAficions(aficio, numStars);
                    }
                 }else{
                    Toast.makeText(getContext(),"Selecciona un numero de estrellas.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity a = null;

        if (context instanceof MainActivity){
            a=(MainActivity) context;
        }

        desaDades = (DesaDades) a;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(Uri uri);
    }
}
