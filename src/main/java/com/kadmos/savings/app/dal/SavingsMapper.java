package com.kadmos.savings.app.dal;

import com.kadmos.savings.app.model.AccountCreateRequest;
import com.kadmos.savings.app.model.BalanceResponse;
import com.kadmos.savings.app.model.TransactionRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface SavingsMapper {

    @Select("select * from savings_account_tbl where account_no = #{accountNo} order by updated_at desc limit 1")
    BalanceResponse getCurrentBalance(Long accountNo);

    @Select("select count(*) from customer_tbl where account_no = #{accountNo}")
    int checkAccountExists(Long accountNo);

    @Insert("insert into customer_tbl (account_type, first_name, last_name, address) values " +
            "(#{accountType}, #{firstName}, #{lastName}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "account_no")
    void createAccount(AccountCreateRequest request);

    @Insert("insert into savings_account_tbl " +
            "(account_no, account_type, transaction_type, description, deposit, balance) values " +
            "(#{accountNo}, #{accountType}, #{transactionType}, #{description}, #{amount}, #{balance})")
    void deposit(TransactionRequest request);

    @Insert("insert into savings_account_tbl " +
            "(account_no, account_type, transaction_type, description, withdraw, balance) values " +
            "(#{accountNo}, #{accountType}, #{transactionType}, #{description}, #{amount}, #{balance})")
    void withdraw(TransactionRequest request);

}
