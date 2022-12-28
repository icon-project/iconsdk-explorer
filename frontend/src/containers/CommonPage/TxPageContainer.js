import { connect } from 'react-redux';
import { TxPage } from 'components';
import { withRouter } from 'react-router-dom';
import { 
  contractTxList,
  contractInternalTxList,
  contractTokenTxList,
  contractEventLogList
} from '../../redux/actions/contractsActions';
import { 
  addressList,
  addressTxList,
  addressInternalTxList,
  addressTokenTxList,
} from '../../redux/actions/addressesActions';
import { 
  transactionRecentTx,
  transactionEventLogList,
  transactionInternalTxList
} from '../../redux/actions/transactionsActions';
import { 
  tokenTxList,
  tokenTransfersList,
  tokenHoldersList
} from '../../redux/actions/tokensActions';
import { 
  blockList,
  blockTxList,
} from '../../redux/actions/blocksActions';
import {
  btpList,
  btpTxList
} from "../../redux/actions/btpsActions";
import {
  networkList,
  networkBTPList
} from "../../redux/actions/networksActions"
import {
  nftList,
  nftTxList,
  nftTransfersList,
  nftHoldersList
} from "../../redux/actions/nftsActions"

function mapStateToProps(state) {
  return {
    url: state.router.location,
    contractTx: state.contracts.contractTx,
    contractInternalTx: state.contracts.contractInternalTx,
    contractTokenTx: state.contracts.contractTokenTx,
    contractEvents: state.contracts.contractEvents,
    addresses: state.addresses.addresses,
    walletTx: state.addresses.walletTx,
    addressInternalTx: state.addresses.addressInternalTx,
    walletTokenTx: state.addresses.walletTokenTx,
    recentTx: state.transactions.recentTx,
    recentTokenTx: state.tokens.recentTokenTx,
    blocks: state.blocks.blocks,
    blockTx: state.blocks.blockTx,
    tokenTransfers: state.tokens.tokenTransfers,
    tokenHolders: state.tokens.tokenHolders,
    transactionEvents: state.transactions.transactionEvents,
    transactionInternalTx: state.transactions.transactionInternalTx,
    btps: state.btps.btps,
    btpTx: state.btps.btpTx,
    networks: state.networks.networks,
    networkBTPs: state.networks.networkBTPs,
    nfts: state.nfts.nfts,
    nftTx: state.nfts.nftTx,
    nftTransfers: state.nfts.nftTransfers,
    nftHolders: state.nfts.nftHolders,
    recentNftTx: state.nfts.recentNftTx,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    contractTxList: payload => dispatch(contractTxList(payload)),
    contractInternalTxList: payload => dispatch(contractInternalTxList(payload)),
    contractTokenTxList: payload => dispatch(contractTokenTxList(payload)),
    contractEventLogList: payload => dispatch(contractEventLogList(payload)),
    addressList: payload => dispatch(addressList(payload)),
    addressInternalTxList: payload => dispatch(addressInternalTxList(payload)),
    addressTxList: payload => dispatch(addressTxList(payload)),
    addressTokenTxList: payload => dispatch(addressTokenTxList(payload)),
    transactionRecentTx: payload => dispatch(transactionRecentTx(payload)),
    tokenTxList: payload => dispatch(tokenTxList(payload)),
    blockList: payload => dispatch(blockList(payload)),
    blockTxList: payload => dispatch(blockTxList(payload)),
    tokenTransfersList: payload => dispatch(tokenTransfersList(payload)),
    tokenHoldersList: payload => dispatch(tokenHoldersList(payload)),
    transactionEventLogList: payload => dispatch(transactionEventLogList(payload)),
    transactionInternalTxList: payload => dispatch(transactionInternalTxList(payload)),
    btpList: payload => dispatch(btpList(payload)),
    btpTxList: payload => dispatch(btpTxList(payload)),
    networkList: payload => dispatch(networkList(payload)),
    networkBTPList: payload => dispatch(networkBTPList(payload)),
    nftList: payload => dispatch(nftList(payload)),
    nftTxList: payload => dispatch(nftTxList(payload)),
    nftTransfersList: payload => dispatch(nftTransfersList(payload)),
    nftHoldersList: payload => dispatch(nftHoldersList(payload))
  };
}

const TxPageContainer = withRouter(connect(mapStateToProps, mapDispatchToProps)(TxPage));
export default TxPageContainer;
