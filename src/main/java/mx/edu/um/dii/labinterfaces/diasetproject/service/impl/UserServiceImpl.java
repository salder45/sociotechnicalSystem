/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.CredentialDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.UserDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Credential;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
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
    private CredentialDao credentialDao;
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
        String data = "{\"username\":\"" + user.getUsername() + "\",\"password\":\"" + user.getPassword() + "\"}";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userDao.save(user);
        //
        Credential credential = new Credential(user.getId(), data);
        credential.setUser(user);
        credentialDao.save(credential);
        user.setCredential(credential);
        //
        return user;
    }

    @Override
    public User update(User user) {
        //check if password is not encoded
        User tmp = get(user.getId());
        //first check if the are not equals
        if (!tmp.getPassword().equals(user.getPassword())) {
            String data = "{\"username\":\"" + user.getUsername() + "\",\"password\":\"" + user.getPassword() + "\"}";
            Credential credential=tmp.getCredential();
            credential.setCredentialData(data);
            credentialDao.update(credential);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user = userDao.update(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userDao.get();
    }

    @Override
    public String delete(Long id) {
        User user=get(id);
        credentialDao.delete(user.getCredential().getId());
        String result = userDao.delete(id);
        return result;
    }

}
