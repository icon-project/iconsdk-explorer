const actionTypes = {
  getMainInfo: 'GET_MAIN_INFO',
  getMainInfoFulfilled: 'GET_MAIN_INFO_FULFILLED',
  getMainInfoRejected: 'GET_MAIN_INFO_REJECTED',

  getMainChart: 'GET_MAIN_CHART',
  getMainChartFulfilled: 'GET_MAIN_CHART_FULFILLED',
  getMainChartRejected: 'GET_MAIN_CHART_REJECTED',

  addressList: 'ADDRESS_LIST',
  addressListFulfilled: 'ADDRESS_LIST_FULFILLED',
  addressListRejected: 'ADDRESS_LIST_REJECTED',

  blockList: 'BLOCK_LIST',
  blockListFulfilled: 'BLOCK_LIST_FULFILLED',
  blockListRejected: 'BLOCK_LIST_REJECTED',

  transactionTxDetail: 'TRANSACTION_TX_DETAIL',
  transactionTxDetailFulfilled: 'TRANSACTION_TX_DETAIL_FULFILLED',
  transactionTxDetailRejected: 'TRANSACTION_TX_DETAIL_REJECTED',

  search: 'SEARCH',
  searchFulfilled: 'SEARCH_FULFILLED',
  searchRejected: 'SEARCH_REJECTED',
  searchErrorReset: 'SEARCH_ERROR_RESET',

  addressInfo: 'ADDRESS_INFO',
  addressInfoFulfilled: 'ADDRESS_INFO_FULFILLED',
  addressInfoRejected: 'ADDRESS_INFO_REJECTED',

  addressInternalTxList: 'ADDRESS_INTERNAL_TX_LIST',
  addressInternalTxListFulfilled: 'ADDRESS_INTERNAL_TX_LIST_FULFILLED',
  addressInternalTxListRejected: 'ADDRESS_INTERNAL_TX_LIST_REJECTED',
  
  addressTokenTxList: 'ADDRESS_TOKEN_TX_LIST',
  addressTokenTxListFulfilled: 'ADDRESS_TOKEN_TX_LIST_FULFILLED',
  addressTokenTxListRejected: 'ADDRESS_TOKEN_TX_LIST_REJECTED',

  blockInfo: 'BLOCK_INFO',
  blockInfoFulfilled: 'BLOCK_INFO_FULFILLED',
  blockInfoRejected: 'BLOCK_INFO_REJECTED',

  blockTxList: 'BLOCK_TX_LIST',
  blockTxListFulfilled: 'BLOCL_TX_LIST_FULFILLED',
  blockTxListRejected: 'BLOCK_TX_LIST_REJECTED',

  transactionRecentTx: 'TRANSACTION_RECENT_TX',
  transactionRecentTxFulfilled: 'TRANSACTION_RECENT_TX_FULFILLED',
  transactionRecentTxRejected: 'TRANSACTION_RECENT_TX_REJECTED',

  transactionEventLogList: 'TRANSACTION_EVENT_LOG_LIST',
  transactionEventLogListFulfilled: 'TRANSACTION_EVENT_LOG_LIST_FULFILLED',
  transactionEventLogListRejected: 'TRANSACTION_EVENT_LOG_LIST_REJECTED',
  
  transactionInternalTxList: 'TRANSACTION_INTERNAL_TX_LIST',
  transactionInternalTxListFulfilled: 'TRANSACTION_INTERNAL_TX_LIST_FULFILLED',
  transactionInternalTxListRejected: 'TRANSACTION_INTERNAL_TX_LIST_REJECTED',

  contractList: 'SELECT_CONTRACT_LIST',
  contractListFulfilled: 'SELECT_CONTRACT_LIST_FULFILLED',
  contractListRejected: 'SELECT_CONTRACT_LIST_REJECTED',
  
  contractListSearch: 'SELECT_CONTRACT_LIST_SEARCH',
  contractListSearchFulfilled: 'SELECT_CONTRACT_LIST_SEARCH_FULFILLED',
  contractListSearchRejected: 'SELECT_CONTRACT_LIST_SEARCH_REJECTED',

  contractTokenTxList: 'CONTRACT_TOKEN_TX_LIST',
  contractTokenTxListFulfilled: 'CONTRACT_TOKEN_TX_LIST_FULFILLED',
  contractTokenTxListRejected: 'CONTRACT_TOKEN_TX_LIST_REJECTED',

  contractInfo: 'CONTRACT_INFO',
  contractInfoFulfilled: 'CONTRACT_INFO_FULFILLED',
  contractInfoRejected: 'CONTRACT_INFO_REJECTED',

  contractDetail: 'CONTRACT_DETAIL',
  contractDetailFulfilled: 'CONTRACT_DETAIL_FULFILLED',
  contractDetailRejected: 'CONTRACT_DETAIL_REJECTED',

  contractDetailPopup: 'CONTRACT_DETAIL_POPUP',
  imageConverterPopup: 'IMAGE_CONVERTER_POPUP',

  contractTxList: 'CONTRACT_TX_LIST',
  contractTxListFulfilled: 'CONTRACT_TX_LIST_FULFILLED',
  contractTxListRejected: 'CONTRACT_TX_LIST_REJECTED',

  contractInternalTxList: 'CONTRACT_INTERNAL_TX_LIST',
  contractInternalTxListFulfilled: 'CONTRACT_INTERNAL_TX_LIST_FULFILLED',
  contractInternalTxListRejected: 'CONTRACT_INTERNAL_TX_LIST_REJECTED',

  contractEventLogList: 'CONTRACT_EVENT_LOG_LIST',
  contractEventLogListFulfilled: 'CONTRACT_EVENT_LOG_LIST_FULFILLED',
  contractEventLogListRejected: 'CONTRACT_EVENT_LOG_LIST_REJECTED',

  addressTxList: 'ADDRESS_TX_LIST',
  addressTxListFulfilled: 'ADDRESS_TX_LIST_FULFILLED',
  addressTxListRejected: 'ADDRESS_TX_LIST_REJECTED',

  icxGetScore: 'ICX_GET_SCORE',
  icxGetScoreFulfilled: 'ICX_GET_SCORE_FULFILLED',
  icxGetScoreRejected: 'ICX_GET_SCORE_REJECTED',

  tokenList: 'TOKEN_LIST',
  tokenListFulfilled: 'TOKEN_LIST_FULFILLED',
  tokenListRejected: 'TOKEN_LIST_REJECTED',
  
  tokenListSearch: 'TOKEN_LIST_SEARCH',
  tokenListSearchFulfilled: 'TOKEN_LIST_FULFILLED_SEARCH',
  tokenListSearchRejected: 'TOKEN_LIST_REJECTED_SEARCH',
  
  tokenTxList: 'TOKEN_TX_LIST',
  tokenTxListFulfilled: 'TOKEN_TX_LIST_FULFILLED',
  tokenTxListRejected: 'TOKEN_TX_LIST_REJECTED',

  tokenSummary: 'TOKEN_SUMMARY',
  tokenSummaryFulfilled: 'TOKEN_SUMMARY_FULFILLED',
  tokenSummaryRejected: 'TOKEN_SUMMARY_REJECTED',

  tokenTransfersList: 'TOKEN_TRANSFERS_LIST',
  tokenTransfersListFulfilled: 'TOKEN_TRANSFERS_LIST_FULFILLED',
  tokenTransfersListRejected: 'TOKEN_TRANSFERS_LIST_REJECTED',

  tokenHoldersList: 'TOKEN_HOLDERS_LIST',
  tokenHoldersListFulfilled: 'TOKEN_HOLDERS_LIST_FULFILLED',
  tokenHoldersListRejected: 'TOKEN_HOLDERS_LIST_REJECTED',

  initPopup: 'INIT_POPUP',
  setPopup: 'SET_POPUP',

  readContractInformation: 'READ_CONTRACT_INFORMATION',
  readContractInformationFulfilled: 'READ_CONTRACT_INFORMATION_FULFILLED',
  readContractInformationRejected: 'READ_CONTRACT_INFORMATION_REJECTED',

  icxCall: 'ICX_CALL',
  icxCallFulfilled: 'ICX_CALL_FULFILLED',
  icxCallRejected: 'ICX_CALL_REJECTED',

  setAddress: 'SET_ADDRESS',
  setAddressSuccess :'SET_ADDRESS_SUCCESS',
  setNotification: 'SET_NOTIFICATION',
  setNotificationSuccess :'SET_NOTIFICATION_SUCCESS',
  clearWallet: 'CLEAR_WALLET',
  clearWalletSuccess: 'CLEAR_WALLET_SUCCESS',

  chainInfoList: 'CHAIN_INFO_LIST',
  chainInfoListFulfilled: 'CHAIN_INFO_LIST_FULFILLED',
  chainInfoListRejected: 'CHAIN_INFO_LIST_REJECTED',

  btpList: 'BTP_LIST',
  btpListFulfilled: 'BTP_LIST_FULFILLED',
  btpListRejected: 'BTP_LIST_REJECTED',

  btpInfo: 'BTP_INFO',
  btpInfoFulfilled: 'BTP_INFO_FULFILLED',
  btpInfoRejected: 'BTP_INFO_REJECTED',

  btpTxList: 'BTP_TX_LIST',
  btpTxListFulfilled: 'BTP_TX_LIST_FULFILLED',
  btpTxListRejected: 'BTP_TX_LIST_REJECTED',

  btpMessage: 'BTP_MESSAGE',
  btpMessageFulfilled: 'BTP_MESSAGE_FULFILLED',
  btpMessageRejected: 'BTP_MESSAGE_REJECTED',

  networkList: 'NETWORK_LIST',
  networkListFulfilled: 'NETWORK_LIST_FULFILLED',
  networkListRejected: 'NETWORK_LIST_REJECTED',

  networkInfo: 'NETWORK_INFO',
  networkInfoFulfilled: 'NETWORK_INFO_FULFILLED',
  networkInfoRejected: 'NETWORK_INFO_REJECTED',

  networkBTPList: 'NETWORK_BTP_LIST',
  networkBTPListFulfilled: 'NETWORK_BTP_LIST_FULFILLED',
  networkBTPListRejected: 'NETWORK_BTP_LIST_REJECTED',

  nftList: 'NFT_LIST',
  nftListFulfilled: 'NFT_LIST_FULFILLED',
  nftListRejected: 'NFT_LIST_REJECTED',

  nftListSearch: 'NFT_LIST_SEARCH',
  nftListSearchFulfilled: 'NFT_LIST_FULFILLED_SEARCH',
  nftListSearchRejected: 'NFT_LIST_REJECTED_SEARCH',

  nftTxList: 'NFT_TX_LIST',
  nftTxListFulfilled: 'NFT_TX_LIST_FULFILLED',
  nftTxListRejected: 'NFT_TX_LIST_REJECTED',

  nftSummary: 'NFT_SUMMARY',
  nftSummaryFulfilled: 'NFT_SUMMARY_FULFILLED',
  nftSummaryRejected: 'NFT_SUMMARY_REJECTED',

  nftTransfersList: 'NFT_TRANSFERS_LIST',
  nftTransfersListFulfilled: 'NFT_TRANSFERS_LIST_FULFILLED',
  nftTransfersListRejected: 'NFT_TRANSFERS_LIST_REJECTED',

  nftHoldersList: 'NFT_HOLDERS_LIST',
  nftHoldersListFulfilled: 'NFT_HOLDERS_LIST_FULFILLED',
  nftHoldersListRejected: 'NFT_HOLDERS_LIST_REJECTED',
}

export default actionTypes
