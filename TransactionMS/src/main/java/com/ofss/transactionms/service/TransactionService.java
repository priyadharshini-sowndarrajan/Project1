package com.ofss.transactionms.service;

import com.ofss.transactionms.model.Transaction;
import com.ofss.transactionms.repository.TransactionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request; // to capture incoming Authorization header

    private static final String ACCOUNT_MS_URL = "http://account-ms/accounts";

    // ✅ Helper method to forward Authorization header while making REST calls
    private <T> T postWithAuth(String url, Class<T> responseType) {
        String authHeader = request.getHeader("Authorization");

        HttpHeaders headers = new HttpHeaders();
        if (authHeader != null) {
            headers.set("Authorization", authHeader);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                responseType
        );

        return response.getBody();
    }

    // ✅ Deposit
    @Transactional
    public Transaction deposit(int accountId, double amount) throws Exception {
        String url = ACCOUNT_MS_URL + "/deposit/" + accountId + "?amount=" + amount;

        Integer customerId = postWithAuth(url, Integer.class);

        Transaction transaction = new Transaction(
                customerId,
                null,
                accountId,
                LocalDate.now(),
                "deposit",
                amount
        );
        return transactionRepository.save(transaction);
    }

    // ✅ Withdraw
    @Transactional
    public Transaction withdraw(int accountId, double amount) throws Exception {
        String url = ACCOUNT_MS_URL + "/withdraw/" + accountId + "?amount=" + amount;

        Integer customerId = postWithAuth(url, Integer.class);

        Transaction transaction = new Transaction(
                customerId,
                accountId,
                null,
                LocalDate.now(),
                "withdraw",
                amount
        );
        return transactionRepository.save(transaction);
    }

    // ✅ Transfer
    @Transactional
    public Transaction transfer(int sourceAccountId, int destAccountId, double amount) throws Exception {
        String url = ACCOUNT_MS_URL + "/transfer?sourceId=" + sourceAccountId +
                     "&destId=" + destAccountId +
                     "&amount=" + amount;

        Integer customerId = postWithAuth(url, Integer.class);

        Transaction transaction = new Transaction(
                customerId,
                sourceAccountId,
                destAccountId,
                LocalDate.now(),
                "transfer",
                amount
        );
        return transactionRepository.save(transaction);
    }
}
