package it.jaschke.alexandria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by jacob on 9/9/15.
 */
public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {

    private final String LOG_TAG = SimpleScannerActivity.class.getSimpleName();
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Intent resultIntent = new Intent();
        resultIntent.putExtra("EAN", rawResult.getText());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
        Log.v(LOG_TAG, rawResult.getText()); // Prints scan results
        Log.v(LOG_TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
    }
}