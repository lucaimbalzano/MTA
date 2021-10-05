package Service;

import model.User;

public interface IUserService {

    public Boolean addUser(User user);

    public void importUserFromExcel(String path);

    public Boolean signupUser(User user,String password, String email);

    public Integer getLastIdUser();

    public String getPasswordFromEmail(String email);

}
