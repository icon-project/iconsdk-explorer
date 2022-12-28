import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import NFTTransfers from './NFTTransfers'
import NFTHolders from './NFTHolders'
import NFTContractRead from './NFTContractRead'
import {
    NoBox,
    TabTable
} from 'components'
import {
    TX_TYPE,
    NFT_TABS,
} from 'utils/const'

class NFTTabs extends Component {

    render() {
        const { on, nft, nftTransfers, nftHolders, contractReadInfo } = this.props
        const { loading, data } = nft
        const { contract } = data
        return (
            <TabTable
                {...this.props}
                TABS={NFT_TABS}
                on={on}
                loading={loading}
                TableContents={on => {
                    switch (on) {
                        case 0:
                            return (
                                <NFTTransfers
                                    txData={nftTransfers}
                                    goAllTx={() => { this.props.history.push(`/${TX_TYPE.NFT_TX}/${contract}`) }}
                                    txType={TX_TYPE.NFT_TX}
                                />
                            )
                        case 1:
                            return (
                                <NFTHolders
                                    txData={nftHolders}
                                    goAllTx={() => { this.props.history.push(`/${TX_TYPE.NFT_HOLDERS}/${contract}`) }}
                                    txType={TX_TYPE.NFT_HOLDERS}
                                />
                            )
                        case 2:
                            return (
                                <NFTContractRead
                                    contract={{ data: { address: contract } }}
                                    contractReadInfo={contractReadInfo}
                                    icxCall={this.props.icxCall}
                                />
                            )
                        default:
                            return <NoBox text="No Data" />
                    }
                }}
            />
        )
    }
}

export default withRouter(NFTTabs);