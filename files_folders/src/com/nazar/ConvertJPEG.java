package com.nazar;

import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: nazar
 * Date: 05.04.14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class ConvertJPEG {


    private Document convert;
    private PdfWriter writer;
    private Path source;
    private Path target;

    public ConvertJPEG(Path target) {
        convert = new Document(PageSize.A4);
        this.target = target;

    }

    public void setSource(Path source) {
        this.source = source;
    }

    public void init() {
        try {
            FileOutputStream output = new FileOutputStream(target.toString());
            writer = PdfWriter.getInstance(convert, output);
            writer.open();
            convert.open();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void convert() {
        try{

            //open image
            Image sourceJpg = Image.getInstance(source.toString());
            if (sourceJpg.getScaledWidth() > 1000 || sourceJpg.getScaledHeight() > 1000) {
                sourceJpg.scaleToFit(800, 842);
            }
            sourceJpg.setRotationDegrees(270);
            sourceJpg.rotate();

            //Add image to Document
            convert.add(sourceJpg);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        convert.close();
    }
}
