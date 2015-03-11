package com.android.vk_gram;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.dialogs.VKCaptchaDialog;


public class MainActivity extends ActionBarActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKUIHelper.onCreate(this);
        VKSdk.initialize(sdkListener, VKGramApplication.APP_ID, VKAccessToken.tokenFromSharedPreferences(this, VKGramApplication.APP_KEY_TOKEN));
        VKGramApplication.activity = this;
        VKSdk.authorize(VKGramApplication.APP_SCOPE,true,false);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())

                    .commit();
        }





    }


    public final VKSdkListener sdkListener = new VKSdkListener() {
        @Override
        public void onCaptchaError(VKError captchaError) {
            new VKCaptchaDialog(captchaError).show();
        }

        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            VKSdk.authorize(VKGramApplication.APP_SCOPE);
        }

        @Override
        public void onAccessDenied(VKError authorizationError) {

            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(authorizationError.toString())
                    .show();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        VKUIHelper.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKUIHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        public final VKSdkListener sdkListener = new VKSdkListener() {
            @Override
            public void onCaptchaError(VKError captchaError) {
                new VKCaptchaDialog(captchaError).show();
            }

            @Override
            public void onTokenExpired(VKAccessToken expiredToken) {
                VKSdk.authorize(VKGramApplication.APP_SCOPE);
            }

            @Override
            public void onAccessDenied(VKError authorizationError) {
                try {
                    new AlertDialog.Builder(VKGramApplication.activity)
                            .setMessage(authorizationError.toString())
                            .show(); } catch (Exception e ){}
            }
        };
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {




            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);








           final TextView result = ((TextView) rootView.findViewById(R.id.first_request_result));
            final Button getResult = (Button)rootView.findViewById(R.id.getResult);
            final Button auth = (Button)rootView.findViewById(R.id.authorize);


            auth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VKSdk.authorize(VKGramApplication.APP_SCOPE,true,true);
                }
            });
            getResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    APIUtil.getMyselfInfo(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);

                            result.setText(response.json.toString());
                            Log.d("RESPONSE",response.json.toString());
                        }
                    });


                }
            });






            return rootView;
        }
    }
}
