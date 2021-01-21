package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO login(UserDTO userDTO) throws Exception {
        User user = new User(userDTO.getUsername(),userDTO.getPassword());
        user = userDAO.login(user);
        if(user==null){
            return null;
        }
        UserDTO userDTO1 = new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), user.getUserType());
        return userDTO1;
    }
}
