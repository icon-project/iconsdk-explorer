package com.dfg.icon.score;

import foundation.icon.icx.IconService;
import foundation.icon.icx.KeyWallet;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.data.Bytes;
import foundation.icon.icx.transport.http.HttpProvider;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Irc3 {
    protected static final BigInteger ICX = BigInteger.TEN.pow(18);
    private static final Address ZERO_ADDRESS = new Address("hx0000000000000000000000000000000000000000");
    private static String jarPath = "src/test/resources/irc3.jar";
    private static String url = "http://localhost:9080/api/v3/src";
    private static String networkIdStr = "0x351760";
    private static BigInteger networkId = new BigInteger(networkIdStr.substring(2), 16);
    private static SecureRandom secureRandom;
    private static KeyWallet ownerWallet;
    private static KeyWallet[] callers;
    private static TransactionHandler txHandler;


    static void init() throws Exception {
        IconService iconService = new IconService(new HttpProvider(url));
        secureRandom = new SecureRandom();
        txHandler = new TransactionHandler(iconService);
    }

    static void initWallet() throws Exception{
        KeyWallet[] wallets;
        // init wallets
        wallets = new KeyWallet[5];
        BigInteger amount = ICX.multiply(BigInteger.valueOf(0));
        for (int i = 0; i < wallets.length; i++) {
            wallets[i] = KeyWallet.create();
            Bytes txHash = txHandler.transfer(networkId, wallets[0], wallets[i].getAddress(), amount);
            System.out.println("   == transfer hash: " + txHash);
        }
        ownerWallet = wallets[0];
        System.out.println("   == ownerWallet: " + ownerWallet.getAddress());
        callers = new KeyWallet[wallets.length-1];
        for (int i = 1; i < wallets.length; i++) {
            callers[i-1] = wallets[i];
            System.out.println("   == callers Wallet: " + callers[i-1].getAddress());
        }
    }




    private byte[] getRandomBytes(int size) {
        byte[] bytes = new byte[size];
        secureRandom.nextBytes(bytes);
        bytes[0] = 0; // make positive
        return bytes;
    }

    @Test
    public void testIRC3Token() throws Exception {
        Bytes txHash;
        init();
        initWallet();
        // 1. deploy);
        byte[] jar = Files.readAllBytes(Paths.get(jarPath));
        IRC3TokenScore tokenScore = IRC3TokenScore.mustDeploy("NFT", "NFTTest", networkId, jar, txHandler, ownerWallet);

        for(KeyWallet caller : callers){
            // 1. mint some tokens
            BigInteger[] tokenId = new BigInteger[] {
                    new BigInteger(getRandomBytes(8)),
                    new BigInteger(getRandomBytes(8)),
                    new BigInteger(getRandomBytes(8)),
                    new BigInteger(getRandomBytes(8)),
            };
            for (int i = 0; i < tokenId.length; i++) {
                txHash = tokenScore.mint(networkId, ownerWallet, tokenId[i]);
                System.out.println("   == mint hash: " + txHash);
            }

            BigInteger token = tokenId[0];
            txHash = tokenScore.transfer(networkId, ownerWallet, caller.getAddress(), token);
            System.out.println("   == transfer hash: " + txHash);

            // 5. approve and check
            token = tokenId[1];
            txHash = tokenScore.approve(networkId, ownerWallet, caller.getAddress(), token);
            System.out.println("   == approve hash: " + txHash);

            txHash = tokenScore.transferFrom(networkId, caller, ownerWallet.getAddress(), caller.getAddress(), token);
            System.out.println("   == transferFrom hash: " + txHash);

            // 6. burn and check
            BigInteger balance = tokenScore.balanceOf(ownerWallet.getAddress());
            token = tokenScore.tokenOfOwnerByIndex(ownerWallet.getAddress(), 0);
            txHash = tokenScore.burn(networkId, ownerWallet, token);
            System.out.println("   == burn hash: " + txHash);
            showTokenStatus(tokenScore, caller);
        }

    }

    private void showTokenStatus(IRC3TokenScore tokenScore, KeyWallet caller) throws Exception {
        BigInteger totalSupply = tokenScore.totalSupply();
        System.out.println(">>> totalSupply = " + totalSupply);
        for (int i = 0; i < totalSupply.intValue(); i++) {
            BigInteger token = tokenScore.tokenByIndex(i);
            Address owner = tokenScore.ownerOf(token);
            Address approved = tokenScore.getApproved(token);
            System.out.printf("   [%s](%s)<%s>\n", token, owner,
                    approved.equals(ZERO_ADDRESS) ? "null" : approved);
        }
        BigInteger ownerBalance = tokenScore.balanceOf(ownerWallet.getAddress());
        System.out.println("   == balanceOf owner: " + ownerBalance);
        for (int i = 0; i < ownerBalance.intValue(); i++) {
            BigInteger token = tokenScore.tokenOfOwnerByIndex(ownerWallet.getAddress(), i);
            System.out.printf("     -- %d: [%s]\n", i, token);
        }
        BigInteger callerBalance = tokenScore.balanceOf(caller.getAddress());
        System.out.println("   == balanceOf caller: " + callerBalance);
        for (int i = 0; i < callerBalance.intValue(); i++) {
            BigInteger token = tokenScore.tokenOfOwnerByIndex(caller.getAddress(), i);
            System.out.printf("     -- %d: [%s]\n", i, token);
        }
    }
}

