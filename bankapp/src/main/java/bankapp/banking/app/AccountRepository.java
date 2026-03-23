package bankapp.banking.app;

import bankapp.banking.model.Account;

public interface AccountRepository {
	Account findById(int accountNumber);

	void save(Account account);
}
