package directives;

import db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.HashMap;

public class Report {

    public void getReport(int value) throws JRException, SQLException, ClassNotFoundException {
        JasperReport jr = JasperCompileManager.compileReport("src/Jasper/bill.jrxml");

        HashMap hs = new HashMap();
        hs.put("RESERVATION_ID", value);

        JasperPrint js = JasperFillManager.fillReport(jr, hs, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(js,false);
    }
}
