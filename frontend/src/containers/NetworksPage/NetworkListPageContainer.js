import { connect } from 'react-redux';
import { NetworkListPage } from 'components'
import { withRouter } from 'react-router-dom';
import { networkList } from "../../redux/actions/networksActions";

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.networks
  };
}

function mapDispatchToProps(dispatch) {
  return {
    networkList: (payload) => dispatch(networkList(payload)),
  };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(NetworkListPage));

