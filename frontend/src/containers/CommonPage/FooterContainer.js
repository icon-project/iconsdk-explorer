import { connect } from 'react-redux';
import { Footer } from 'components';
import {chainInfoList} from "../../redux/actions/chainsActions";

function mapStateToProps(state) {
  return {
    chainInfos: state.chains.chainInfos
  };
}

function mapDispatchToProps(dispatch) {
  return {
    chainInfoList: () => dispatch(chainInfoList()),
  };
}

const FooterContainer = connect(mapStateToProps, mapDispatchToProps)(Footer);

export default FooterContainer;
