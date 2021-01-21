package bo.custom.impl;

import bo.custom.PriceCategoryBO;
import dao.DAOFactory;
import dao.custom.PriceCategoryDAO;
import dto.PriceCategoryDTO;
import entity.PriceCategory;

import java.util.ArrayList;

public class PriceCategoryBOImpl implements PriceCategoryBO {
    PriceCategoryDAO priceCategoryDAO = (PriceCategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRICECATEGORY);

    @Override
    public boolean addPriceCategory(PriceCategoryDTO p) throws Exception{
        PriceCategory priceCategory = new PriceCategory(p.getPriceCategoryName(), p.getDefaultRate(), p.getKm(), p.getAddingRate());
        return priceCategoryDAO.add(priceCategory);
    }

    @Override
    public ArrayList<PriceCategoryDTO> getAllPriceCate() throws Exception {
        ArrayList<PriceCategory> allPriceCate = priceCategoryDAO.getAll();
        ArrayList<PriceCategoryDTO> allPriceCateDTO = new ArrayList<>();
        for (PriceCategory p : allPriceCate) {
            allPriceCateDTO.add(new PriceCategoryDTO(p.getPriceCategoryId(),p.getPriceCategoryName(),p.getDefaultRate(),p.getKm(),p.getAddingRate()));
        }
        return allPriceCateDTO;
    }
}
