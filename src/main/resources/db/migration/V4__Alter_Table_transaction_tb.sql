ALTER TABLE transaction_tb
ADD CONSTRAINT fk_payer_id_user_id FOREIGN KEY (payer_id) REFERENCES user_tb(id);

ALTER TABLE transaction_tb
ADD CONSTRAINT fk_payee_id_user_id FOREIGN KEY (payee_id) REFERENCES user_tb(id);