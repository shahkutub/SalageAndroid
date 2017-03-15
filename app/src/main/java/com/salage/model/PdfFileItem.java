package com.salage.model;

import java.nio.ByteBuffer;

/**
 * Created by User on 3/10/2017.
 */

public class PdfFileItem {

    byte [] pdf;

    public PdfFileItem(byte[] pdf) {
        this.pdf = pdf;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }
}
