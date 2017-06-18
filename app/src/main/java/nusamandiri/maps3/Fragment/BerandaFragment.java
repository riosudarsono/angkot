package nusamandiri.maps3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaredrummler.materialspinner.MaterialSpinner;

import nusamandiri.maps3.R;

/**
 * Created by Mr.R on 4/29/2017.
 */

public class BerandaFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_beranda, container, false);

        MaterialSpinner spinner = (MaterialSpinner) fragmentView.findViewById(R.id.spinner3);
        spinner.setItems("Pilih Kampus Pertama", "BSI Square", "BSI Cut Meutia", "Universitas Bhayangkara", "Universitas Gunadarma", "STMIK Bani Shaleh", "Stasiun Bekasi");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, item + " Telah Dipilih", Snackbar.LENGTH_LONG).show();
            }
        });
        MaterialSpinner spinner1 = (MaterialSpinner) fragmentView.findViewById(R.id.spinner4);
        spinner1.setItems("Pilih Kampus Kedua", "BSI Square", "BSI Cut Meutia", "Universitas Bhayangkara", "Universitas Gunadarma", "STMIK Bani Shaleh", "Stasiun Bekasi");
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, item + " Telah Dipilih", Snackbar.LENGTH_LONG).show();
            }
        });

        Button btcarirute = (Button) fragmentView.findViewById(R.id.btn_carirute);
        btcarirute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent MainActivity
                        = new Intent(getActivity(), nusamandiri.maps3.Activity.MainActivity.class);
                startActivity(MainActivity);
            }
        });

        return fragmentView;
    }
}
