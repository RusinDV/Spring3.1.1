package ru.mail.dtraider.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mail.dtraider.crud.model.User;

import java.util.List;


@Repository
public class UserDaoJpaRepositoryImpl implements UserDao {
    @Autowired
    private UserJPARepository userJPARepository;

    @Override
    public void createUser(User user) {
        userJPARepository.save(user);
    }

    @Override
    public User readUser(Long idUser) {
        User user = userJPARepository.findById(idUser).get();
        return user;
    }

    @Override
    public void updateUser(Long idUser, String name, String lastname, int age) {
        User user = userJPARepository.findById(idUser).get();
        user.setName(name);
        user.setLastName(lastname);
        user.setAge(age);
        userJPARepository.save(user);
    }

    @Override
    public void deleteUser(Long idUser) {
        userJPARepository.deleteById(idUser);
    }

    @Override
    public List<User> getUsers() {
        return userJPARepository.findAll();
    }
}

