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

import nusamandiri.maps3.Activity.RuteAngkot.Stasiun_Ubhara;
import nusamandiri.maps3.Activity.RuteAngkot.Stasiun_Square;
import nusamandiri.maps3.Activity.RuteAngkot.Square_Ubhara;
import nusamandiri.maps3.R;

/**
 * Created by Mr.R on 4/29/2017.
 */

public class BerandaFragment extends Fragment {


    Object s3 ="3";
    Object s4 ="4";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_beranda, container, false);

        final MaterialSpinner spinner = (MaterialSpinner) fragmentView.findViewById(R.id.spinner3);
        spinner.setItems("Pilih Kampus Pertama", "BSI Square", "BSI Cut Meutia", "Universitas Bhayangkara", "Universitas Gunadarma", "STMIK Bani Shaleh", "Stasiun Bekasi");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {


                s3 = item.toString();
                Snackbar.make(view, item + " Telah Dipilih", Snackbar.LENGTH_LONG).show();

            }
        });
        final MaterialSpinner spinner1 = (MaterialSpinner) fragmentView.findViewById(R.id.spinner4);
        spinner1.setItems("Pilih Kampus Kedua", "BSI Square", "BSI Cut Meutia", "Universitas Bhayangkara", "Universitas Gunadarma", "STMIK Bani Shaleh", "Stasiun Bekasi");
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                s4 = item.toString();
                Snackbar.make(view, item + " Telah Dipilih", Snackbar.LENGTH_LONG).show();
            }
        });

        Button btcarirute = (Button) fragmentView.findViewById(R.id.btn_carirute);
        btcarirute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.btn_carirute) {
                    if (s3.equals("3") && s4.equals("4")) {

                        Snackbar.make(view, "Silakan Pilih Tujuan", Snackbar.LENGTH_LONG).show();

                    }
                    else if (s3.equals("Stasiun Bekasi") && s4.equals("BSI Square")) {

                        Intent MainActivity
                                = new Intent(getActivity(), Stasiun_Square.class);
                        startActivity(MainActivity);

                    }
                    else if (s3.equals("BSI Square") && s4.equals("Stasiun Bekasi")) {

                        Intent MainActivity
                                = new Intent(getActivity(), Stasiun_Square.class);
                        startActivity(MainActivity);

                    }
                    else if (s3.equals("BSI Square") && s4.equals("Universitas Bhayangkara")) {

                            Intent MainActivity
                                    = new Intent(getActivity(), Square_Ubhara.class);
                            startActivity(MainActivity);

                    }
                    else if (s3.equals("Universitas Bhayangkara") && s4.equals("BSI Square")) {

                        Intent MainActivity
                                = new Intent(getActivity(), Square_Ubhara.class);
                        startActivity(MainActivity);

                    }
                    else if (s3.equals("Stasiun Bekasi") && s4.equals("Universitas Bhayangkara")) {

                        Intent MainActivity
                                = new Intent(getActivity(), Stasiun_Ubhara.class);
                        startActivity(MainActivity);

                    }
                    else if (s3.equals("Universitas Bhayangkara") && s4.equals("Stasiun Bekasi")) {

                        Intent MainActivity
                                = new Intent(getActivity(), Stasiun_Ubhara.class);
                        startActivity(MainActivity);

                    }
                    else {

                        Snackbar.make(view, "Tujuan Tidak Boleh Sama", Snackbar.LENGTH_LONG).show();
                    }

                }
                else {
                    Snackbar.make(view, "Gagal", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return fragmentView;
    }
}
