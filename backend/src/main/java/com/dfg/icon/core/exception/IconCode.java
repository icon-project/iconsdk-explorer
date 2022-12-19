package com.dfg.icon.core.exception;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public enum IconCode implements CustomErrorCode {

	//HashMap<String, String> tx_result_map = new  HashMap<String,String>();

	/*기본 응답 */
	OK(HttpStatus.OK, "OK"),
	NO_DATA("No Data" , "No Data"),
	SUCCESS("200","success"),
	DUPLICATE("208", "already data"),
	TOO_LARGE("413", "file too large"),
	ERROR("404","ERROR"),

	/* 통신관련 */
	SESSION_EXPIRED(HttpStatus.INTERNAL_SERVER_ERROR, "Session Expired"),
	INVALID_AUTHENTICATION(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Authentication"),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Invalid Password"),
	PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "Permission Denied"),
	BLOCK_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Block Error"),
	BTP_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "BTP Error"),
	TRANSACTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Transaction Error"),
	BALANCE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GetBalance Error"),
	PREP_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Prep Request Errror"),
	EXCHANGE_ACCESS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Exchange Access Fail"),

	/* IRC Version*/
	IRC_NOIRC("-", "No IRC Format"),
	IRC2_TOKEN("IRC2", "Token IRC"),
	IRC3_TOKEN("IRC3", "NON FUNGIBLE Token IRC"),

	/* API Version*/
	SCHEDULER_VER0("v0", "version0"),
	SCHEDULER_VER1("v1", "version1"),
	SCHEDULER_VER2("v2", "version2"),
	SCHEDULER_VER3("v3", "version3"),

	/* List Page*/
