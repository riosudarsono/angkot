package nusamandiri.maps3.Activity.RuteAngkot;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
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

public class Unisma_Meutia extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private Button btn3;

    private TextView mTextMessage;

    GoogleMap mGoogleMap;
    Polyline line;

    private String serverKey = "AIzaSyBk8bt23ytMHcsc5cTCT7b6UmWYqjhseTQ";
    private LatLng camera = new LatLng(-6.258497, 107.004822);

    private LatLng origin4 = new LatLng(-6.2567419,107.0052318);//depan Unisma

    private LatLng origin1 = new LatLng(-6.2598283,107.0028272);//depan BSI cut meutia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServiceAvailable()) {
            Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_unisma__meutia);
            initMap();
        } else {
            Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show();
            //Tidak ada google maps layout
        }
        mTextMessage = (TextView) findViewById(R.id.message_unisma_meutia);

        btn3 = (Button) findViewById(R.id.btn3_unisma_meutia);
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
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment_unisma_meutia);
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
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 16));

        LatLng kampus1 = new LatLng(-6.257064, 107.006247);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus1)
                .title("Universitas Islam 45")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );

        LatLng kampus2 = new LatLng(-6.259811, 107.003053);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(kampus2)
                .title("BSI Cut Meutia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_kampus))
        );
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
       if (id == R.id.btn3_unisma_meutia){
            requestDirection3();
            mTextMessage.setText("Jalan Kaki / Angkot 11 (Rp 1.000)\ndepan BSI Cut Meutia - depan Universitas Islam 45");
        }


    }



    public void requestDirection3() {
        PolylineOptions jalan  = new PolylineOptions()
                .add(origin1)
                .add(origin4)
                .color(Color.GREEN)
                .width(7);
        line =mGoogleMap.addPolyline(jalan);

        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin4)
                .title("Jalan Kaki")
                .snippet("depan Universitas Islam 45")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_jalan))
        );
        mGoogleMap.addMarker(new MarkerOptions()
                .position(origin1)
                .title("Jalan Kaki")
                .snippet("depan BSI Cut Meutia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_jalan))
        );
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin1, 16));
    }





    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

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