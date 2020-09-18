package bsmidtoseniorjava.test;

import bsmidtoseniorjava.test.model.Task;
import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.repos.UserRepo;
import bsmidtoseniorjava.test.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestApplicationTests {

    private UserService underTest;
    private UserRepo userRepo;

    @Before
    public void setUp() {
        underTest = new UserService(userRepo, new JdbcTemplate());
    }

    @Test
    public void testingCRUD() {
        UUID idOne = UUID.randomUUID();
        UUID idTaskOne = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(1);
        User userOne;
        userOne = new User(idOne, "martonSz", "Marton","Szekely",null);
        Task firstTask = new Task(idTaskOne,userOne,"New Task","Easy task",timestamp);
        userOne.addTask(firstTask);

        UUID idTwo = UUID.randomUUID();
        UUID idTaskTwo = UUID.randomUUID();
        User userTwo;
        userTwo = new User(idOne, "JDOE", "John","Doe",null);
        Task secondTask = new Task(idTaskTwo,userTwo,"New Task","Moderate task",timestamp);
        userTwo.addTask(secondTask);

        underTest.addUser(userOne);
        underTest.addUser(userTwo);

        assertThat(underTest.getUserById(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(userOne));

        assertThat(underTest.getUserById(idTwo))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(userTwo));

        Iterable<User> people = underTest.getAllUsers();

        assertThat(people)
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(userOne, userTwo);

        UUID idUser = UUID.randomUUID();
        UUID idTask = UUID.randomUUID();
        User anotheUser = new User(idUser, "Wrong Name", "Wrong", "Name", null);
        Task anotherTask = new Task(idTask,anotheUser,"New Task","Easy task",timestamp);
        anotheUser.addTask(anotherTask);
        User userUpdate = new User(idOne, "John Doe", "John","Doe", null);
        userUpdate.addTask(anotherTask);


        assertThat(underTest.updateUser(idOne, userUpdate)).isEqualTo(1);

        assertThat(underTest.getUserById(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(userUpdate));

        // delete
        assertThat(underTest.deleteUser(idOne)).isEqualTo(1);

        //userOne should be empty
        assertThat(underTest.getUserById(idOne)).isEmpty();

        // database should contain only 1
        assertThat(underTest.getAllUsers())
                .hasSize(1)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(userTwo);
    }

    @Test
    public void returnNullIfNoUserFoundToDelete() {
        UUID id = UUID.randomUUID();
        int deleteResult = underTest.deleteUser(id);
        assertThat(deleteResult).isEqualTo(0);
    }

    @Test
    public void returnNullIfNoPersonFoundToUpdate() {
        UUID id = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(1);
        User bsuser = new User(id, "Wrong Name", "Wrong", "Name", null);
        Task thirdTask = new Task(id,bsuser,"New Task","Easy task",timestamp);
        bsuser.addTask(thirdTask);

        int deleteResult = underTest.updateUser(id, bsuser);

        assertThat(deleteResult).isEqualTo(0);
    }
}
