package ar.com.terminal;

import ar.com.pos.db.dto.Sale;
import ar.com.pos.ui.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created by yo on 19/10/14.
 */
public class SalesReportWindow {

    private JTextField textField1;
    private JTextField textField2;
    private JPanel JPanelSalesReport;
    private JTable tableSales;

    public JPanel getJPanelSalesReport() {
        return JPanelSalesReport;
    }

    public void setJPanelSalesReport(JPanel JPanelSalesReport) {
        this.JPanelSalesReport = JPanelSalesReport;
    }

    public void setData(List<Sale> data) {

        for(Sale sale : data){
            Vector sales = new Vector();
            sales.add(sale.date());
            sales.add(sale.totalPrice());
        }

        ar.com.pos.ui.View view = new ar.com.pos.ui.View();
        view.addSalesToTheFollowingTable((DefaultTableModel)tableSales.getModel(), data);
        tableSales.setVisible(true);
        tableSales.repaint();
    }

    public boolean isModified(SalesReportListData data) {
        return false;
    }
}