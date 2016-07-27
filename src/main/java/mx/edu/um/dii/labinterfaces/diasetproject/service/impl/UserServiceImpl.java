/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.UserDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User get(Long id) {
        User user = userDao.get(id);
        //init confirmPassword
        user.setConfirmPassword(user.getPassword());
        //
        return user;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userDao.save(user);
        return user;
    }

    @Override
    public User update(User user) {
        //check if password is not encoded
        User tmp = get(user.getId());
        //first check if the are not equals
        if (!tmp.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user = userDao.update(user);
        return user;
    }

}
