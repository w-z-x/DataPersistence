package services;

import daos.UserDao;
import entities.UserEntity;
import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserDao userDao;
    private UserService userService;

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldCreateUser() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("张三");
        user.setAddress("广州");
        // 设置模拟对象的返回预期值
        when(userDao.create(user)).thenReturn(1);
        // 执行测试
        int result = userService.createUser(user);
        // 验证创建是否成功
        assertEquals(1, result);
        // 验证模拟对象的方法是否被调用了一次
        verify(userDao).create(user);
        // 得到一个抓取器
        ArgumentCaptor<UserEntity> personCaptor = ArgumentCaptor.forClass(UserEntity.class);
        // 验证模拟对象的update()是否被调用一次，并抓取调用时传入的参数值
        verify(userDao).create(personCaptor.capture());
        // 获取抓取到的参数值
        UserEntity createUser = personCaptor.getValue();
        // 验证调用时的参数值
        assertEquals("张三", createUser.getName());
        // 检查模拟对象上是否还有未验证的交互
        verifyNoMoreInteractions(userDao);
    }
}
