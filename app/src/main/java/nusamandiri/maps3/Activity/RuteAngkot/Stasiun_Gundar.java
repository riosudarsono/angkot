package nusamandiri.maps3.Activity.RuteAngkot;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import nusamandiri.maps3.Activity.BantuanActivity;
import nusamandiri.maps3.R;

public class Stasiun_Gundar extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private Button btn1;
    private Button btn2;
    private Button btn3;


    private TextView mTextMessage;

    GoogleMap mGoogleMap;
    Polyline line;

    private String serverKey = "AIzaSyBk8bt23ytMHcsc5cTCT7b6UmWYqjhseTQ";
    private LatLng camera = new LatLng(-6.230230, 106.986033);


    private LatLng destination1 = new LatLng(-6.236083, 107.000366);//depan Stasiun bekasi

    private LatLng origin2 = new LatLng(-6.2492165,106.9703382);//depan gundar
    private LatLng destination2 = new LatLng(-6.2478832,106.9891995);//Pangkalan 26

    private LatLng origin3 = new LatLng(-6.2466019,106.9924656);//depan BCP
    private LatLng destination3 = new LatLng(-6.233966, 106.994401);//depan walikota





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServiceAvailable()) {
            Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_stasiun__gundar);
            initMap();
        } else {
            Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show();
            //Tidak ada google maps layout
        }
        mTextMessage = (TextView) findViewById(R.id.message_stasiun_gundar);

        btn1 = (Button) findViewById(R.id.btn1_stasiun_gundar);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.btn2_stasiun_gundar);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.btn3_stasiun_gundar);
        btn3.setOnClickListener(this);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beranda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bantuan) {

            Intent intent = new Intent(this, BantuanActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment_stasiun_gundar);
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
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 13));

        LatLng kampus1 = new LatLng(-6.236159, 106.999399);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus1)
                .title("Stasiun Bekasi")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_station))
        );

        LatLng kampus2 = new LatLng(-6.248764, 106.970491);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus2)
                .title("Universitas Gunadarma")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1_stasiun_gundar) {
            mTextMessage.setText("Angkot 09\nDepan Stasiun Bekasi - BCP (Bekasi Cyber Park)\nRp 4.000");
            requestDirection1();
        }
        else if (id == R.id.btn2_stasiun_gundar){
            requestDirection2();
            mTextMessage.setText("Angkot 26\ndepan Universitas Gunadarma - Pangkalan Angko26\nRp 3.000");
        }
        else if (id == R.id.btn3_stasiun_gundar){
            requestDirection3();
            mTextMessage.setText("Jalan Kaki\nPangkalan Angkot 26 - BCP (Bekasi Cyber Park)");
        }

    }

    public void requestDirection1() {
        Snackbar.make(btn1, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(destination1)
                .to(destination3)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);
        GoogleDirection.withServerKey(serverKey)
                .from(origin3)
                .to(destination3)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(destination1)
                .title("Angkot 09")
                .snippet("depan Stasiun Bekasi")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin3)
                .title("Angkot 09")
                .snippet("depan BCP (Bekasi Cyber Park))")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination1, 13));
    }

    public void requestDirection2() {
        Snackbar.make(btn1, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();

        GoogleDirection.withServerKey(serverKey)
                .from(origin2)
                .to(destination2)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);


        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin2)
                .title("Angkot 26")
                .snippet("Depan Universitas Gunadarma")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin2, 13));
    }



    public void requestDirection3() {
        Snackbar.make(btn3, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin3, 15));
        PolylineOptions jalan  = new PolylineOptions()
                .add(origin3)
                .add(destination2)
                .color(Color.GREEN)
                .width(7);
        line =mGoogleMap.addPolyline(jalan);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(destination2)
                .title("Jalan")
                .snippet("Pangkalan Angkot 26 - BCP")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_jalan))
        );

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        Snackbar.make(btn1, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(btn2, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(btn3, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();

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
