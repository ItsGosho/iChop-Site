package ichop.services.thread;

import ichop.domain.entities.threads.Thread;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.repository.threads.thread.ThreadRepository;
import ichop.service.threads.thread.crud.ThreadCrudServicesImp;
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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ThreadCrudServicesTests {

    @Mock
    private ThreadRepository threadRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ThreadCrudServicesImp threadCrudServicesImp;


    @Before
    public void init() {

    }


    //getThread

    @Test
    public void getThread_withNotExistingId_shouldReturnNull() {
        String id = "notExistingId";
        when(this.threadRepository.findThreadById(id)).thenReturn(null);
        ThreadServiceModel result = this.threadCrudServicesImp.getThread(id);

        assertNull(result);
    }

    @Test
    public void getThread_withExistingId_shouldReturnThread() {
        String id = "existingId";
        Thread thread = new Thread();
        thread.setId(id);

        when(this.threadRepository.findThreadById(id)).thenReturn(thread);
        ThreadServiceModel result = this.threadCrudServicesImp.getThread(id);

        assertEquals(id, result.getId());
    }

    //delete

    @Test
    public void delete_deleteMethodShouldBeInvoked() {
        String id = "id";

        Thread thread = new Thread();
        thread.setId(id);

        when(this.threadRepository.findThreadById(id)).thenReturn(thread);
        this.threadCrudServicesImp.delete(id);

        verify(this.threadRepository, times(1)).delete(thread);
    }

    //exists

    @Test
    public void exists_withNotExistingId_shouldReturnFalse() {
        boolean result = this.threadCrudServicesImp.exists("");
        assertFalse(result);
    }

    @Test
    public void exists_withExistingId_shouldReturnTrue() {
        String id = "id";

        when(this.threadRepository.findThreadById(id)).thenReturn(new Thread());
        boolean result = this.threadCrudServicesImp.exists(id);

        assertTrue(result);
    }

    //save

    @Test
    public void save_shouldInvokeSaveMethodOnlyOneTime() {

        this.threadCrudServicesImp.save(new ThreadServiceModel());

        verify(this.threadRepository, times(1)).save(any());
    }

    //findAll

    @Test
    public void findAll_withThreePresentedThreads_shouldReturnListWithThreeThreads(){
        List<Thread> threads = new LinkedList<>(Arrays.asList(new Thread(),new Thread(),new Thread()));

        when(this.threadRepository.findAll()).thenReturn(threads);

        List<ThreadServiceModel> result = this.threadCrudServicesImp.findAll();

        assertEquals(3,result.size());
    }

    @Test
    public void findAll_withNoPresentedThreads_shouldReturnListWithNoThreads(){
        when(this.threadRepository.findAll()).thenReturn(new LinkedList<>());

        List<ThreadServiceModel> result = this.threadCrudServicesImp.findAll();

        assertEquals(0,result.size());
    }

}
