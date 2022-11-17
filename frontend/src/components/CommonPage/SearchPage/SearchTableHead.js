import React, { Component } from 'react';
import {
    StatusHolder
} from 'components'
import {
    SEARCH_TYPE
} from 'utils/const'

class SearchTableHead extends Component {
    render() {
        const TableHead = () => {
            const { searchType } = this.props
            switch (searchType) {
                case SEARCH_TYPE.CONTRACTS:
                    return (
                        <tr>
                            <th>Address</th>
                            <th>Contract Name</th>
                            <th>Balance</th>
                            <th>TxCount</th>
                            <StatusHolder getData={this.props.getListByStatus}/>
                            <th>Confirmed date</th>
                        </tr>
                    )
                case SEARCH_TYPE.TOKENS:
                    return (
                        <tr>
                            <th>No.</th>
                            <th>Token</th>
                            <th>Volume (24h)</th>
                        </tr>
                    )
                default:
                    return (
                        <tr></tr>
                    )
            }
        }

        return TableHead()
    }
}

export default SearchTableHead