import React, { Component } from 'react';
import { getChainInfo, initChainInfo, setChainInfo} from 'utils/utils'

class InitPage extends Component {

  async componentDidMount() {
    let chainName = getChainInfo();
    if(chainName === null) {
      chainName = await initChainInfo();
    }
    const redirectUrl = `/main#${chainName}`;
    this.props.history.push(redirectUrl);
    window.location.reload();
  }

  render() {
    return(
        "Redirection Page"
    );
  }
}

export default InitPage;
