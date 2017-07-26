package nusamandiri.maps3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nusamandiri.maps3.Activity.DetailKampus.BSI_CutMeutia;
import nusamandiri.maps3.Activity.DetailKampus.BSI_Square;
import nusamandiri.maps3.Activity.DetailKampus.Universitas_Bhayangkara;
import nusamandiri.maps3.Activity.DetailKampus.Universitas_Gunadarma;
import nusamandiri.maps3.Activity.DetailKampus.Universitas_Islam_45;
import nusamandiri.maps3.R;

/**
 * Created by Mr.R on 5/1/2017.
 */

public class InfoKampusFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_infokampus, container, false);

        Button btnsquare = (Button) fragmentView.findViewById(R.id.detail_square);
        btnsquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.detail_square) {
                    Intent MainActivity
                            = new Intent(getActivity(), BSI_Square.class);
                    startActivity(MainActivity);
                }
                }});

        Button btnubhara = (Button) fragmentView.findViewById(R.id.detail_ubhara);
        btnubhara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.detail_ubhara) {
                    Intent MainActivity
                            = new Intent(getActivity(), Universitas_Bhayangkara.class);
                    startActivity(MainActivity);
                }
            }});

        Button btnunisma = (Button) fragmentView.findViewById(R.id.detail_unisma);
        btnunisma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.detail_unisma) {
                    Intent MainActivity
                            = new Intent(getActivity(), Universitas_Islam_45.class);
                    startActivity(MainActivity);
                }
            }});

        Button btngundar = (Button) fragmentView.findViewById(R.id.detail_gundar);
        btngundar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.detail_gundar) {
                    Intent MainActivity
                            = new Intent(getActivity(), Universitas_Gunadarma.class);
                    startActivity(MainActivity);
                }
            }});

        Button btnmeutia = (Button) fragmentView.findViewById(R.id.detail_meutia);
        btnmeutia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.detail_meutia) {
                    Intent MainActivity
                            = new Intent(getActivity(), BSI_CutMeutia.class);
                    startActivity(MainActivity);
                }
            }});

        return fragmentView;
    }
}
