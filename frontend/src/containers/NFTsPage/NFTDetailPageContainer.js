import { connect } from 'react-redux';
import { NFTDetailPage } from 'components';
import { withRouter } from 'react-router-dom';
import {
  nftSummary,
  nftTransfersList,
  nftHoldersList
} from '../../redux/actions/nftsActions'
import {
  icxCall,
  readContractInformation
} from '../../redux/actions/contractsActions'

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.nfts,
    contractReadInfo: state.contracts.contractReadInfo
  };
}

function mapDispatchToProps(dispatch) {
  return {
    nftSummary: payload => dispatch(nftSummary(payload)),
    nftTransfersList: payload => dispatch(nftTransfersList(payload)),
    nftHoldersList: payload => dispatch(nftHoldersList(payload)),
    icxCall: payload => dispatch(icxCall(payload)),        
    readContractInformation: payload => dispatch(readContractInformation(payload))
  };
}

const NFTDetailPageContainer = withRouter(connect(mapStateToProps, mapDispatchToProps)(NFTDetailPage));

export default NFTDetailPageContainer;
