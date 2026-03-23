package bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

import bankapp.banking.app.AccountRepository;
import bankapp.banking.app.FileAccountRepository;
import bankapp.banking.model.Account;
import bankapp.banking.service.BankService;

class BankServiceTest {

	@Mock
	private AccountRepository repository;
	@Mock
	private ObjectMapper mapper;

	@InjectMocks
	private BankService bankService;
	@InjectMocks
	private FileAccountRepository fileRepository;

	private Account account;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		fileRepository = new FileAccountRepository(mapper);
		account = new Account(1, "Amit", 1000);
	}

	@Test
	void testFetchingAccountNumber() {
		when(repository.findById(1)).thenReturn(account);
		account.getAccountNumber();
		assertEquals(1, account.getAccountNumber());
	}

	@Test
	void testDeposit() {
		when(repository.findById(1)).thenReturn(account); // mocking
		bankService.deposit(1, 500);
		assertEquals(1500, account.getBalance());
		verify(repository).save(account);
	}

	@Test
	void testDepositWhenAmountIsNegative() {
		when(repository.findById(1)).thenReturn(account);
		assertThrows(IllegalArgumentException.class, () -> bankService.deposit(1, -500));
		verify(repository, never()).save(any());
	}

	@Test
	void testSuccessfulWithdrawal() {
		when(repository.findById(1)).thenReturn(account);
		bankService.withdraw(1, 300);
		assertEquals(700, account.getBalance());
		verify(repository).save(account);
	}

	@Test
	void testWithdrawInsufficientBalance() {
		when(repository.findById(1)).thenReturn(account);
		assertThrows(IllegalArgumentException.class, () -> bankService.withdraw(1, 2000));
		verify(repository, never()).save(any());
	}

	@Test
	void testSuccesfulMapperRead_FindByIdWhenFileExists() throws IOException {
		Account mockAccount = new Account(2, "Kaushik", 2000);
		when(mapper.readValue(any(File.class), eq(Account.class))).thenReturn(mockAccount);
		Account result = fileRepository.findById(2);
		assertEquals(2, result.getAccountNumber());
		verify(mapper).readValue(any(File.class), eq(Account.class));
	}

	@Test
	void testFindByID_ThrowsRunTimeException() throws IOException {
		when(mapper.readValue(any(File.class), eq(Account.class))).thenThrow(new IOException("File read Error..."));
		assertThrows(RuntimeException.class, () -> fileRepository.findById(2));
	}

	@Test
	void testSuccessfulSave() throws IOException {
		Account saveAccountMock = new Account(3, "RichieRich", 3000);
		fileRepository.save(saveAccountMock);
		verify(mapper).writeValue(any(File.class), eq(saveAccountMock));
	}
}
