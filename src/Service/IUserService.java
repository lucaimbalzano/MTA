package Service;

import model.User;

public interface IUserService {

    public Boolean addUser(User user);

    public void importUserFromExcel(String path);

}
