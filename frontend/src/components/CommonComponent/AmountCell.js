import React from 'react';
import {
    convertNumberToText,
    convertToExponentialText
} from 'utils/utils'
import configData from '../../config/config.json'

const AmountCell = ({ amount, decimal, symbol, noEllipsis }) => {
	amount = convertNumberToText(amount || "0", decimal || 4)
	amount = amount.length < 20 ? amount : convertToExponentialText(amount, decimal || 4)
	return <td><span>{amount}</span><em className={!(symbol === configData.ICONSDK_EXPLORER_SYM || noEllipsis) ? "ellipsis" : ""}>{symbol}</em></td>
}

export default AmountCell
