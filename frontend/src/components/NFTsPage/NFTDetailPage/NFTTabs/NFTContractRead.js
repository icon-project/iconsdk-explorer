import React, { Component } from 'react';
import {
  ContractRead
} from 'components'

class NFTContractRead extends Component {
  render() {
    return <ContractRead {...this.props}/>
  }
}

export default NFTContractRead;