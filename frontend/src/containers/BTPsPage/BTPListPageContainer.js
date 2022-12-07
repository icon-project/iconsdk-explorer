import { connect } from 'react-redux';
import { BTPListPage } from 'components';
import { withRouter } from 'react-router-dom';
import { btpList } from "../../redux/actions/btpsActions";
import { networkList } from "../../redux/actions/networksActions";

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.btps,
    ...state.networks
  };
}

function mapDispatchToProps(dispatch) {
  return {
    btpList: (payload) => dispatch(btpList(payload)),
    networkList: (payload) => dispatch(networkList(payload))
  };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(BTPListPage));

