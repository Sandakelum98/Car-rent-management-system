package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

public interface UserBO extends SuperBO {
    public UserDTO login(UserDTO userDTO) throws Exception;
}
