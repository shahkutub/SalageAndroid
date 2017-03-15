package com.salage;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.Utils.PersistData;
import com.salage.model.DatabaseHelper;
import com.salage.model.ProductTableInfo;
import com.salage.model.RowItem;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class ProductDetailsActivity extends AppCompatActivity{

    Context con;
    private View view;
    private ImageView dissmissCatListBtn,imgProd;
    TextView tvCodedPro,tvDescriptPro,tvLinkPdf,tvCat,tvSubCat,tvMarche,tvIva,tvSup,tvBarcode;
    private ProgressDialog pDialog;
    DatabaseHelper db;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    // File url to download
    private static String file_url = "",urlPdf="";
    List<ProductTableInfo> plist = new ArrayList<>();
    List<RowItem> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.product_details);
        con=this;
        initUi();
    }

    private void initUi() {
        db = new DatabaseHelper(con);
        plist = db.getAllProducts();

        //items = PersistData.getStringArrayPref(con,"urls");
        imgProd = (ImageView) findViewById(R.id.imgProd);
        for(int i = 0; i<plist.size();i++){
            if(AppConstant.productTableInfo.getPROD_CODE().
                    equalsIgnoreCase(plist.get(i).getPROD_CODE())){
                //imgProd.setImageBitmap(AppConstant.rowItem.get(i).getBitmapImage());

               // imgProd.setImageBitmap(AppConstant.rowItem.get(i).getBitmapImage());
            }
        }

        //Picasso.with(con).load(AppConstant.rowItem.get(0).getBitmapImage()).into(imgProd);
        //new ImageLoadTask(file_url, imgProd).execute();

        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

       // imgProd.setImageBitmap(getBitmapFromURL(file_url));
        tvCodedPro = (TextView) findViewById(R.id.tvCodedPro);
        tvDescriptPro = (TextView) findViewById(R.id.tvDescriptPro);
        tvLinkPdf = (TextView) findViewById(R.id.tvLinkPdf);
        tvLinkPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i<plist.size();i++){
                    if(AppConstant.productTableInfo.getPROD_CODE().
                            equalsIgnoreCase(plist.get(i).getPROD_CODE())){

                        OutputStream out = null;
                        try {
                            out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"out.pdf");
                            out.write(AppConstant.pdfFileItem.get(i).getPdf());
                            out.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

////                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConstant.pdfFileItem.get(i).getPdf()));
////                        startActivity(browserIntent);
//                        imgProd.setImageBitmap(AppConstant.rowItem.get(i).getBitmapImage());
                    }
                }

            }
        });
        tvCat = (TextView) findViewById(R.id.tvCat);
        tvSubCat = (TextView) findViewById(R.id.tvSubCat);

        tvMarche = (TextView) findViewById(R.id.tvMarche);
        tvIva = (TextView) findViewById(R.id.tvIva);
        tvSup = (TextView) findViewById(R.id.tvSup);
        tvBarcode = (TextView) findViewById(R.id.tvBarcode);


        tvCodedPro.setText("codice prodotti:" + AppConstant.productTableInfo.getPROD_CODE());
        tvDescriptPro.setText("discrizion prodotti:" + AppConstant.productTableInfo.getPROD_DESCRIPTION());
        if (!TextUtils.isEmpty(AppConstant.productTableInfo.getPROD_PDF())) {
            tvLinkPdf.setTextColor(Color.parseColor("#4059BA"));
        }
        tvLinkPdf.setText("link pdf:");
        tvCat.setText("classe:" + AppConstant.productTableInfo.getCATE_ID());
        tvSubCat.setText("sottoclasse:" + AppConstant.productTableInfo.getSUBC_ID());
        tvMarche.setText("marche:" + AppConstant.productTableInfo.getBRAN_ID());
        tvIva.setText("IVA Code:" + AppConstant.productTableInfo.getVATT_ID());
        tvSup.setText("id supp:" + AppConstant.productTableInfo.getSUPP_ID());
        tvBarcode.setText("Barcode:");
    }


    /**
     * Showing Dialog
     * */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
            // setting downloaded into image view
            imgProd.setImageDrawable(Drawable.createFromPath(imagePath));
        }

    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //imageView.setImageBitmap(result);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            result.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(con.getContentResolver(), result, "Title", null);
            Picasso.with(con).load(path).into(imageView);
           // return Uri.parse(path);
        }

    }

}
