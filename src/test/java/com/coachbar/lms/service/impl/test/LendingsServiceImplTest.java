package com.coachbar.lms.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.coachbar.lms.enumeration.LendingStatus;
import com.coachbar.lms.model.Books;
import com.coachbar.lms.model.Lendings;
import com.coachbar.lms.repository.LendingsRepository;
import com.coachbar.lms.service.BooksService;
import com.coachbar.lms.service.impl.LendingsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LendingsServiceImplTest {

    @Mock
    private LendingsRepository lendingsRepository;

    @Mock
    private BooksService booksService;

    @InjectMocks
    private LendingsServiceImpl lendingsService;

    @Test
    public void testSaveOrUpdateIssueEntry_shouldDecreaseBookQuantity() {
        Books book = new Books();
        book.setCode("B001");
        book.setQuantityAvailable(5);

        Lendings lending = new Lendings();
        lending.setStatus(LendingStatus.ISSUED);
        lending.setBook(book);

        when(booksService.getBookByBookCode("B001")).thenReturn(Optional.of(book));
        when(lendingsRepository.save(lending)).thenReturn(lending);

        Lendings result = lendingsService.saveOrUpdateIssueEntry(lending);

        assertEquals(Integer.valueOf(4), book.getQuantityAvailable());
        verify(booksService).saveBook(book);
        verify(lendingsRepository).save(lending);
    }

    @Test
    public void testSaveOrUpdateIssueEntry_shouldIncreaseBookQuantity() {
        Books book = new Books();
        book.setCode("B002");
        book.setQuantityAvailable(2);

        Lendings lending = new Lendings();
        lending.setStatus(LendingStatus.RETURNED);
        lending.setBook(book);

        when(booksService.getBookByBookCode("B002")).thenReturn(Optional.of(book));
        when(lendingsRepository.save(lending)).thenReturn(lending);

        Lendings result = lendingsService.saveOrUpdateIssueEntry(lending);

        assertEquals(Integer.valueOf(3), book.getQuantityAvailable());
        verify(booksService).saveBook(book);
        verify(lendingsRepository).save(lending);
    }
}
