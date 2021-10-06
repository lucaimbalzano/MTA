package Service;

import model.Login;
import model.User;

public interface IUserService {

    public Boolean addUser(User user);

    public void importUserFromExcel(String path);

    public Boolean signupUser(User user,String password, String email);

    public Integer getLastIdUser();

    public String getPasswordFromEmail(String email);

    public Integer getUserIdByEmail(String email);

    public User getUserById(Integer id);

    public Boolean updateUser(Integer id,User u);

    public Boolean updateLogin(String email,Login l);

}
