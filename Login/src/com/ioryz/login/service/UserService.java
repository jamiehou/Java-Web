package com.ioryz.login.service;

import com.ioryz.login.dao.UserDao;
import com.ioryz.login.exception.ParamException;
import com.ioryz.login.exception.ServiceException;
import com.ioryz.login.model.User;
import com.ioryz.login.util.StringUtil;

public class UserService {

    public User login(String username, String password) throws ParamException, ServiceException {

        ParamException paramException = new ParamException();

        if (StringUtil.isEmpty(username)) {
            paramException.addErrorMessage("username", "User name is required!");
        }

        if (StringUtil.isEmpty(password)) {
            paramException.addErrorMessage("password", "Password is required!");
        }

        if (paramException.hasError()) {
            throw paramException;
        }

        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(username);

        if (user == null) {
            throw new ServiceException(1000, "Username or password is incorrect!");
        }

        if (!password.equals(user.getPassword())) {
            throw new ServiceException(1001, "Username or password is incorrect!");
        }

        return user;
    }
}
