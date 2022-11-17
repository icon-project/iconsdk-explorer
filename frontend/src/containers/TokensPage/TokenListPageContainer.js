import { connect } from 'react-redux';
import { TokenListPage } from 'components';
import { withRouter } from 'react-router-dom';
import { 
  tokenList,
  tokenListSearch
} from '../../redux/actions/tokensActions'

function mapStateToProps(state) {
  return {
    url: state.router.location,
    ...state.tokens
  };
}

function mapDispatchToProps(dispatch) {
  return {
    tokenList: payload => dispatch(tokenList(payload)),
    tokenListSearch: payload => dispatch(tokenListSearch(payload))
  };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(TokenListPage));

