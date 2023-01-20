import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import BTPTransactions from './BTPTransactions'
import {
    TX_TYPE,
    BTP_TABS,
} from 'utils/const'
import {
    NoBox,
    TabTable
} from 'components'
import { getChainInfo } from "utils/utils";

class BTPTabs extends Component {

    render() {
        const { on, btp, btpTx } = this.props
        const { loading, data } = btp
        const { height } = data
        return (
            <TabTable
                {...this.props}
                TABS={BTP_TABS}
                on={on}
                loading={loading}
                TableContents={on => {
                    switch (on) {
                        case 0:
                            return (
                                <BTPTransactions
                                    txData={btpTx}
                                    goAllTx={() => {this.props.history.push(`/${TX_TYPE.BTP_TX}/${height}#${getChainInfo()}`)}}
                                    txType={TX_TYPE.BTP_TX}
                                    btpMessage={this.props.btpMessage}
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

export default withRouter(BTPTabs);
