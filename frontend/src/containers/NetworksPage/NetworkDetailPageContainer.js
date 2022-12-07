import { connect } from 'react-redux';
import { NetworkDetailPage } from 'components';
import { withRouter } from 'react-router-dom';
import {
  networkInfo,
  networkBTPList
} from "../../redux/actions/networksActions";

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.networks
  };
}

function mapDispatchToProps(dispatch) {
  return {
    networkInfo: (payload) => dispatch(networkInfo(payload)),
    networkBTPList: (payload) => dispatch(networkBTPList(payload))
  };
}

const NetworkDetailPageContainer = withRouter(connect(mapStateToProps, mapDispatchToProps)(NetworkDetailPage));

export default NetworkDetailPageContainer;
