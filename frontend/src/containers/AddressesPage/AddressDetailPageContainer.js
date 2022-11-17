import { connect } from 'react-redux';
import { AddressDetailPage } from 'components';
import { withRouter } from 'react-router-dom';
import { 
  addressInfo,
  addressTxList,
  addressInternalTxList,
  addressTokenTxList,
} from '../../redux/actions/addressesActions';
import { 
  setPopup 
} from '../../redux/actions/popupActions'
import { 
  setNotification,
} from '../../redux/actions/storageActions';

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.addresses,
    walletAddress: state.storage.walletAddress,
    walletNotification: state.storage.walletNotification
  };
}

function mapDispatchToProps(dispatch) {
  return {
    addressInfo: (payload) => dispatch(addressInfo(payload)),
    addressTxList: (payload) => dispatch(addressTxList(payload)),
    addressInternalTxList: (payload) => dispatch(addressInternalTxList(payload)),
    addressTokenTxList: (payload) => dispatch(addressTokenTxList(payload)),
    setNotification: (payload) => dispatch(setNotification(payload)),
    setPopup: (payload) => dispatch(setPopup(payload))
  };
}

const AddressListPageContainer = withRouter(connect(mapStateToProps, mapDispatchToProps)(AddressDetailPage));

export default AddressListPageContainer;
