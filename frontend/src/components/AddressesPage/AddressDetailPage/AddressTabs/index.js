import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import AddressTransactions from './AddressTransactions'
import AddressInternalTransactions from './AddressInternalTransactions'
import AddressTokenTransfers from './AddressTokenTransfers'
import {
    TX_TYPE,
    ADDRESS_TABS,
} from 'utils/const'
import {
    NoBox,
    TabTable
} from 'components'
import { getChainInfo } from "utils/utils";

class WalletTabs extends Component {
    render() {
        const {
            on, wallet, walletTx, addressInternalTx, walletTokenTx,
        } = this.props
        const { loading, data } = wallet
        const { address, tokenList, internalTxCount } = data

        const TABS = []
        TABS.push(ADDRESS_TABS[0])
        if (internalTxCount && Number(internalTxCount) !== 0) {
            TABS.push(ADDRESS_TABS[1])
        }
        if (tokenList && tokenList.length !== 0) {
            TABS.push(ADDRESS_TABS[2])
        }

        return (
            <TabTable
                {...this.props}
                TABS={TABS}
                on={on}
                loading={loading}
                TableContents={on => {
                    switch (TABS[on]) {
                        case ADDRESS_TABS[0]:
                            return (
                                <AddressTransactions
                                    txData={walletTx}
                                    goAllTx={() => { this.props.history.push(`/${TX_TYPE.ADDRESS_TX}/${address}#${getChainInfo()}`) }}
                                    txType={TX_TYPE.ADDRESS_TX}
                                    address={address}
                                />
                            )
                        case ADDRESS_TABS[1]:
                            return (
                                <AddressInternalTransactions
                                    txData={addressInternalTx}
                                    goAllTx={() => { this.props.history.push(`/${TX_TYPE.ADDRESS_INTERNAL_TX}/${address}#${getChainInfo()}`) }}
                                    txType={TX_TYPE.ADDRESS_INTERNAL_TX}
                                    address={address}
                                />
                            )
                        case ADDRESS_TABS[2]:
                            return (
                                <AddressTokenTransfers
                                    txData={walletTokenTx}
                                    goAllTx={() => { this.props.history.push(`/${TX_TYPE.ADDRESS_TOKEN_TX}/${address}#${getChainInfo()}`) }}
                                    txType={TX_TYPE.ADDRESS_TOKEN_TX}
                                    address={address}
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

export default withRouter(WalletTabs);
