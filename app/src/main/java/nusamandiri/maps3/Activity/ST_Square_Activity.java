package nusamandiri.maps3.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
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

import java.util.ArrayList;

import nusamandiri.maps3.R;

public class ST_Square_Activity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private TextView mTextMessage;
    GoogleMap mGoogleMap;
    private String serverKey = "AIzaSyBk8bt23ytMHcsc5cTCT7b6UmWYqjhseTQ";
    private LatLng camera = new LatLng(-6.220068, 107.005191);

    private LatLng origin1 = new LatLng(-6.236101, 107.000380);
    private LatLng destination1 = new LatLng(-6.203150, 107.002980);

    private LatLng origin2 = new LatLng(-6.206557, 107.015297);
    private LatLng destination2 = new LatLng(-6.203150, 107.002980);

    private LatLng origin4 = new LatLng(-6.224458, 107.009488);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st__square_);
        initMap();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment_st_square);
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

        GoogleDirection.withServerKey(serverKey)
                .from(origin1)
                .to(destination1)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);

        GoogleDirection.withServerKey(serverKey)
                .from(origin2)
                .to(destination2)
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);

        LatLng stasiun = new LatLng(-6.236722, 106.999387);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(stasiun)
                .title("Stasiun Bekasi")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_stasiun))
        );
        LatLng kampus = new LatLng(-6.206071, 107.015447);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus)
                .title("BSI SQUARE")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        if (direction.isOK()) {


            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            mGoogleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.BLUE));

        }

    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_rute:
                    mTextMessage.setText("Total Ongkos Perjalanan \n Rp. 8.000");

                    return true;
                case R.id.navigation_angkot15a:
                    mTextMessage.setText("ST. Bekasi => POM Bensin Permata \n Rp. 5.000");
                    requestDirection1();
                    return true;
                case R.id.navigation_angkot45:
                    mTextMessage.setText("BSI Square => POM Bensin Permata \n Rp 3.000");
                    requestDirection2();
                    return true;
                case R.id.navigation_transit:
                    mTextMessage.setText("Dari Angkot 15A ke Angkot 45" +
                            "\nDari Angkot 45   ke Angkot 15A");
                    requestDirection3();
                    return true;
            }
            return false;
        }

    };



    public void requestDirection1() {

        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin1)
                .title("Angkot 45")
                .snippet("depan BSI Square")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin1, 14));


    }

    public void requestDirection2() {

        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin2)
                .title("Angkot 15A")
                .snippet("depan Stasiun Bekasi")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
        );

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin2, 14));


    }

    public void requestDirection3() {

        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin2)
                .title("Angkot 15A")
                .snippet("depan Stasiun Bekasi")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_angkot))
                );

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin2, 14));


    }
}
