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
    public List<CustomerTableInfo> cust_customers =new ArrayList<CustomerTableInfo>();
    public List<AgentTableInfo> agen_agents =new ArrayList<AgentTableInfo>();
    public List<VatTableInfo> vatt_vat =new ArrayList<VatTableInfo>();
    public List<PaymentTableInfo> paym_payments =new ArrayList<PaymentTableInfo>();
    public List<CustomerProductTableInfo> cupr_customersproducts =new ArrayList<>();

    public List<CustomerProductTableInfo> getCupr_customersproducts() {
        return cupr_customersproducts;
    }

    public void setCupr_customersproducts(List<CustomerProductTableInfo> cupr_customersproducts) {
        this.cupr_customersproducts = cupr_customersproducts;
    }

    public List<VatTableInfo> getVatt_vat() {
        return vatt_vat;
    }

    public void setVatt_vat(List<VatTableInfo> vatt_vat) {
        this.vatt_vat = vatt_vat;
    }

    public List<PaymentTableInfo> getPaym_payments() {
        return paym_payments;
    }

    public void setPaym_payments(List<PaymentTableInfo> paym_payments) {
        this.paym_payments = paym_payments;
    }

    public List<AgentTableInfo> getAgen_agents() {
        return agen_agents;
    }

    public void setAgen_agents(List<AgentTableInfo> agen_agents) {
        this.agen_agents = agen_agents;
    }

    public List<CustomerTableInfo> getCust_customers() {
        return cust_customers;
    }

    public void setCust_customers(List<CustomerTableInfo> cust_customers) {
        this.cust_customers = cust_customers;
    }

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
