import React, { Component } from 'react';
import {
  TxBottom
} from 'components'

class BTPTransactions extends Component {
  render() {
    return <TxBottom {...this.props}/>
  }
}

export default BTPTransactions;
