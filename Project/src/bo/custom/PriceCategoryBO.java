package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.PriceCategoryDTO;

import java.util.ArrayList;

public interface PriceCategoryBO extends SuperBO {
    public boolean addPriceCategory(PriceCategoryDTO p) throws Exception;
    public ArrayList<PriceCategoryDTO> getAllPriceCate() throws Exception;

}
