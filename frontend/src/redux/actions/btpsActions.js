import actionTypes from '../actionTypes/actionTypes';

export function btpList(payload) {
  return {
    type: actionTypes.btpList,
    payload
  }
}

export function btpInfo(payload) {
  return {
    type: actionTypes.btpInfo,
    payload
  }
}

export function btpTxList(payload) {
  return {
    type: actionTypes.btpTxList,
    payload
  }
}

