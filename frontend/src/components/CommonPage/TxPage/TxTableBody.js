import React, { Component } from 'react';
import {
	calcFromNow,
	numberWithCommas,
	dateToUTC,
	isValidData,
	tokenText,
	is0xHash,
} from 'utils/utils'
import {
	TransactionLink,
	BlockLink,
	BTPLink,
	AddressCell,
	AddressSet,
	AmountCell,
	NetworkLink
} from 'components'
import {
	TX_TYPE,
} from 'utils/const'
import configData from '../../../config/config.json'

const TxHashCell = ({ isError, txHash }) => {
	let _txHash, className
	if (!isValidData(txHash)) {
		_txHash = '-'
		className = 'no'
	}
	else {
		const _is0xHash = is0xHash(txHash)
		_txHash = _is0xHash ? <TransactionLink to={txHash} label={<span className="ellipsis">{txHash}</span>} /> : txHash
		className = `${isError ? 'icon error' : ''} ${_is0xHash ? 'on' : ''}`	
	}
	return (
		<td className={className}>
			{(_txHash !== '-' && isError) && <i className="img"></i>}
			{_txHash}
		</td>
	)
}

const TokenCell = ({ name, address }) => {
	return <td><span className="ellipsis">{tokenText(name, undefined, address, 'ellipsis')}</span></td>
}

const DateCell = ({ date, isDate }) => {
	let className, dateText
	if (!isValidData(date)) {
		className = ""
		dateText = "-"
	}
	else {
		className = "break"
		if (isDate) {
			dateText = dateToUTC(date)
		}
		else {
			dateText = calcFromNow(date)
		}
	}
	return <td className={className}>{dateText}</td>
}

const BlockCell = ({ height }) => {
	return <td className="on"><BlockLink to={height} label={numberWithCommas(height)} /></td>
}

const BTPCell = ({ height, networkId }) => {
	return <td className="on"><BTPLink to={height} networkId={networkId} label={numberWithCommas(height)} /></td>
}

const NetworkCell = ({ networkId }) => {
	return <td className="on"><NetworkLink to={networkId} label={networkId} /></td>
}

const getNetworkName = (networks, networkId) => {
	return networks.map(network => {
		if(network.networkId === networkId){
			return network.networkName;
		}
		return null;
	});
}

const getNetworkType = (networks, btpNetworkNetworkId) => {
	return networks.map(network => {
		if (network.networkTypeId === btpNetworkNetworkId) {
			return network.networkTypeName;
		}
		return null;
	});
}

class TxTableBody extends Component {

