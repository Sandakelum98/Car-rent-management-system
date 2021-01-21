package bo.custom.impl;

import bo.custom.DashBoardBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dto.DashBoardDTO;

import java.util.ArrayList;

public class DashBoardBOImpl implements DashBoardBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);

    @Override
    public ArrayList<DashBoardDTO> loadBarChart(DashBoardDTO dashBoardDTO)  throws Exception {
        return queryDAO.loadBarChart(dashBoardDTO);
    }
}
