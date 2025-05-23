/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class PanelPrinter implements Printable {
    private JPanel panelToPrint;

    public PanelPrinter(JPanel panelToPrint) {
        this.panelToPrint = panelToPrint;
    }

   @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;

        // Set up paper size (Letter)
        Paper paper = new Paper();
        double width = 8.5 * 72; // 612 points
        double height = 11 * 72; // 792 points
        paper.setSize(width, height);

        // Set 0.5 inch margins (36 points)
        paper.setImageableArea(36, 36, width - 72, height - 72); 
        pageFormat.setPaper(paper);

        // Get the printable area
        double imageableX = pageFormat.getImageableX();
        double imageableY = pageFormat.getImageableY();
        double imageableWidth = pageFormat.getImageableWidth();
        double imageableHeight = pageFormat.getImageableHeight();

        // Get panel size
        double panelWidth = panelToPrint.getWidth();
        double panelHeight = panelToPrint.getHeight();

        // Calculate scale to fit panel within printable area
        double scaleX = imageableWidth / panelWidth;
        double scaleY = imageableHeight / panelHeight;
        double scale = Math.min(scaleX, scaleY); // Keep aspect ratio

        // Translate and scale graphics context
        g2d.translate(imageableX, imageableY);
        g2d.scale(scale, scale);

        // Print panel
        panelToPrint.printAll(g2d);

        return PAGE_EXISTS;
    }



    public void printPanel() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
