import React, { Component } from 'react';
import {
	convertNumberToText,
	numberWithCommas,
	tokenText,
	onlyDate,
} from 'utils/utils'
import {
	AddressLink,
	AmountCell,
} from 'components'
import {
	CONTRACT_STATUS,
	SEARCH_TYPE
} from 'utils/const'

class SearchTableBody extends Component {
	render() {
		const TableRow = () => {
			const { searchType, data } = this.props
			switch (searchType) {
				case SEARCH_TYPE.CONTRACTS:
					return (
						<tr>
							<td className="on"><span className="ellipsis"><AddressLink to={data.address} /></span></td>
							<td>{data.contractName || '-'}</td>
							<AmountCell type="icx" amount={data.balance} symbol="ICX" />
							<td>{numberWithCommas(data.txCount)}</td>
							<td>{CONTRACT_STATUS[data.status]}</td>
							<td>{onlyDate(data.verifiedDate)}</td>
						</tr>
					)
				case SEARCH_TYPE.TOKENS:
					const { index, count, page } = this.props
					const ranking = count * (page - 1) + index + 1
					return (
						<tr>
							<td>{ranking}</td>
							<td><span className="ellipsis">{tokenText(data.name, data.symbol, data.contractAddr)}</span></td>
							<td>{convertNumberToText(Math.floor(data.volume)) || '-'}<em>USD</em></td>
						</tr>
					)
				default:
					return <tr></tr>
			}
		}

		return TableRow()
	}
}

export default SearchTableBody