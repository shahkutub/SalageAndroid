package com.salage.Utils;


import android.util.Log;

import com.salage.model.CateGoryInfo;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.DocumentTableInfo;
import com.salage.model.DownLoadFile;
import com.salage.model.PriceListTableInfo;
import com.salage.model.ProductTableInfo;
import com.salage.model.UpojelaOfficerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shohel on 2/15/2016.
 */
public class AppConstant {



    public static String agentCode ="agentCode";
    public static String AGEN_ENABLED ="AGEN_ENABLED";
    public static String AGEN_CANCHANGEPRICE ="AGEN_CANCHANGEPRICE";
    public static String AGEN_CANCHANGEPAYM ="AGEN_CANCHANGEPAYM";
    public static String AGEN_CANCHANGEVAT ="AGEN_CANCHANGEVAT";
    public static String AGEN_CANADDCUST ="AGEN_CANADDCUST";
    public static String AGEN_CANADDDEST ="AGEN_CANADDDEST";
    public static String AGEN_CANCHANGEDESC ="AGEN_CANCHANGEDESC";
    public static String AGEN_CANUSENOCODE ="AGEN_CANUSENOCODE";
    public static String AGEN_CANADDCOMMENTS ="AGEN_CANADDCOMMENTS";
    public static String AGEN_TYPE ="AGEN_TYPE";

    public static int processingFee =0;
    public static int logInt =0;
    public static String isSync ="false";
    public static String isDoc ="false";
    public static String isCustEdit ="";
    public static String customerColumId ="";
    public static List<PriceListTableInfo> priceListTableInfo= new ArrayList<PriceListTableInfo>();

    public static int CAMERA_RUNTIME_PERMISSION=2,WRITEEXTERNAL_PERMISSION_RUNTIME=3,LOCATION_PERMISSION=4;
    public static boolean isGallery=false;
    public static DocumentTableInfo documentTableInfo = new DocumentTableInfo();
    public static ProductTableInfo productTableInfo = new ProductTableInfo();
    public static CustomerTableInfo customerTableInfo = new CustomerTableInfo();
    public static List<DownLoadFile> DownLoadFileList= new ArrayList<DownLoadFile>();

    public static List getCat(DatabaseHelper db){
        List<CateGoryInfo> cateGoryInfoList= new ArrayList<CateGoryInfo>();
        cateGoryInfoList = db.getAllCategories();
        Log.e("cat size: ", ""+cateGoryInfoList.size());
        for (CateGoryInfo cd : cateGoryInfoList) {
            String log = "cat_des: "+cd.getCATE_DESCRIPTION()+" ,catId: " + cd.getCATE_ID();
            // Writing Contacts to log
            Log.e("cat DATA: ", log);

        }
        return cateGoryInfoList;
    }

}
