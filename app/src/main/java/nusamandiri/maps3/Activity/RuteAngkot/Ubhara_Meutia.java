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

public class Ubhara_Meutia extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private TextView mTextMessage;

    GoogleMap mGoogleMap;
    Polyline line;

    private String serverKey = "AIzaSyBk8bt23ytMHcsc5cTCT7b6UmWYqjhseTQ";
    private LatLng camera = new LatLng(-6.237171, 107.004332);


    private LatLng origin2 = new LatLng(-6.249777, 107.015343);//Terminal1
    private LatLng destination2 = new LatLng(-6.256568, 107.005368);//depan unisma

    private LatLng origin3 = new LatLng(-6.2598283,107.0028272);    //depan bsi cut meutia

    private LatLng origin4 = new LatLng(-6.2360952,107.0003635);//depan stasiun
    private LatLng destination4 = new LatLng(-6.224436, 107.009457);//depan Universitas bhayangkara



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServiceAvailable()) {
            Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_ubhara__meutia);
            initMap();
        } else {
            Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show();
            //Tidak ada google maps layout
        }
        mTextMessage = (TextView) findViewById(R.id.message_ubhara_meutia);


        btn1 = (Button) findViewById(R.id.btn1_ubhara_meutia);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.btn2_ubhara_meutia);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.btn3_ubhara_meutia);
        btn3.setOnClickListener(this);

        btn4 = (Button) findViewById(R.id.btn4_ubhara_meutia);
        btn4.setOnClickListener(this);

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
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment_ubhara_meutia);
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
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 14));

        LatLng kampus1 = new LatLng(-6.223529, 107.008953);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus1)
                .title("Universitas Bhayangkara")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );

        LatLng kampus2 = new LatLng(-6.259806, 107.003073);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus2)
                .title("BSI Cut Meutia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1_ubhara_meutia){
            requestDirection1();
            mTextMessage.setText("Angkot 09\ndepan Universitas Bhayangkara - depan Stasiun Bekasi \nRp 3.000");
        }
        else if (id == R.id.btn2_ubhara_meutia){
            requestDirection2();
            mTextMessage.setText("Angkot 15A\nStasiun Bekasi - UNISMA \nRp 5.000");
        }
        else if (id == R.id.btn3_ubhara_meutia){
            requestDirection3();
            mTextMessage.setText("Transit Angkot 09 - 15A\ndepan Stasiun Bekasi");
        }

        else if (id == R.id.btn4_ubhara_meutia){
            requestDirection4();
            mTextMessage.setText("Jalan Kaki / Angkot 11 (Rp. 1.000) \nUNISMA - BSI Cut Meutia");
        }
    }

    public void requestDirection1() {
        Snackbar.make(btn2, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(destination4)
                .to(origin4)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);

        mGoogleMap.addMarker(new MarkerOptions()
                .position(destination4)
                .title("Angkot 09")
                .snippet("Depan Universitas Bhayangkara")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination4, 14));
    }


    public void requestDirection2() {
        Snackbar.make(btn2, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin4)
                .to(origin2)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);
        GoogleDirection.withServerKey(serverKey)
                .from(origin2)
                .to(destination2)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);

        mGoogleMap.addMarker(new MarkerOptions()
                .position(destination2)
                .title("Angkot K15")
                .snippet("Depan Unisma")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination2, 14));
    }

    public void requestDirection3() {
        Snackbar.make(btn4, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin4)
                .title("Transit Angkot 09 - 15A")
                .snippet("Depan Stasiun Bekasi")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin4, 15));


    }

    public void requestDirection4() {
        Snackbar.make(btn4, "Permintaan Sedang DIPROSES...", Snackbar.LENGTH_SHORT).show();
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin3)
                .title("Jalan Kaki / Angkot 11")
                .snippet("Depan BSI Cut Meutia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_jalan))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin3, 15));
        PolylineOptions jalan  = new PolylineOptions()
                .add(origin3)
                .add(destination2)
                .color(Color.GREEN)
                .width(7);
        line =mGoogleMap.addPolyline(jalan);

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        Snackbar.make(btn2, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        Snackbar.make(btn4, "Status Permintaan : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();

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