import { connect } from 'react-redux';
import { NFTListPage } from 'components';
import { withRouter } from 'react-router-dom';
import { 
  nftList,
  nftListSearch
} from '../../redux/actions/nftsActions'

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.nfts
  };
}

function mapDispatchToProps(dispatch) {
  return {
    nftList: payload => dispatch(nftList(payload)),
    nftListSearch: payload => dispatch(nftListSearch(payload))
  };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(NFTListPage));

