package nusamandiri.maps3.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import nusamandiri.maps3.R;

public class Stasiun_Ubhara extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private Button btn_angkot1_sk;
    private Button btn_angkot2_sk;
    private Button btn_angkot3_sk;
    private Button btn_angkot4_sk;

    private TextView mTextMessage;

    GoogleMap mGoogleMap;
    Polyline line;

    private String serverKey = "AIzaSyBk8bt23ytMHcsc5cTCT7b6UmWYqjhseTQ";
    private LatLng camera = new LatLng(-6.214973, 107.012137);

    private LatLng origin1 = new LatLng(-6.206557, 107.015281);
    private LatLng destination1 = new LatLng(-6.203150, 107.002980);
    //BSI Square - POM Bensin Permata

    private LatLng origin2 = new LatLng(-6.227078, 107.005963);
    private LatLng destination2 = new LatLng(-6.203150, 107.002980);
    //Bundaran Summarecon - POM Bensin Permata

    private LatLng origin4 = new LatLng(-6.224458, 107.009488);
    //Universitas Bhayangkara

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServiceAvailable()) {
            Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_square__ubhara);
            initMap();
        } else {
            Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show();
            //Tidak ada google maps layout
        }
        mTextMessage = (TextView) findViewById(R.id.message);

        btn_angkot1_sk = (Button) findViewById(R.id.btn_angkot1_SK);
        btn_angkot1_sk.setOnClickListener(this);

        btn_angkot2_sk = (Button) findViewById(R.id.btn_angkot2_SK);
        btn_angkot2_sk.setOnClickListener(this);

        btn_angkot3_sk = (Button) findViewById(R.id.btn_angkot3_SK);
        btn_angkot3_sk.setOnClickListener(this);

        btn_angkot4_sk = (Button) findViewById(R.id.btn_angkot4_SK);
        btn_angkot4_sk.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServiceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
        } else {
            Toast.makeText(this, "Tidak Terhubung ke Play Services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 15));

        LatLng kampus1 = new LatLng(-6.206071, 107.015447);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus1)
                .title("BSI Square")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );

        LatLng kampus2 = new LatLng(-6.223585, 107.008962);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus2)
                .title("Universitas Bhayangkara")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_angkot1_SK) {
            mTextMessage.setText("BSI Square - POM Bensin Permata \nRp 3.000");
            requestDirection1();
        }
        else if (id == R.id.btn_angkot2_SK){
            requestDirection2();
            mTextMessage.setText("Bundaran Summarecon - POM Bensin Permata \nRp 4.000");
        }
        else if (id == R.id.btn_angkot3_SK){
            requestDirection3();
            mTextMessage.setText("Transit Angkot \nDari 45 ke 15A \nDari 15A ke 45");
        }
        else if (id == R.id.btn_angkot4_SK){
            requestDirection4();
            mTextMessage.setText("Jalan Kaki / Angkot 09 (Rp. 1.000) \nBundaran Sumarecon - Universitas Bhayangkara");
        }
    }

    public void requestDirection1() {
        Snackbar.make(btn_angkot1_sk, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin1)
                .to(destination1)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin1)
                .title("Angkot 45")
                .snippet("depan BSI Square")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin1, 15));
    }

    public void requestDirection2() {
        Snackbar.make(btn_angkot1_sk, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin2)
                .to(destination2)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin2)
                .title("Angkot K15")
                .snippet("Bundaran Summarecon")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin2, 15));
    }

    public void requestDirection3() {
        Snackbar.make(btn_angkot3_sk, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        mGoogleMap.addMarker(new MarkerOptions()
                .position(destination1)
                .title("Transit Angkot ( 45 dan K15)")
                .snippet("SPBU Permata")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination1, 15));

    }

    public void requestDirection4() {
        Snackbar.make(btn_angkot1_sk, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin4)
                .title("Jalan Kaki")
                .snippet("Depan Univ.Bhayangkara")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_jalan))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin4, 15));
        PolylineOptions jalan  = new PolylineOptions()
                .add(origin4)
                .add(origin2)
                .color(Color.GREEN)
                .width(7);
        line =mGoogleMap.addPolyline(jalan);

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        Snackbar.make(btn_angkot1_sk, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(btn_angkot2_sk, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(btn_angkot3_sk, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(btn_angkot4_sk, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();

        if (direction.isOK()) {

            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            mGoogleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.BLUE));
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show();
    }

}