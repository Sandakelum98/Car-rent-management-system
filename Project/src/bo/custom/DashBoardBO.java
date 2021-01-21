package bo.custom;

import bo.SuperBO;
import dto.DashBoardDTO;

import java.util.ArrayList;

public interface DashBoardBO extends SuperBO {
    ArrayList<DashBoardDTO> loadBarChart(DashBoardDTO dashBoardDTO) throws Exception;
}
