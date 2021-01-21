package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PriceCategoryDAO;
import entity.PriceCategory;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PriceCategoryDAOImpl implements PriceCategoryDAO {

    @Override
    public boolean add(PriceCategory p) throws Exception {
        String sql = "INSERT INTO price_category (price_category_name,default_rate,km,adding_rate) VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,p.getPriceCategoryName(),p.getDefaultRate(),p.getKm(),p.getAddingRate());
    }

    @Override
    public PriceCategory search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(PriceCategory priceCategory) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<PriceCategory> getAll() throws Exception {
        String sql = "SELECT * FROM price_category";
        ArrayList<PriceCategory> allPriceCate = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            allPriceCate.add(new PriceCategory(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getDouble(5)
                    ));
        }
        return allPriceCate;
    }
}
