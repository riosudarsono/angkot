package nusamandiri.maps3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nusamandiri.maps3.Activity.MainActivity;
import nusamandiri.maps3.R;

/**
 * Created by Mr.R on 4/29/2017.
 */

public class BerandaFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_beranda, container, false);
        Button btcarirute = (Button) fragmentView.findViewById(R.id.btn_carirute);
        btcarirute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity
                        = new Intent(getActivity(),MainActivity.class);
                startActivity(MainActivity);
            }
        });
        return fragmentView;
    }
}
