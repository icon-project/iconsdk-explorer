import { connect } from 'react-redux';
import { Header } from 'components';
import { search } from '../../redux/actions/searchActions';
import { chainInfoList } from "../../redux/actions/chainsActions"
import { setAddress, clearWallet } from "../../redux/actions/storageActions"

function mapStateToProps(state) {
  return {
    loading: state.search.loading,
    walletAddress: state.storage.walletAddress,
    chainInfos: state.chains.chainInfos
  };
}

function mapDispatchToProps(dispatch) {
  return {
    search: param => dispatch(search(param)),
    setAddress: payload => dispatch(setAddress(payload)),
    clearWallet: () => dispatch(clearWallet()),
    chainInfoList: () => dispatch(chainInfoList()),
  };
}

const HeaderContainer = connect(mapStateToProps, mapDispatchToProps)(Header);

export default HeaderContainer;
