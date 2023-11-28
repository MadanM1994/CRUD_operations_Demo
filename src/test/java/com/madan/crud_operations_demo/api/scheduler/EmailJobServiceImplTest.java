package com.madan.crud_operations_demo.api.scheduler;

import com.madan.crud_operations_demo.constants.EmailStatus;
import com.madan.crud_operations_demo.implementation.EmailJobServiceImpl;
import com.madan.crud_operations_demo.repository.EmailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class EmailJobServiceImplTest {

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailJobServiceImpl emailJobService;

    @Test
    public void EmailJobServiceImpl_AddEmployeeToEmail_ReturnEmployee() {
        String email = "maddala.madan@gmail.com";

        emailJobService.addEmployeeToEmail(email);

        verify(emailRepository).save(argThat(newEmail -> newEmail
                .getEmailAddress()
                .equals(email)
                && newEmail
                .getEmailStatus() == EmailStatus.CREATED));

    }

    @Test
    void addEmployeeToEmail_NullEmail_ThrowsException() {
        String email = null;

        assertThrows(IllegalArgumentException.class, () -> {
            emailJobService.addEmployeeToEmail(email);
        });

        verifyNoInteractions(emailRepository);
    }

    @Test
    void addEmployeeToEmail_EmptyEmail_ThrowsException() {
        String email = "";

        assertThrows(IllegalArgumentException.class, () -> {
            emailJobService.addEmployeeToEmail(email);
        });

        verifyNoInteractions(emailRepository);
    }
}
