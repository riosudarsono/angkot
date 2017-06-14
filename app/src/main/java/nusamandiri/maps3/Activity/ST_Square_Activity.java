package nusamandiri.maps3.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nusamandiri.maps3.R;

public class ST_Square_Activity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    GoogleMap mGoogleMap;
    private String serverKey = "AIzaSyBk8bt23ytMHcsc5cTCT7b6UmWYqjhseTQ";
    private LatLng camera = new LatLng(-6.220068, 107.005191);

    private LatLng origin1 = new LatLng(-6.236101, 107.000380);
    private LatLng destination1 = new LatLng(-6.203150, 107.002980);

    private LatLng origin2 = new LatLng(-6.227078, 107.005963);
    private LatLng destination2 = new LatLng(-6.203150, 107.002980);

    private LatLng origin4 = new LatLng(-6.224458, 107.009488);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st__square_);
        initMap();


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

        LatLng stasiun = new LatLng(-6.236722, 106.999387);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(stasiun)
                .title("BSI Square")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_stasiun))
        );
        LatLng kampus = new LatLng(-6.206071, 107.015447);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus)
                .title("Universitas Bhayangkara")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }
}
