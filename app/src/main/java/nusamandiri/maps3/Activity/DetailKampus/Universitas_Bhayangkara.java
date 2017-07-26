package nusamandiri.maps3.Activity.DetailKampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;

import nusamandiri.maps3.R;

public class Universitas_Bhayangkara extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universitas__bhayangkara);

        String htmlAsString = getString(R.string.ubhara);      // used by WebView
        Spanned htmlAsSpanned = Html.fromHtml(htmlAsString); // used by TextView

        WebView webView = (WebView) findViewById(R.id.visi_ubhara);
        webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);

    }
}
