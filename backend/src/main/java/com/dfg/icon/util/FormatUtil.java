package com.dfg.icon.util;


import java.util.List;


import com.dfg.icon.web.v3.dto.RecentBlock;
import com.dfg.icon.web.v0.dto.RecentBlockDto;
import com.dfg.icon.web.v0.dto.block.Transaction;
import com.dfg.icon.web.v3.dto.TxDetail;
import com.dfg.icon.web.v3.dto.TxDetailDto;


public class FormatUtil {

  
    // 소숫점 4자리 까지 format
    public static List<Transaction> txFormat(List<Transaction> list) {

        for (int i = 0; i < list.size(); i++) {
            double getAmount = Double.parseDouble(list.get(i).getAmount());
            String amount = String.format("%.4f", getAmount);
            list.get(i).setAmount(amount);
        }

        return list;
    }

    public static List<TxDetail> txDetailFormat(List<TxDetail> list) {

        for (int i = 0; i < list.size(); i++) {
            double getAmount = Double.parseDouble(list.get(i).getAmount());
            String amount = String.format("%.4f", getAmount);
            list.get(i).setAmount(amount);
        }

        return list;
    }
    
    
    public static List<TxDetailDto> txDetailV3Format(List<TxDetailDto> list) {

        for (int i = 0; i < list.size(); i++) {
            double getAmount = Double.parseDouble(list.get(i).getAmount());
            String amount = String.format("%.4f", getAmount);
            list.get(i).setAmount(amount);
        }

        return list;
    }
    
    public static List<RecentBlock> blockFormat(List<RecentBlock> list) {
        
        for (RecentBlock rb : list) {
            if (rb.getAmount() != null && !"".equals(rb.getAmount())) {
                double getAmount = Double.parseDouble(rb.getAmount());
                rb.setAmount(String.format("%.4f", getAmount));
            } else {
                rb.setAmount("-");
            }
        }
        return list;
     }
    
}
