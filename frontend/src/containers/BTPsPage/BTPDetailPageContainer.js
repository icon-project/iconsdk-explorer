import { connect } from 'react-redux';
import { BTPDetailPage } from 'components';
import { withRouter } from 'react-router-dom';
import {
  btpInfo,
  btpTxList,
} from '../../redux/actions/btpsActions';
import {
  networkList,
  networkInfo
} from "../../redux/actions/networksActions";

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.btps,
    ...state.networks,
    ...state.network
  };
}

function mapDispatchToProps(dispatch) {
  return {
    btpInfo: (payload) => dispatch(btpInfo(payload)),
    btpTxList: (payload) => dispatch(btpTxList(payload)),
    networkList: (payload) => dispatch(networkList(payload)),
    networkInfo: (payload) => dispatch(networkInfo(payload))
  };
}

const BTPDetailPageContainer = withRouter(connect(mapStateToProps, mapDispatchToProps)(BTPDetailPage));

export default BTPDetailPageContainer;
