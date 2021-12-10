package com.vuw17.dao.jpa;

import com.vuw17.entities.Role;
import com.vuw17.entities.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface UserDao {

    //Hàm tìm tất cả user
    List<User> findAllUser();

    // hàm tìm user
    Role findRoleById(int id);
}
