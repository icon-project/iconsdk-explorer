import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import NetworkBTPs from './NetworkBTPs'
import { NoBox, TabTable } from 'components'
import { TX_TYPE, NETWORK_TABS } from 'utils/const'

class NetworkTabs extends Component {

    render() {
        const { on, network, networkBTPs, networks } = this.props
        const { loading, data } = network

        const { networkId } = data

        return (
            <TabTable
                {...this.props}
                TABS={NETWORK_TABS}
                on={on}
                loading={loading}
                TableContents={on => {
                    switch (on) {
                        case 0:
                            return (
                                <NetworkBTPs
                                    txData={networkBTPs}
                                    goAllTx={() => {this.props.history.push(`/${TX_TYPE.NETWORK_BTPS}/${networkId}`)}}
                                    txType={TX_TYPE.NETWORK_BTPS}
                                    networks={networks}
                                />
                            )
                        default:
                            return <NoBox text="No Data"/>
                    }
                }}
            />
        )
    }
}

export default withRouter(NetworkTabs);
