package com.salage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/13/2017.
 */

public class SyncData {

    public List<PriceListTableInfo> pric_pricelists =new ArrayList<PriceListTableInfo>();
    public List<DocumentTableInfo> doch_document_heads =new ArrayList<DocumentTableInfo>();
    public List<ProductTableInfo> prod_products =new ArrayList<ProductTableInfo>();
    public List<CateGoryInfo> cate_categories =new ArrayList<CateGoryInfo>();
    public List<SubCatTableInfo> subc_subcategories =new ArrayList<SubCatTableInfo>();
    public List<BrandsTableInfo> bran_brands =new ArrayList<BrandsTableInfo>();

    public List<BrandsTableInfo> getBran_brands() {
        return bran_brands;
    }

    public void setBran_brands(List<BrandsTableInfo> bran_brands) {
        this.bran_brands = bran_brands;
    }

    public List<SubCatTableInfo> getSubc_subcategories() {
        return subc_subcategories;
    }

    public void setSubc_subcategories(List<SubCatTableInfo> subc_subcategories) {
        this.subc_subcategories = subc_subcategories;
    }

    public List<CateGoryInfo> getCate_categories() {
        return cate_categories;
    }

    public void setCate_categories(List<CateGoryInfo> cate_categories) {
        this.cate_categories = cate_categories;
    }

    public List<ProductTableInfo> getProd_products() {
        return prod_products;
    }

    public void setProd_products(List<ProductTableInfo> prod_products) {
        this.prod_products = prod_products;
    }

    public List<PriceListTableInfo> getPric_pricelists() {
        return pric_pricelists;
    }

    public void setPric_pricelists(List<PriceListTableInfo> pric_pricelists) {
        this.pric_pricelists = pric_pricelists;
    }

    public List<DocumentTableInfo> getDoch_document_heads() {
        return doch_document_heads;
    }

    public void setDoch_document_heads(List<DocumentTableInfo> doch_document_heads) {
        this.doch_document_heads = doch_document_heads;
    }
}
