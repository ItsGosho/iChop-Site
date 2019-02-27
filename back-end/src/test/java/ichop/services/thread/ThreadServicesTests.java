package ichop.services.thread;

import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.service.thread.ThreadServicesImp;
import ichop.service.thread.crud.ThreadCrudServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ThreadServicesTests {

    @Mock
    private ThreadCrudServices threadCrudServices;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ThreadServicesImp threadServicesImp;

    @Before
    public void init() {

    }

    //create

    @Test
    public void create_withValidParameters_shouldCreateThread() {
        String userUsername = "username";
        String threadTitle = "title";
        String threadContent = "content";
        ThreadCreateBindingModel threadCreateBindingModel = new ThreadCreateBindingModel();
        threadCreateBindingModel.setTitle("title");
        threadCreateBindingModel.setContent("content");
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername(userUsername);

        ThreadServiceModel resultedThread = this.threadServicesImp.create(threadCreateBindingModel, userServiceModel);

        assertEquals(threadTitle,resultedThread.getTitle());
        assertEquals(threadContent,resultedThread.getContent());
        assertEquals(userUsername,resultedThread.getCreator().getUsername());
        assertEquals(0,resultedThread.getComments().size());
        assertEquals(0,resultedThread.getReacts().size());
    }

}