	render() {
		const TableRow = (_props) => {
			const {
				txType,
				data,
				address,
				networks
			} = this.props

			const addressInData = data.address
			const isError = data.state === 0
			switch (txType) {
				case TX_TYPE.ADDRESS_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<BlockCell height={data.height} />
							<DateCell date={data.createDate} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} address={address} txType={data.txType} targetContractAddr={data.targetContractAddr} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.ADDRESS_INTERNAL_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<BlockCell height={data.height} />
							<DateCell date={data.createDate} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} address={address} txType={data.txType} targetContractAddr={address} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.ADDRESS_TOKEN_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<DateCell date={data.createDate} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} address={address} txType={data.txType} targetContractAddr={data.contractAddr} />
							<AmountCell amount={data.quantity} symbol={data.contractSymbol} />
							<TokenCell name={data.contractName} address={data.contractAddr} />
						</tr>
					)
				case TX_TYPE.CONTRACT_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<BlockCell height={data.height} />
							<DateCell date={data.createDate} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} address={address} txType={data.txType} targetContractAddr={address} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.CONTRACT_INTERNAL_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<BlockCell height={data.height} />
							<DateCell date={data.createDate} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} address={address} txType={data.txType} targetContractAddr={address} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.CONTRACT_TOKEN_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<DateCell date={data.age} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} address={address} txType={data.txType} targetContractAddr={address} />
							<AmountCell amount={data.quantity} symbol={data.symbol} />
							<TokenCell name={data.name} address={data.tradeTokenAddr} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.BLOCK_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.targetContractAddr} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.TRANSACTIONS:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<BlockCell height={data.height} />
							<DateCell date={data.createDate} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.targetContractAddr} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.TOKEN_TRANSFERS:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<DateCell date={data.age} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.contractAddr} />
							<AmountCell amount={data.quantity} symbol={data.symbol} />
							<TokenCell name={data.tokenName} address={data.contractAddr} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.TOKEN_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<DateCell date={data.age} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.contractAddr} />
							<AmountCell amount={data.quantity} symbol={data.symbol} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.ADDRESSES:
					return (
						<tr>
							<AddressCell targetAddr={addressInData} txType={data.txType} />
							<AmountCell amount={data.balance} symbol={configData.ICONSDK_EXPLORER_SYM} />
							<td>{numberWithCommas(data.txCount)}</td>
							<td>{data.nodeType}</td>
						</tr>
					)
				case TX_TYPE.BLOCKS:
					return (
						<tr>
							<BlockCell height={data.height} />
							<DateCell date={data.createDate} />
							<td>{numberWithCommas(data.txCount)}</td>
							<td><BlockLink label={data.hash} to={data.height} ellipsis /></td>
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
							<AmountCell amount={data.fee} symbol={configData.ICONSDK_EXPLORER_SYM} />
						</tr>
					)
				case TX_TYPE.BTPS:
					return (
						<tr>
							<BTPCell height={data.blockHeight} networkId={data.networkId} />
							<td>{getNetworkType(networks.data, data.btpNetworkId)}</td>
							<td>{getNetworkName(networks.data, data.networkId)}</td>
							<DateCell date={data.createDate} />
							<td>{data.messageCnt}</td>
						</tr>
					)
				// TODO view btp message
				case TX_TYPE.BTP_TX:
					return (
						<tr>
							<td>{data.btpMessageSn}</td>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<td><BTPMessageButton btpTx={data} btpMessage={this.props.btpMessage} /></td>
						</tr>
					)
				case TX_TYPE.NETWORKS:
					return (
						<tr>
							<NetworkCell networkId={data.networkId} />
							<td>{data.networkTypeName}</td>
							<td>{data.networkName}</td>
							<td>{data.startHeight}</td>
						</tr>
					)
				case TX_TYPE.NETWORK_BTPS:
					return (
						<tr>
							<BTPCell height={data.blockHeight} networkId={data.networkId} />
							<td>{getNetworkType(networks.data, data.btpNetworkId)}</td>
							<td>{getNetworkName(networks.data, data.networkId)}</td>
							<DateCell date={data.createDate} />
							<td>{data.messageCnt}</td>
						</tr>
					)
				case TX_TYPE.CONTRACT_EVENTS:
					return (
						<tr>
							<td className="on" style={{maxWidth: '234px'}}>
								<span className="ellipsis"><TransactionLink to={data.txHash} /></span><br />
								<span><BlockLink label={`# ${data.height}`} to={data.height} /></span>
								<p>{calcFromNow(data.age)}</p>
							</td>
							<td style={{maxWidth: '234px', wordWrap: 'break-word'}}>{data.method}</td>
							<td style={{maxWidth: '690px', wordWrap: 'break-word'}}>{data.eventLog}</td>
						</tr>
					)
				case TX_TYPE.TRANSACTION_EVENTS:
					return (
						<tr>
							<td style={{maxWidth: '1155px', wordWrap: 'break-word'}}>{data.eventLog}</td>
						</tr>
					)
				case TX_TYPE.TRANSACTION_INTERNAL_TX:
					return (
						<tr>
							{/* <td>-</td> */}
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.targetContractAddr} />
							<AmountCell amount={data.amount} symbol={configData.ICONSDK_EXPLORER_SYM} />
							{/* <td>-</td> */}
						</tr>
					)
				case TX_TYPE.TOKEN_HOLDERS:
					return (
						<tr>
							<td>{data.rank}</td>
							<AddressCell targetAddr={addressInData} txType={data.txType} spanNoEllipsis />
							<AmountCell amount={data.quantity} symbol={data.symbol} />
							<td><span>{data.percentage}</span><em>%</em></td>
						</tr>
					)
				case TX_TYPE.NFT_TRANSFERS:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<DateCell date={data.age} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.contractAddr} />
							<td><span>{data.tokenId}</span></td>
							<AmountCell amount={data.quantity} symbol={data.symbol} />
						</tr>
					)
				case TX_TYPE.NFT_TX:
					return (
						<tr>
							<TxHashCell isError={isError} txHash={data.txHash} />
							<DateCell date={data.age} />
							<AddressSet fromAddr={data.fromAddr} toAddr={data.toAddr} txType={data.txType} targetContractAddr={data.contractAddr} />
							<td><span>{data.tokenId}</span></td>
							<AmountCell amount={data.quantity} symbol={data.symbol} />
						</tr>
					)
				case TX_TYPE.NFT_HOLDERS:
					return (
						<tr>
							<td>{data.rank}</td>
							<AddressCell targetAddr={addressInData} txType={data.txType} spanNoEllipsis />
							<AmountCell amount={data.quantity} symbol={data.symbol} />
						</tr>
					)
				default:
					return <tr></tr>
			}
		}

		return TableRow(this.props)
	}
}

class BTPMessageButton extends Component {
	handleClick = () => {
		const { btpHeaderBlockHeight, btpHeaderNetworkId, btpMessageSn } = this.props.btpTx
		this.props.btpMessage({
			'height': btpHeaderBlockHeight, 'sequenceNumber': btpMessageSn, 'networkId': btpHeaderNetworkId
		})
	}

	render() {
		return (
			<button onClick={this.handleClick} className="btn-type-normal status">
				Message
			</button>
		)
	}
}

export default TxTableBody