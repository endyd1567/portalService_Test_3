package portalService.test2.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import portalService.test2.connection.HallaConnectionMaker;
import portalService.test2.connection.JejuConnectionMaker;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private static UserDao userDao;
    @BeforeAll
    public static void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException {
        Long id = 1l;
        String name = "umdu";
        String password = "1234";
        User user = userDao.findById(id);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void insert() throws SQLException {
        User user = insertedUser();

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId()).isGreaterThan(1l);

        assertThat(insertedUser.getId()).isEqualTo(user.getId());
        assertThat(insertedUser.getName()).isEqualTo(user.getName());
        assertThat(insertedUser.getPassword()).isEqualTo(user.getPassword());
    }

    private User insertedUser() throws SQLException {
        User user = new User();
        String name = "엄두용";
        String password = "1234";
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }

    @Test
    public void update() throws SQLException {
        User user = insertedUser();
        String updatedName = "updated_엄두용";
        String updatedPassword = "updated_1234";
        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);
        User updatedUser = userDao.findById(user.getId());

        assertThat(updatedUser.getId()).isEqualTo(user.getId());
        assertThat(updatedUser.getName()).isEqualTo(updatedName);
        assertThat(updatedUser.getPassword()).isEqualTo(updatedPassword);
    }

    @Test
    public void delete() throws SQLException {
        User user = insertedUser();

        userDao.delete(user.getId());
        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser).isNull();
    }

}
