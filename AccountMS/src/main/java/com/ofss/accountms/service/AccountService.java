package com.ofss.accountms.service;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ofss.accountms.model.Account;
import com.ofss.accountms.model.AccountDTO;
import com.ofss.accountms.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request; // To forward auth headers

    // Get all accounts
    public ResponseEntity<Object> getAllAccounts() {
        Iterable<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    // Get account by ID
    public ResponseEntity<Object> getAccountById(int accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.<ResponseEntity<Object>>map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                     .body("No such account id is present"));
    }

    // Get account details with Customer & Bank info
    public ResponseEntity<Object> getAccountDetailsById(int accountId) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            Object customer = getCustomerById(account.getCustomerId());
            Object bank = getBankById(account.getBankId());

            AccountDTO dto = new AccountDTO(account, customer, bank);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("No such account id is present");
    }

    // Add a new account
    public ResponseEntity<Object> addAccount(Account account) {
        if (account.getCustomerId() == 0 || account.getBankId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Customer or Bank ID missing");
        }

        if (account.getAccountCreationDate() == null) {
            account.setAccountCreationDate(LocalDate.now());
        }

        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Account created successfully");
    }

    // Delete account
    public ResponseEntity<Object> deleteAccountById(int accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return ResponseEntity.ok("Account deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("No such account id is present");
    }

    // Full update of an account
    public ResponseEntity<Object> updateAccountById(int accountId, Account updatedAccount) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            Account existing = accountOpt.get();
            existing.setAccountType(updatedAccount.getAccountType());
            existing.setBalance(updatedAccount.getBalance());
            existing.setCustomerId(updatedAccount.getCustomerId());
            existing.setBankId(updatedAccount.getBankId());
            accountRepository.save(existing);
            return ResponseEntity.ok("Account updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("No such account id is present");
    }

    // Partial update of an account
    public ResponseEntity<Object> updateAccountPartially(int accountId, Account partialAccount) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            Account existing = accountOpt.get();
            if (partialAccount.getAccountType() != null)
                existing.setAccountType(partialAccount.getAccountType());
            if (partialAccount.getBalance() != 0)
                existing.setBalance(partialAccount.getBalance());
            if (partialAccount.getCustomerId() != 0)
                existing.setCustomerId(partialAccount.getCustomerId());
            if (partialAccount.getBankId() != 0)
                existing.setBankId(partialAccount.getBankId());
            accountRepository.save(existing);
            return ResponseEntity.ok("Account partially updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("No such account id is present");
    }

    // =======================
    // External Microservice Calls
    // =======================

    private Object getCustomerById(int customerId) {
        return callMicroservice("http://customer-ms/customers/id/{id}", customerId);
    }

    private Object getBankById(int bankId) {
        return callMicroservice("http://bank-ms/banks/{id}", bankId);
    }

    private Object callMicroservice(String url, int id) {
        String authHeader = request.getHeader("Authorization"); // Forward caller's auth
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            Object.class,
            id
        );

        return response.getBody();
    }

    // =======================
    // Transactions
    // =======================

    public ResponseEntity<?> deposit(int accountId, double amount) {
        Optional<Account> optional = accountRepository.findById(accountId);
        if(optional.isPresent()) {
            Account account = optional.get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            return ResponseEntity.ok(account.getCustomerId());
        }
        return ResponseEntity.badRequest().body("Account not found");
    }

    public ResponseEntity<?> withdraw(int accountId, double amount) {
        Optional<Account> optional = accountRepository.findById(accountId);
        if(optional.isPresent()) {
            Account account = optional.get();
            if(account.getBalance() < amount) {
                return ResponseEntity.badRequest().body("Insufficient balance");
            }
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            return ResponseEntity.ok(account.getCustomerId());
        }
        return ResponseEntity.badRequest().body("Account not found");
    }

    public ResponseEntity<?> transfer(int sourceId, int destId, double amount) {
        Optional<Account> sourceOpt = accountRepository.findById(sourceId);
        Optional<Account> destOpt = accountRepository.findById(destId);

        if(sourceOpt.isEmpty() || destOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Source or Destination account not found");
        }

        Account source = sourceOpt.get();
        Account dest = destOpt.get();

        if(source.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }

        source.setBalance(source.getBalance() - amount);
        dest.setBalance(dest.getBalance() + amount);

        accountRepository.save(source);
        accountRepository.save(dest);

        return ResponseEntity.ok(source.getCustomerId());
    }
}
