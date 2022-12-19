package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.v3.vo.AddressType;
import com.dfg.icon.core.v3.vo.HexType;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * param for txQuery
 * @author LYJ
 */
public class PageReq {
    String hash;

    Integer height;
    /**
     * 트랜잭션 해시 조건값
     */
    String txHash;

    /**
     * 조회할 페이지
     */
    Integer page;

    /**
     * 페이지당 조회할 개수
     */
    int counting;

    /**
     * 주소 조건값
     */
    String address;
    String address2;

    /**
     * 주소 타입 조건값
     */
    String nodeType;
    Byte txType;
    Byte addrType;

    /**
     * IRC 버전
     */
    String ircVersion;

    /**
     * 0 : pending, 1 : success, 2 : fail
     */
    Integer state;
    String startDate;
    String endDate;
    String type;
    String icxSupply;
    String keyword;

    Integer startId;
    Integer endId;

    String orderBy;

    /**
     * id  optional
     */
    int id;

    Byte grade;

    /**
     *  page 확인
     *  pre / next
     */
    String pageKind;

    // 기본 20개 조회
    public PageReq() {
        counting = 20;
        orderBy = "DESC";
    }

    // address별 10개 조회
    public PageReq(int counting) {
        if(counting > 100) {
            counting = 100;
        }
        this.counting = counting;
        orderBy = "DESC";
    }

    public void setIrcVersion(String ircVersion) { this.ircVersion = ircVersion;}

    public String getIrcVersion() { return this.ircVersion; }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getCounting() {
        return counting;
    }

    public void setCounting(int counting) {
        if(counting > 100) {
            counting = 100;
        }
        this.counting = counting;
    }

    public void setCountingUnLimit(int counting) {
        this.counting = counting;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Byte getTxType() {
        return txType;
    }

    public void setTxType(Byte txType) {
        this.txType = txType;
    }

    public Byte getAddrType() {
        return addrType;
    }

    public void setAddrType(Byte addrType) {
        this.addrType = addrType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcxSupply() {
        return icxSupply;
    }

    public void setIcxSupply(String icxSupply) {
        this.icxSupply = icxSupply;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageKind() {
        return pageKind;
    }

    public void setPageKind(String pageKind) {
        this.pageKind = pageKind;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean checkReverse(Integer totalCount) {
        if(totalCount/2 > this.page) {
            // 앞페이지면 기존대로
            return true;
        } else {
            // 뒤페이지면 오름조회 이후 내림
            int newPage = totalCount - this.page - this.counting;
            if(newPage < 0) {
                this.counting = this.counting + newPage;
                newPage = 0;
                if(this.counting < 1) {
                    return false;
                }
            }
            this.page = newPage;
            this.orderBy = "ASC";
        }

        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