//	LIST_PAGE_TYPE10("10", "10"),
//	LIST_PAGE_TYPE20("20", "20"),

	/* TX Version*/
	TXNVERSION_2("0x2", "ver2"),
	TXNVERSION_3("0x3", "ver3"),

	/*tx data type */
	TXDATATYPE_ICX("icx", "icx"), // if dataType is null, set dataType as icx - default value
	TXDATATYPE_BASE("base", "base"),
	TXDATATYPE_DEPLOY("deploy", "deploy"),
	TXDATATYPE_CALL("call", "call"),
	TXDATATYPE_MESSAGE("message", "message"),

	TX_METHOD_TYPE_TRANSFER("transfer", "transfer"),
	TX_METHOD_TYPE_ACCEPTSCORE("acceptScore", "acceptScore"),
	TX_METHOD_TYPE_REJECTSCORE("rejectScore", "rejectScore"),
	TX_METHOD_TYPE_ADD_DEPLOYER("addDeployer", "addDeployer"),
	TX_METHOD_TYPE_ADD_BLACKLIST("addToScoreBlackList", "addToScoreBlackList"),
	TX_METHOD_TYPE_REGISTER_PROPOSAL("registerProposal", "registerProposal"),
	TX_METHOD_TYPE_CANCEL_PROPOSAL("cancelProposal","cancelProposal"),
	TX_METHOD_TYPE_VOTE_PROPOSAL("voteProposal", "voteProposal"),

	TX_METHOD_TYPE_SET_DELEGATION("setDelegation", "setDelegation"),

	/*Score Deploy addr*/
	SCORE_INSTALL_ADDR("cx0000000000000000000000000000000000000000"," Score Deploy addr"),
	SCORE_CONTROL_ADDR("cx0000000000000000000000000000000000000001"," Score Control addr"),
	TREASURY_ADDR("hx0000000000000000000000000000000000000000", "treasury addr"),

	/*tx result code */
	TX_RESULT_SUCCESS("1", "success"),
	TX_RESULT_FAILURE("0", "failure"),
	
	/*SCORE state */
	SCORE_PENDING("0", "padding"),
	SCORE_ACCEPT("1", "success"),
	SCORE_REJECT("2", "reject"),
	SCORE_ERROR("3", "error"),
	SCORE_CANCELED("4", "canceled"),
	WRONG_SCORE("WRONG SCORE","WRONG SCORE"),

	/* Start - Event Log Method of TX (DataType is base) */
	SCORE_METHOD_REWARDFUND_TRANSFER("RewardFundTransferred(str,Address,Address,int)", "reward fund transferred"), // parameters: name, from, to, amount
	SCORE_METHOD_ICX_ISSUED("ICXIssued(int,int,int,int)", "icx issued"), // parameters: int, int, int, int
	SCORE_METHOD_PROPOSAL_EXPIRED("NetworkProposalExpired(bytes)", "proposal is expired"),
	SCORE_METHOD_PROPOSAL_DISAPPROVED("NetworkProposalDisapproved(bytes)", "proposal is disapproved"),
	SCORE_METHOD_SLASHED("Slashed(Address,Address,int)", "slashed"),
	SCORE_METHOD_PENALTY_IMPOSED("PenaltyImposed(Address,int,int)", "penalty imposed"),
	SCORE_METHOD_REWARDFUND_BURNED("RewardFundBurned(str,Address,int)", "reward fund burned"),
	SCORE_METHOD_ICX_BURNED_V2("ICXBurnedV2(Address,int,int)", "icx burned v2"),
	SCORE_METHOD_TERM_STARTED("TermStarted(int,int,int)", "term started"),
	SCORE_METHOD_PREP_ISSUED("PRepIssued(int,int,int,int)", "prep issued"),
	/* End - Event Log Method of TX (DataType is base) */
	SCORE_METHOD_IRC2_TRANSFER("Transfer(Address,Address,int,bytes)","IRC2 Transfer method "),
	SCORE_METHOD_IRC3_TRANSFER("Transfer(Address,Address,int)","IRC3 Transfer method "),
	SCORE_METHOD_IRC3_APPROVAL("Approval(Address,Address,int)","IRC3 Approval method "),
	SCORE_METHOD_ICXSEND("ICXTransfer(Address,Address,int)","icx send method  "),

	/* BTP Event Log */
	SCORE_METHOD_BTP_NETWORK_OPEN("BTPNetworkOpened(int,int)","btp network open method  "),
	SCORE_METHOD_BTP_MESSAGE("BTPMessage(int,int)","btp message method  "),

	SCORE_METHOD_ACCEPT("Accepted(str)", "score deploy accept method"),
	SCORE_METHOD_REJECT("Rejected(str,str)", "score deploy reject method"),
	SCORE_METHOD_ISCORE_CLAIM("IScoreClaimed(int,int)", "claim IScore"),
	SCORE_METHOD_ISCORE_CLAIM_V2("IScoreClaimedV2(Address,int,int)", "claim IScore"),

	/*tx type code*/
	TX_TYPE_ICX("0" , "icx transfer"),
	TX_TYPE_TOKEN("1" , "token transfer"),
	TX_TYPE_CALL("2" , "score call"),
	TX_TYPE_INSTALL("3", "score install"),
	TX_TYPE_UPDATE("4" , "score update"),
	TX_TYPE_INSTALL_ACCEPT("5", "score accept"),
	TX_TYPE_INSTALL_REJECT("6", "score reject"),
	TX_TYPE_UPDATE_ACCEPT("7", "update accept"),
	TX_TYPE_UPDATE_REJECT("8", "update reject"),
	TX_TYPE_UPDATE_CANCELED("9", "update canceled"),

	TX_TYPE_REGISTER_PREP("10", "registerPRep"),
	TX_TYPE_UNREGISTER_PREP("11", "unregisterPRep"),
	TX_TYPE_SET_PREP("12", "setPRep"),
	TX_TYPE_SET_STAKE("13", "setStake"),
	TX_TYPE_SET_DELEGATTION("14", "setDelegation"),
	TX_TYPE_CLAIM_ISCORE("15", "claimIScore"),

	TX_TYPE_REGISTER_PROPOSAL("16", "registerProposal"),
	TX_TYPE_CANCEL_PROPOSAL("17", "cancelProposal"),
	TX_TYPE_VOTE_PROPOSAL("18", "voteProposal"),

	
	/* Defalut Token Info */
	TOKEN_DEFAULT_DECIMALS("0x12","defalut decimal"),
	TOKEN_DEFAULT_SYMBOL("???","unknwon symbol"),

	/* pageMove */
	pageKind_Pre("pre","move pre page"),
	pageKind_Next("next","move next page"),
	
	
	/*tx result code */
	TX_RESULT_ERROR("tx_result_error", "[ {\"code\":\"0x7d00\" , \"value\":\"Out Of Balance\"} ,  {\"code\":\"0x0000\" , \"value\":\"Unknown ERROR\"} ]" ),
	;

	private HttpStatus status;
	private String message;
	private String code;
	
	private IconCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	private IconCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	@Override
	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getCode() {
		return code;
	}

	/** Tx 결과 Error 코드 조회  
	 * @param code
	 * @return
	 * @throws JSONException
	 */
	public static String getTxResultErrorCode(String code) throws JSONException{
		  JSONArray ja = new JSONArray(TX_RESULT_ERROR.getMessage());
		  for (int i = 0; i < ja.length(); i++){
		      JSONObject jData = ja.getJSONObject(i);
		      if(jData.getString("code").equals(code)) {
		    	  return jData.getString("value"); 
		      }
		   }
		return null;
	}
}
