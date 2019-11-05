package com.r00t.chart.services;

import com.r00t.chart.models.UserModel;
import com.r00t.chart.principals.UserPrincipal;
import com.r00t.chart.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = findUserWithUsername(s);
        if (userModel != null)
            return new UserPrincipal(repository.findByUserName(s));
        else
            throw new UsernameNotFoundException("User not found");
    }

    public UserModel findUserWithUsername(String userName) {
        return repository.findByUserName(userName);
    }

    public UserModel insertUser(UserModel userModel) {
        return repository.insert(userModel);
    }

    public UserModel updateUser(UserModel userModel) {
        return repository.save(userModel);
    }

    public void deleteUser(String userId) {
        repository.deleteById(userId);
    }

    //------------------------------------------------------------------------------------------------------------------

    public void dropAll() {
        repository.deleteAll();
    }
}
