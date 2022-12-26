/*
 * Copyright 2019 ICON Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dfg.icon.score;

import com.dfg.icon.common.ResultTimeoutException;
import com.dfg.icon.common.TransactionFailureException;
import foundation.icon.icx.*;
import foundation.icon.icx.data.*;
import foundation.icon.icx.transport.jsonrpc.RpcError;
import foundation.icon.icx.transport.jsonrpc.RpcItem;
import foundation.icon.icx.transport.jsonrpc.RpcObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class TransactionHandler {
    private static final Logger logger = LoggerFactory.getLogger(TransactionHandler.class);
    private static final BigInteger STEP_MARGIN = BigInteger.valueOf(100000);

    private final IconService iconService;

    public TransactionHandler(IconService iconService) {
        this.iconService = iconService;

    }

    public Score deploy(BigInteger networkId, byte[] jar,
                        Wallet owner, RpcObject params)
            throws IOException, ResultTimeoutException, TransactionFailureException {
        return getScore(doDeploy(networkId, owner, jar, params, Constants.CONTENT_TYPE_JAVA));
    }

    public Score getScore(Bytes txHash)
            throws IOException, ResultTimeoutException, TransactionFailureException {
        TransactionResult result = getResult(txHash);
        if (!Constants.STATUS_SUCCESS.equals(result.getStatus())) {
            throw new TransactionFailureException(result.getFailure());
        }
        return new Score(this, new Address(result.getScoreAddress()));
    }


    private Bytes doDeploy(BigInteger networkId, Wallet owner, byte[] content, RpcObject params, String contentType)
            throws IOException {
        return doDeploy(networkId, owner, content, Constants.CHAINSCORE_ADDRESS, params, null, contentType);
    }

    public Bytes doDeploy(BigInteger networkId, Wallet owner, byte[] content, Address to, RpcObject params, BigInteger steps, String contentType)
            throws IOException {
        Transaction transaction = TransactionBuilder.newBuilder()
                .nid(networkId)
                .from(owner.getAddress())
                .to(to)
                .deploy(contentType, content)
                .params(params)
                .build();
        if (steps == null) {
            steps = estimateStep(transaction).add(STEP_MARGIN);
        }
        SignedTransaction signedTransaction = new SignedTransaction(transaction, owner, steps);
        return iconService.sendTransaction(signedTransaction).execute();
    }

    public IconService getIconService() {
        return this.iconService;
    }


    public BigInteger getBalance(Address address) throws IOException {
        return iconService.getBalance(address).execute();
    }

    public List<ScoreApi> getScoreApi(Address scoreAddress) throws IOException {
        return iconService.getScoreApi(scoreAddress).execute();
    }

    public BigInteger estimateStep(Transaction transaction) throws IOException {
        try {
            return iconService.estimateStep(transaction).execute();
        } catch (RpcError e) {
            logger.info("estimateStep failed(" + e.getCode() + ", " + e.getMessage() + "); use default steps.");
            return Constants.DEFAULT_STEPS;
        }
    }

    public RpcItem call(Call<RpcItem> call) throws IOException {
        return this.iconService.call(call).execute();
    }

    public Bytes invoke(Wallet wallet, Transaction tx) throws IOException {
        return this.iconService.sendTransaction(new SignedTransaction(tx, wallet)).execute();
    }

    public Bytes invoke(Wallet wallet, Transaction tx, BigInteger steps) throws IOException {
        if (steps == null) {
            steps = estimateStep(tx).add(STEP_MARGIN);
        }
        return this.iconService.sendTransaction(new SignedTransaction(tx, wallet, steps)).execute();
    }

    public TransactionResult invokeAndWait(Wallet wallet, Transaction tx, BigInteger steps) throws IOException {
        if (steps == null) {
            steps = estimateStep(tx).add(STEP_MARGIN);
        }
        return this.iconService.sendTransactionAndWait(new SignedTransaction(tx, wallet, steps)).execute();
    }

    public TransactionResult waitResult(Bytes txHash) throws IOException {
        return this.iconService.waitTransactionResult(txHash).execute();
    }

    public TransactionResult getResult(Bytes txHash)
            throws IOException, ResultTimeoutException {
        return getResult(txHash, Constants.DEFAULT_WAITING_TIME);
    }

    public TransactionResult getResult(Bytes txHash, long waiting)
            throws IOException, ResultTimeoutException {
        long limitTime = System.currentTimeMillis() + waiting;
        while (true) {
            try {
                return iconService.getTransactionResult(txHash).execute();
            } catch (RpcError e) {
                if (e.getCode() == -31002 /* pending */
                        || e.getCode() == -31003 /* executing */
                        || e.getCode() == -31004 /* not found */) {
                    if (limitTime < System.currentTimeMillis()) {
                        throw new ResultTimeoutException(txHash);
                    }
                    try {
                        // wait until block confirmation
                        logger.debug("RpcError: code(" + e.getCode() + ") message(" + e.getMessage() + "); Retry in 1 sec.");
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    continue;
                }
                logger.info("RpcError: code(" + e.getCode() + ") message(" + e.getMessage() + ")");
                throw e;
            }
        }
    }


    public Bytes transfer(BigInteger networkId, Wallet owner, Address to, BigInteger amount) throws IOException {
        return transfer(networkId, owner, to, amount, null);
    }

    public Bytes transfer(BigInteger networkId, Wallet owner, Address to, BigInteger amount, BigInteger steps) throws IOException {
        Transaction transaction = TransactionBuilder.newBuilder()
                .nid(networkId)
                .from(owner.getAddress())
                .to(to)
                .value(amount)
                .build();
        if (steps == null) {
            steps = estimateStep(transaction).add(STEP_MARGIN);
        }
        SignedTransaction signedTransaction = new SignedTransaction(transaction, owner, steps);
        return iconService.sendTransaction(signedTransaction).execute();
    }


    public ConfirmedTransaction getTransaction(Bytes txHash) throws IOException {
        return iconService.getTransaction(txHash).execute();
    }
}
