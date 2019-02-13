package ichop.services.threads;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.exceptions.user.UserException;
import ichop.repository.thread.ThreadRepository;
import ichop.service.thread.ThreadServicesImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ThreadServicesTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ThreadRepository threadRepository;

    @InjectMocks
    private ThreadServicesImp threadServicesImp;

    @Before
    public void setUp() {

    }

    @Test(expected = UserException.class)
    public void create_withNullUser_shouldThrowException() {
        this.threadServicesImp.create(new ThreadCreateBindingModel(), null);
    }

    @Test
    public void create_withValidParameters_shouldReturnValidThread() {

        ThreadCreateBindingModel threadCreateBindingModel = new ThreadCreateBindingModel();
        threadCreateBindingModel.setTitle("Cats have gone crazy");
        threadCreateBindingModel.setContent("Yep they have gone");
        threadCreateBindingModel.setRowsForNewsPage(0);

        User creator = new User();

        Thread mappedThread = new ModelMapper().map(threadCreateBindingModel, Thread.class);

        Mockito.when(this.modelMapper.map(threadCreateBindingModel, Thread.class)).thenReturn(mappedThread);
        Mockito.when(this.threadRepository.save(mappedThread)).thenReturn(mappedThread);
        Thread thread = this.threadServicesImp.create(threadCreateBindingModel, creator);

        Assert.assertEquals("Title wasn't set properly", threadCreateBindingModel.getTitle(), thread.getTitle());
        Assert.assertEquals("Content wasn't set properly", threadCreateBindingModel.getContent(), thread.getContent());
        Assert.assertEquals("Creator wasn't the same as the passed in the method", mappedThread.getCreator(), creator);
    }

    @Test
    public void create_withValidParameters_shouldInvokeTheRepositorySaveMethodOnlyOneTime() {
        ThreadCreateBindingModel threadCreateBindingModel = new ThreadCreateBindingModel();
        User creator = new User();

        Thread mappedThread = new ModelMapper().map(threadCreateBindingModel, Thread.class);

        Mockito.when(this.modelMapper.map(threadCreateBindingModel, Thread.class)).thenReturn(mappedThread);
        Mockito.when(this.threadRepository.save(mappedThread)).thenReturn(mappedThread);

        this.threadServicesImp.create(threadCreateBindingModel, creator);

        Mockito.verify(this.threadRepository, Mockito.times(1)).save(mappedThread);
    }

    @Test
    public void exists_withNotExistingId_shouldReturnFalse(){
        final String NOT_EXISTING_ID =  "e9bb85c2-5d5a-470c-8062-82d50d90e61c";

        Mockito.when(this.threadRepository.findThreadById(NOT_EXISTING_ID)).thenReturn(null);

        boolean result = this.threadServicesImp.exists(NOT_EXISTING_ID);

        Assert.assertFalse(result);
    }

    @Test
    public void exists_withExistingId_shouldReturnTrue(){
        final String NOT_EXISTING_ID =  "ef785c2-5d5a-470c-8062-82d50d30e61c";

        Mockito.when(this.threadRepository.findThreadById(NOT_EXISTING_ID)).thenReturn(new Thread());

        boolean result = this.threadServicesImp.exists(NOT_EXISTING_ID);

        Assert.assertTrue(result);
    }

}
