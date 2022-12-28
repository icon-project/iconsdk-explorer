import actionTypes from '../actionTypes/actionTypes';

export function nftList(payload) {
  return {
    type: actionTypes.nftList,
    payload
  }
}

export function nftListSearch(payload) {
  return {
    type: actionTypes.nftListSearch,
    payload
  }
}

export function nftTxList(payload) {
  return {
    type: actionTypes.nftTxList,
    payload
  }
}

export function nftSummary(payload) {
  return {
    type: actionTypes.nftSummary,
    payload
  }
}

export function nftTransfersList(payload) {
  return {
    type: actionTypes.nftTransfersList,
    payload
  }
}

export function nftHoldersList(payload) {
  return {
    type: actionTypes.nftHoldersList,
    payload
  }
}